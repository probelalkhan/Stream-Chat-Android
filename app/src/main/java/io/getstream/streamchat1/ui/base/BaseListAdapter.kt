package io.getstream.streamchat1.ui.base

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding

abstract class BaseListAdapter<I : Any, VB : ViewBinding>(comparator: DiffUtil.ItemCallback<I>) :
    ListAdapter<I, BaseListAdapter.Companion.BaseViewHolder<VB>>(comparator) {

    var itemClickListener: ((item: I, position: Int, view: View) -> Unit)? = null

    abstract fun getItemViewBinding(parent: ViewGroup): VB
    abstract fun bindItem(binding: VB, item: I)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        BaseViewHolder(getItemViewBinding(parent))

    override fun onBindViewHolder(holder: BaseViewHolder<VB>, position: Int) {
        getItem(position)?.let {
            bindItem(holder.binding, it)
            holder.binding.root.setOnClickListener { view ->
                itemClickListener?.invoke(it, position, view)
            }
        }
    }

    companion object {
        class BaseViewHolder<VB : ViewBinding>(val binding: VB) :
            RecyclerView.ViewHolder(binding.root)
    }
}