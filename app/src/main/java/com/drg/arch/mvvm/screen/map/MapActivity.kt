package com.drg.arch.mvvm.screen.map

import android.Manifest
import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProvider
import android.content.Context
import android.content.Intent
import android.content.IntentSender
import android.content.pm.PackageManager
import android.location.Location
import android.os.Bundle
import android.support.v4.app.ActivityCompat
import android.support.v4.content.ContextCompat
import com.drg.arch.mvvm.R
import com.drg.arch.mvvm.data.entity.remote.common.MapRemote
import com.drg.arch.mvvm.databinding.AcMapsBinding
import com.drg.arch.mvvm.extension.toast
import com.drg.arch.mvvm.screen.base.BaseActivity
import com.google.android.gms.common.api.ResolvableApiException
import com.google.android.gms.location.*
import com.google.android.gms.location.LocationServices.getFusedLocationProviderClient
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.MapView
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.google.android.gms.tasks.Task
import javax.inject.Inject


class MapActivity : BaseActivity<MapViewModel, AcMapsBinding>(), OnMapReadyCallback, GoogleMap.OnMyLocationButtonClickListener, GoogleMap.OnMyLocationClickListener {

	companion object {
		private const val MY_LOCATION_REQUEST_CODE = 1548
		private const val REQUESTING_LOCATION_UPDATES_KEY = "REQUESTING_LOCATION_UPDATES_KEY"

		fun getIntent(ctx: Context) = Intent(ctx, MapActivity::class.java)
	}

	@Inject
	lateinit var vmFactory: ViewModelProvider.Factory

	private val locationRequest = LocationRequest().apply {
		interval = 10000
		fastestInterval = 5000
		priority = LocationRequest.PRIORITY_HIGH_ACCURACY
	}

	private var requestingLocationUpdates = false;

	private lateinit var mMap: GoogleMap
	private lateinit var locationCallback: LocationCallback

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)

		setSupportActionBar(binding.incToolbar.toolbar)

		supportActionBar?.apply {
			title = "Map"
			setDisplayHomeAsUpEnabled(true)
		}

		viewModel.markers.observe(this, Observer<List<MapRemote>> {
			mMap.clear()
			if (it != null) {
				val size = it.size
				for (i in it.indices) {
					val coordinates = LatLng(it[i].lat, it[i].lon)
					mMap.addMarker(MarkerOptions().position(coordinates).title(it[i].name))
					if (i == size - 1) {
						mMap.animateCamera(CameraUpdateFactory.newLatLng(coordinates))
					}
				}
			}
		})

		locationCallback = object : LocationCallback() {
			override fun onLocationResult(locationResult: LocationResult?) {
				locationResult ?: return
				for (location in locationResult.locations) {
					// Update UI with location data
					toast("New location: ${location.latitude} ${location.longitude}")
				}
			}
		}

		val mapView = findViewById<MapView>(R.id.mapView)
		mapView.onCreate(savedInstanceState)
		mapView.onResume()
		mapView.getMapAsync(this)
		updateValuesFromBundle(savedInstanceState)
	}

	override fun onResume() {
		super.onResume()
		if (requestingLocationUpdates) startLocationUpdates()
	}

	override fun onPause() {
		super.onPause()
		stopLocationUpdates()
	}

	override fun onSaveInstanceState(outState: Bundle?) {
		outState?.putBoolean(REQUESTING_LOCATION_UPDATES_KEY, requestingLocationUpdates)
		super.onSaveInstanceState(outState)
	}

	override fun onMapReady(googleMap: GoogleMap) {
		mMap = googleMap
		viewModel.loadMarkers()
		enableMyLocation()
	}

	override fun getLayoutRes(): Int {
		return R.layout.ac_maps
	}

	override fun getViewModelClass() = MapViewModel::class.java

	override fun getViewModelFactory() = vmFactory

	override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
		if (requestCode == MY_LOCATION_REQUEST_CODE) {
			if (permissions.size == 1 &&
					permissions[0] == Manifest.permission.ACCESS_FINE_LOCATION &&
					grantResults[0] == PackageManager.PERMISSION_GRANTED) {
				enableMyLocation()
			} else {
				onError("My location permission denied")
			}
		}
	}

	override fun onMyLocationButtonClick(): Boolean {
		toast("OnMyLocationButtonClick")
		return false
	}

	override fun onMyLocationClick(location: Location) = toast("Current location: ${location.latitude} ${location.longitude}")

	private fun enableMyLocation() {
		if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
			mMap.isMyLocationEnabled = true
			mMap.setOnMyLocationButtonClickListener(this)
			mMap.setOnMyLocationClickListener(this)
			startCheckLocationSettings()
		} else {
			ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.ACCESS_FINE_LOCATION), MY_LOCATION_REQUEST_CODE)
		}
	}

	private fun startCheckLocationSettings() {
		val builder = LocationSettingsRequest.Builder().addLocationRequest(locationRequest)
		val client: SettingsClient = LocationServices.getSettingsClient(this)
		val task: Task<LocationSettingsResponse> = client.checkLocationSettings(builder.build())
		task.addOnSuccessListener { locationSettingsResponse ->
			// All location settings are satisfied. The client can initialize
			// location requests here.
			requestingLocationUpdates = true
			startLocationUpdates()
		}
		task.addOnFailureListener { exception ->
			if (exception is ResolvableApiException) {
				try {
					// Show the dialog by calling startResolutionForResult(),
					// and check the result in onActivityResult().
					exception.startResolutionForResult(this@MapActivity, MY_LOCATION_REQUEST_CODE)
				} catch (sendEx: IntentSender.SendIntentException) {
					onError("Cannot get access to user location")
				}
			}
		}
	}

	private fun startLocationUpdates() {
		if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
			getFusedLocationProviderClient(this).requestLocationUpdates(locationRequest, locationCallback, null /* Looper */)
		}
	}

	private fun stopLocationUpdates() {
		if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
			getFusedLocationProviderClient(this).removeLocationUpdates(locationCallback)
		}
	}

	private fun updateValuesFromBundle(savedInstanceState: Bundle?) {
		savedInstanceState ?: return
		// Update the value of requestingLocationUpdates from the Bundle.
		if (savedInstanceState.keySet().contains(REQUESTING_LOCATION_UPDATES_KEY)) {
			requestingLocationUpdates = savedInstanceState.getBoolean(
					REQUESTING_LOCATION_UPDATES_KEY)
		}
	}
}
