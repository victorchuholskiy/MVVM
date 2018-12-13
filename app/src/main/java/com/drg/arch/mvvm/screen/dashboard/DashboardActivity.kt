package com.drg.arch.mvvm.screen.dashboard

import android.os.Bundle
import android.support.v7.app.ActionBarDrawerToggle
import com.drg.arch.mvvm.R
import com.drg.arch.mvvm.databinding.AcDashboardBinding
import com.drg.arch.mvvm.screen.base.BaseActivity
import com.drg.arch.mvvm.screen.dashboard.pages.home.HomeFragment
import com.drg.arch.mvvm.screen.dashboard.pages.settings.SettingsFragment
import javax.inject.Inject

/**
 * Created by aleksey.stepanov
 * 8/9/18.
 */
class DashboardActivity : BaseActivity<DashboardActivityViewModel, AcDashboardBinding>() {

	@Inject
	lateinit var vmFactory: DashboardActivityViewModel.Factory

	/* life */

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)

		setSupportActionBar(binding.incToolbar.toolbar)

		val toggle = ActionBarDrawerToggle(this, binding.drawerLayout, binding.incToolbar.toolbar, R.string.app_name, R.string.app_name)
		binding.drawerLayout.addDrawerListener(toggle)
		toggle.syncState()

		configNavigationView()

		if (savedInstanceState == null) {
			binding.navView.setCheckedItem(R.id.nav_home)
			supportFragmentManager.beginTransaction().add(R.id.content_frame, HomeFragment()).commitAllowingStateLoss()
		}
	}

	/* inherited */

	override fun getLayoutRes() = R.layout.ac_dashboard

	override fun getViewModelClass() = DashboardActivityViewModel::class.java

	override fun getViewModelFactory() = vmFactory

	/* own methods */

	private fun configNavigationView() {
		binding.navView.setNavigationItemSelectedListener { menuItem ->
			menuItem.isChecked = true
			binding.drawerLayout.closeDrawers()

			val frg = when (menuItem.itemId) {
				R.id.nav_home -> HomeFragment()
				R.id.nav_settings -> SettingsFragment()
				else -> null
			}

			frg?.let {
				supportFragmentManager.beginTransaction().replace(R.id.content_frame, it).commitAllowingStateLoss()
			}

			true
		}
	}
}