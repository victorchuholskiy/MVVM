package com.drg.arch.mvvm.screen.userList

import android.databinding.DataBindingUtil
import android.databinding.ViewDataBinding
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.drg.arch.mvvm.BR
import com.drg.arch.mvvm.R
import com.drg.arch.mvvm.data.entity.remote.common.UserRemote

/**
 * Created by aleksey.stepanov
 * 8/9/18.
 */
class UserListAdapter : RecyclerView.Adapter<UserListAdapter.VH>() {

	private val data = mutableListOf<UserRemote>()

	/* inherited */

	override fun getItemCount() = data.size

	override fun onCreateViewHolder(parent: ViewGroup,
									viewType: Int): VH {
		val binding: ViewDataBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.item_user, parent, false)
		return VH(binding)
	}

	override fun onBindViewHolder(holder: VH,
								  position: Int) = holder.bind(data[position])

	/* own methods */

	fun update(newData: List<UserRemote>?) {
		data.clear()
		newData?.run { data.addAll(this) }
		notifyDataSetChanged()
	}

	/* data types */

	class VH(private val binding: ViewDataBinding) : RecyclerView.ViewHolder(binding.root) {

		fun bind(user: UserRemote) {
			binding.setVariable(BR.user, user)
			binding.executePendingBindings()
		}

	}
}