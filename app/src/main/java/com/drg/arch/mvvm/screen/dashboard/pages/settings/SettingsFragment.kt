package com.drg.arch.mvvm.screen.dashboard.pages.settings

import com.drg.arch.mvvm.R
import com.drg.arch.mvvm.databinding.FrSettingsBinding
import com.drg.arch.mvvm.screen.base.BaseFragment
import javax.inject.Inject

/**
 * Created by aleksey.stepanov
 * 8/10/18.
 */
class SettingsFragment : BaseFragment<SettingsFragmentViewModel, FrSettingsBinding>() {

	@Inject
	lateinit var vmFactory: SettingsFragmentViewModel.Factory

	/* inherited */

	override fun getLayoutRes() = R.layout.fr_settings

	override fun getViewModelClass() = SettingsFragmentViewModel::class.java

	override fun getViewModelFactory() = vmFactory
}