package com.drg.arch.mvvm.screen.login

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProvider
import android.content.Intent
import android.os.Bundle
import com.drg.arch.mvvm.R
import com.drg.arch.mvvm.databinding.AcLoginBinding
import com.drg.arch.mvvm.screen.base.BaseActivity
import com.drg.arch.mvvm.screen.dashboard.DashboardActivity
import javax.inject.Inject

/**
 * Created by aleksey.stepanov
 * 8/3/18.
 */
class LoginActivity : BaseActivity<LoginViewModel, AcLoginBinding>() {

	@Inject
	lateinit var vmFactory: ViewModelProvider.Factory

	/* life */

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)

		viewModel.isAuthorized.observe(this, Observer {
			if (it == true) {
				startActivity(Intent(this, DashboardActivity::class.java))
				finish()
			}
		})

	}

	/* inherited */

	override fun getLayoutRes() = R.layout.ac_login

	override fun getViewModelClass() = LoginViewModel::class.java

	override fun getViewModelFactory() = vmFactory
}