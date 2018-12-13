package com.drg.arch.mvvm.screen.dashboard.pages.home

import android.arch.lifecycle.Observer
import android.os.Bundle
import android.view.View
import com.drg.arch.mvvm.R
import com.drg.arch.mvvm.databinding.FrHomeBinding
import com.drg.arch.mvvm.screen.base.BaseFragment
import com.drg.arch.mvvm.screen.beer.list.BeerListActivity
import com.drg.arch.mvvm.screen.encrypt.EncryptActivity
import com.drg.arch.mvvm.screen.map.MapActivity
import com.drg.arch.mvvm.screen.userList.UserListActivity
import javax.inject.Inject

/**
 * Created by aleksey.stepanov
 * 8/10/18.
 */
class HomeFragment : BaseFragment<HomeFragmentViewModel, FrHomeBinding>() {

	@Inject
	lateinit var vmFactory: HomeFragmentViewModel.Factory

	/* life */

	override fun onViewCreated(view: View,
							   savedInstanceState: Bundle?) {
		super.onViewCreated(view, savedInstanceState)

		viewModel.goToUserList.observe(this, Observer {
			if (it == true) {
				startActivity(UserListActivity.getIntent(context!!))
			}
		})

		viewModel.goToBeerList.observe(this, Observer {
			if (it == true) {
				startActivity(BeerListActivity.getIntent(context!!))
			}
		})

		viewModel.goToEncrypt.observe(this, Observer {
			if (it == true) {
				startActivity(EncryptActivity.getIntent(context!!))
			}
		})

		viewModel.goToMap.observe(this, Observer {
			if (it == true) {
				startActivity(MapActivity.getIntent(context!!))
			}
		})
	}

	/* inherited */

	override fun getLayoutRes() = R.layout.fr_home

	override fun getViewModelClass() = HomeFragmentViewModel::class.java

	override fun getViewModelFactory() = vmFactory
}