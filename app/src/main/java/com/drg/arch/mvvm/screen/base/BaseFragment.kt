package com.drg.arch.mvvm.screen.base

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.databinding.DataBindingUtil
import android.databinding.ViewDataBinding
import android.os.Bundle
import android.support.annotation.LayoutRes
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.drg.arch.mvvm.BR
import com.drg.arch.mvvm.extension.toast
import dagger.android.support.DaggerAppCompatDialogFragment

/**
 * Created by aleksey.stepanov
 * 8/10/18.
 */
abstract class BaseFragment<VM : BaseViewModel, B : ViewDataBinding> : DaggerAppCompatDialogFragment() {

	protected lateinit var viewModel: VM
	protected lateinit var binding: B

	/* abstract */

	@LayoutRes
	abstract fun getLayoutRes(): Int

	abstract fun getViewModelClass(): Class<VM>

	abstract fun getViewModelFactory(): ViewModelProvider.Factory

	/* life */

	override fun onAttach(context: Context) {
		super.onAttach(context)

		viewModel = ViewModelProviders.of(this, getViewModelFactory())[getViewModelClass()]
	}

	override fun onCreateView(inflater: LayoutInflater,
							  container: ViewGroup?,
							  savedInstanceState: Bundle?): View? {
		binding = DataBindingUtil.inflate(inflater, getLayoutRes(), container, false)
		return binding.root
	}

	override fun onViewCreated(view: View,
							   savedInstanceState: Bundle?) {
		super.onViewCreated(view, savedInstanceState)
		binding.setVariable(BR.viewModel, viewModel)
		binding.setLifecycleOwner(this)
		binding.executePendingBindings()
	}

	override fun onStart() {
		super.onStart()

		viewModel.errorMessage.observe(this, Observer {
			onError(it)
		})
	}

	/* own methods */

	@Suppress("MemberVisibilityCanBePrivate")
	protected fun onError(msg: String?) {
		toast(msg)
	}
}