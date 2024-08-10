package com.example.suitmediaandroidtest.api
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.suitmediaandroidtest.databinding.UserItemBinding
import com.squareup.picasso.Picasso

class AdapterUser(
    private val list: ArrayList<Data>,
    private val onUserClickListener: UserOnClickListener
) : RecyclerView.Adapter<AdapterUser.UserViewHolder>() {

    inner class UserViewHolder(private val binding: UserItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(userResponse: Data) {
            // get all data user
            val firstName = userResponse.first_name
            val lastName = userResponse.last_name
            val email = userResponse.email
            val avatar = userResponse.avatar

            // fill the layout item
            binding.userName.text = "$firstName $lastName"
            binding.emailUser.text = email
            Picasso.with(binding.root.context)
                .load(avatar)
                .resize(140, 140)
                .into(binding.avatarUser)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val binding = UserItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return UserViewHolder(binding)
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        holder.bind(list[position])
        holder.itemView.setOnClickListener {
            onUserClickListener.onUserItemClicked(position)
        }
    }

    override fun getItemCount(): Int = list.size

    fun addList(items: ArrayList<Data>) {
        list.addAll(items)
        notifyDataSetChanged()
    }

    fun clear() {
        list.clear()
        notifyDataSetChanged()
    }
}
