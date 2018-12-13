package com.drg.arch.mvvm.screen.beer.list

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProvider
import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.drg.arch.mvvm.R
import com.drg.arch.mvvm.databinding.AcBeerListBinding
import com.drg.arch.mvvm.screen.base.BaseActivity
import javax.inject.Inject

/**
 * Created by aleksey.stepanov
 * 9/4/18
 */

class BeerListActivity : BaseActivity<BeerListViewModel, AcBeerListBinding>() {

	private lateinit var adapter: BeerListAdapter

	@Inject
	lateinit var vmFactory: ViewModelProvider.Factory

	companion object {
		fun getIntent(ctx: Context) = Intent(ctx, BeerListActivity::class.java)
	}

	/* life */

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)

		setSupportActionBar(binding.incToolbar.toolbar)

		supportActionBar?.apply {
			title = "User List ac"
			setDisplayHomeAsUpEnabled(true)
		}

		adapter = BeerListAdapter()

		binding.rv.adapter = adapter

		viewModel.beerList.observe(this, Observer {
			adapter.update(it)
		})
	}

	/* inherited */

	override fun getLayoutRes() = R.layout.ac_beer_list

	override fun getViewModelClass() = BeerListViewModel::class.java

	override fun getViewModelFactory() = vmFactory
}