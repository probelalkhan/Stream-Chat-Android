package io.getstream.streamchat1.ui.chat

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import io.getstream.chat.android.client.models.User
import io.getstream.streamchat1.data.UserExtra
import io.getstream.streamchat1.databinding.ItemUserBinding
import io.getstream.streamchat1.ui.base.BaseListAdapter

class UsersAdapter: BaseListAdapter<User, ItemUserBinding>(Comparator()) {

    override fun getItemViewBinding(parent: ViewGroup): ItemUserBinding {
        return ItemUserBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
    }

    override fun bindItem(binding: ItemUserBinding, item: User) {
        binding.textViewName.text = item.name
        binding.textViewPhone.text = item.extraData[UserExtra.PHONE].toString()
    }


    class Comparator: DiffUtil.ItemCallback<User>(){
        override fun areItemsTheSame(oldItem: User, newItem: User): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: User, newItem: User): Boolean {
            return oldItem.id == newItem.id
        }

    }
}