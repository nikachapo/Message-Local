package com.epam.messagelocal.ui.messages

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatTextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.epam.messagelocal.R
import com.epam.messagelocal.UserManager
import com.epam.messagelocal.model.Message

class MessagesAdapter : RecyclerView.Adapter<MessagesAdapter.MessageViewHolder>() {

    private val messages = mutableListOf<Message>()

    fun submitItems(messages: MutableList<Message>) {
        this.messages.clear()
        this.messages.addAll(messages)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MessageViewHolder {
        return MessageViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_message, parent, false))
    }

    override fun onBindViewHolder(holder: MessageViewHolder, position: Int) {
        holder.bindMessage(messages[position])
    }

    override fun getItemCount() = messages.size

    inner class MessageViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bindMessage(message: Message) {
            val messageTV = itemView.findViewById<AppCompatTextView>(R.id.messageTV)
            if (UserManager.currentUser?.id == message.senderId) {
                messageTV.background = ContextCompat.getDrawable(
                    itemView.context,
                    R.drawable.bg_sender
                )
                messageTV.layoutParams =
                    (messageTV.layoutParams as ConstraintLayout.LayoutParams).apply {
                        horizontalBias = 1f
                    }
            } else {
                messageTV.background = ContextCompat.getDrawable(
                    itemView.context,
                    R.drawable.bg_receiver
                )
                messageTV.layoutParams =
                    (messageTV.layoutParams as ConstraintLayout.LayoutParams).apply {
                        horizontalBias = 0f
                    }
            }
            messageTV.text = message.text
        }
    }
}