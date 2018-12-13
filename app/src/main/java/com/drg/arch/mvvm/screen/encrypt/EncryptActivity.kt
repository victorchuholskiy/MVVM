package com.drg.arch.mvvm.screen.encrypt

import android.arch.lifecycle.ViewModelProvider
import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.drg.arch.mvvm.R
import com.drg.arch.mvvm.databinding.AcEncryptBinding
import com.drg.arch.mvvm.screen.base.BaseActivity
import javax.inject.Inject

/**
 * Created by aleksey.stepanov
 * 8/3/18.
 */
class EncryptActivity : BaseActivity<EncryptViewModel, AcEncryptBinding>() {

	@Inject
	lateinit var vmFactory: ViewModelProvider.Factory

	companion object {
		fun getIntent(ctx: Context) = Intent(ctx, EncryptActivity::class.java)
	}

	/* life */

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)

		viewModel.init(applicationContext)

//		viewModel.isAuthorized.observe(this, Observer {
//			if (it == true) {
//				startActivity(Intent(this, DashboardActivity::class.java))
//				finish()
//			}
//		})

	}

	/* inherited */

	override fun getLayoutRes() = R.layout.ac_encrypt

	override fun getViewModelClass() = EncryptViewModel::class.java

	override fun getViewModelFactory() = vmFactory
}