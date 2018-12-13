package com.drg.arch.mvvm.screen.map

import com.google.android.gms.maps.MapView
import org.mockito.Mockito.`when`
import com.google.android.gms.maps.CameraUpdate
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.CameraPosition
import android.os.Bundle
import com.google.android.gms.maps.model.MarkerOptions
import com.google.android.gms.maps.GoogleMap
import android.app.Activity
import org.robolectric.Robolectric
import org.junit.Before
import org.junit.Test
import org.robolectric.android.controller.ActivityController
import org.robolectric.RobolectricTestRunner
import org.junit.runner.RunWith
import org.mockito.Mockito.mock
import org.robolectric.annotation.Config
import android.R.attr.button




/**
 * Created by viktor.chukholskiy
 * 11/27/18.
 */
@RunWith(RobolectricTestRunner::class)
class MapActivityTest {

	@Test
	fun clickingButton_shouldChangeMessage() {
		val activity = Robolectric.setupActivity(MapActivity::class.java)
/*
		activity.button.performClick()

		assertThat(activity.message.getText()).isEqualTo("Robolectric Rocks!")*/
	}


	/*private var controller: ActivityController<Activity>? = null
	private var mapView: MapView? = null
	private var map: GoogleMap? = null
	private var cameraUpdate: CameraUpdate? = null
	private var context: Activity? = null

	@Before
	fun setUp() {
		controller = Robolectric.buildActivity(Activity::class.java)
		context = controller!!.create().get()
		mapView = mock(MapView::class.java)
		map = mock(GoogleMap::class.java)
		`when`(mapView!!.getMapAsync { return map }()).thenReturn(map)
		cameraUpdate = mock(CameraUpdate::class.java)
		wrapper = object : GoogleMapWrapperStub(context, mapView) {
			protected fun createCameraUpdate(latitude: Double, longitude: Double, zoomLevel: Int): CameraUpdate? {
				return cameraUpdate
			}
		}
	}

	@Test
	fun testMapWrapper() {
		wrapper!!.setMyLocationEnabled(true)
		verify(map).setMyLocationEnabled(true)

		val cameraListener = mock(GoogleMap.OnCameraChangeListener::class.java)
		wrapper!!.setOnCameraChangeListener(cameraListener)
		verify(map).setOnCameraChangeListener(cameraListener)

		val mapClickListener = mock(GoogleMap.OnMapClickListener::class.java)
		wrapper!!.setOnMapClickListener(mapClickListener)
		verify(map).setOnMapClickListener(mapClickListener)

		val markerClickListener = mock(GoogleMap.OnMarkerClickListener::class.java)
		wrapper!!.setOnMarkerClickListener(markerClickListener)
		verify(map).setOnMarkerClickListener(markerClickListener)

		wrapper!!.getUiSettings()
		verify(map).getUiSettings()

		val marker = mock(Marker::class.java)
		wrapper!!.bringToFront(marker)
		verify(marker).showInfoWindow()

		val markerOptions = mock(MarkerOptions::class.java)
		wrapper!!.addMarker(markerOptions)
		verify(map).addMarker(markerOptions)

		wrapper!!.clear()
		verify(map).clear()

		wrapper!!.onResume()
		verify(mapView).onResume()

		val bundle = Bundle()
		wrapper!!.onSaveInstanceState(bundle)
		verify(mapView).onSaveInstanceState(bundle)

		wrapper!!.onPause()
		verify(mapView).onPause()

		wrapper!!.onLowMemory()
		verify(mapView).onLowMemory()
		wrapper!!.onDestroy()
		verify(mapView).onDestroy()

		val locationClient = mock(LocationClient::class.java)
		wrapper = object : GoogleMapWrapperStub(context, mapView) {
			protected fun getLocationClient(context: Context): LocationClient {
				return locationClient
			}
		}
		wrapper!!.onStart()
		verify(locationClient).connect()

		wrapper!!.onStop()
		verify(locationClient).disconnect()
	}

	@Test
	fun testGetCenter() {
		val expected = LatLng(0.0, 0.0)
		`when`(map!!.cameraPosition).thenReturn(CameraPosition(expected, 10f, 0f, 0f))
		val actual = wrapper!!.getCenter()
		assertEquals(expected, actual)
	}*/

}