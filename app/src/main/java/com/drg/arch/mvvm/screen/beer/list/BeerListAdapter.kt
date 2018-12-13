package com.drg.arch.mvvm.screen.beer.list

import android.databinding.DataBindingUtil
import android.databinding.ViewDataBinding
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.android.databinding.library.baseAdapters.BR
import com.drg.arch.mvvm.R
import com.drg.arch.mvvm.data.entity.remote.common.BeerRemote

/**
 * Created by aleksey.stepanov
 * 9/4/18
 */

class BeerListAdapter : RecyclerView.Adapter<BeerListAdapter.VH>() {

	private val data = mutableListOf<BeerRemote>()

	/* inherited */

	override fun getItemCount() = data.size

	override fun onCreateViewHolder(parent: ViewGroup,
									viewType: Int): VH {
		val binding: ViewDataBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.item_beer, parent, false)
		return VH(binding)
	}

	override fun onBindViewHolder(holder: VH,
								  position: Int) = holder.bind(data[position])

	/* own methods */

	fun update(newData: List<BeerRemote>?) {
		data.clear()
		newData?.run { data.addAll(this) }
		notifyDataSetChanged()
	}

	/* data types */

	class VH(private val binding: ViewDataBinding) : RecyclerView.ViewHolder(binding.root) {

		fun bind(beer: BeerRemote) {
			binding.setVariable(BR.beer, beer)
			binding.executePendingBindings()
		}
	}
}