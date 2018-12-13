package com.drg.arch.mvvm.screen.userList

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProvider
import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.drg.arch.mvvm.R
import com.drg.arch.mvvm.databinding.AcUserListBinding
import com.drg.arch.mvvm.screen.base.BaseActivity
import javax.inject.Inject

/**
 * Created by aleksey.stepanov
 * 8/9/18.
 */
class UserListActivity : BaseActivity<UserListViewModel, AcUserListBinding>() {

	private lateinit var adapter: UserListAdapter

	@Inject
	lateinit var vmFactory: ViewModelProvider.Factory

	companion object {
		fun getIntent(ctx: Context) = Intent(ctx, UserListActivity::class.java)
	}

	/* life */

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)

		setSupportActionBar(binding.incToolbar.toolbar)

		supportActionBar?.apply {
			title = "User List ac"
			setDisplayHomeAsUpEnabled(true)
		}

		adapter = UserListAdapter()

		binding.rv.adapter = adapter

		viewModel.userList.observe(this, Observer {
			adapter.update(it)
		})
	}

	/* inherited */

	override fun getLayoutRes() = R.layout.ac_user_list

	override fun getViewModelClass() = UserListViewModel::class.java

	override fun getViewModelFactory() = vmFactory
}