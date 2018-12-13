package com.drg.arch.mvvm.screen.dashboard

import com.drg.arch.mvvm.ViewModelProviderFactory
import com.drg.arch.mvvm.screen.base.BaseViewModel

/**
 * Created by aleksey.stepanov
 * 8/15/18.
 */
class DashboardActivityViewModel : BaseViewModel() {

	/* data types */

	class Factory(viewModel: DashboardActivityViewModel) : ViewModelProviderFactory<DashboardActivityViewModel>(viewModel)
}