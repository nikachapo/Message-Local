package com.epam.messagelocal.ui.search

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.epam.messagelocal.R
import com.epam.messagelocal.model.User

class UsersAdapter(private val itemClick: (User) -> Unit) :
    RecyclerView.Adapter<UsersAdapter.UserViewHolder>() {

    private val users = mutableListOf<User>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        return UserViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_user, parent, false)
        )
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        holder.bindUser(users[position])
    }

    override fun getItemCount() = users.size

    fun submitItems(users: MutableList<User>) {
        this.users.clear()
        this.users.addAll(users)
        notifyDataSetChanged()
    }

    inner class UserViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bindUser(user: User) {
            itemView.setOnClickListener {
                itemClick(user)
            }
            Glide.with(itemView)
                .load(user.imgUrl)
                .into(itemView.findViewById<AppCompatImageView>(R.id.itemUserImage))

            itemView.findViewById<AppCompatTextView>(R.id.itemUserName).text = user.username
        }
    }
}