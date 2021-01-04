package com.epam.messagelocal.ui.chats

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.epam.messagelocal.R
import com.epam.messagelocal.model.Chat
import java.text.SimpleDateFormat
import java.util.*

class ChatsAdapter(private val itemClick: (Chat) -> Unit) :
    RecyclerView.Adapter<ChatsAdapter.ChatsViewHolder>() {

    private var chats = mutableListOf<Chat>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChatsViewHolder {
        return ChatsViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_chat, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ChatsViewHolder, position: Int) {
        holder.bindChat(chats[position])
    }

    override fun getItemCount() = chats.size

    fun submitList(it: List<Chat>?) {
        it?.toMutableList()?.run {
            chats.clear()
            chats.addAll(this)
            notifyDataSetChanged()
        }
    }

    inner class ChatsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bindChat(chat: Chat) {
            @SuppressLint("SimpleDateFormat")
            val sdf = SimpleDateFormat("dd/MM/yy hh:mm")

            itemView.setOnClickListener {
                itemClick(chat)
            }
            Glide.with(itemView)
                .load(chat.receiver.imgUrl)
                .into(itemView.findViewById<AppCompatImageView>(R.id.chatIV))

            itemView.findViewById<AppCompatTextView>(R.id.displayNameTV).text =
                chat.receiver.username
            itemView.findViewById<AppCompatTextView>(R.id.lastMessageTV).text =
                chat.lastMessage?.text
            chat.lastMessage?.date?.let {
                itemView.findViewById<AppCompatTextView>(R.id.lastMessageTimeTV).text =
                    sdf.format(Date(it))
            }
        }
    }
}