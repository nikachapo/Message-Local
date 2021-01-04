package com.epam.messagelocal.ui.messages

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.epam.messagelocal.UserManager
import com.epam.messagelocal.model.Chat
import com.epam.messagelocal.model.Message
import com.epam.messagelocal.model.User

class MessagesViewModel(application: Application) : AndroidViewModel(application) {

    private val _user2 = MutableLiveData<User>()
    val user2: LiveData<User> = _user2

    private val _messages = MutableLiveData<List<Message>>()
    val messages: LiveData<List<Message>> = _messages

    private var chat: Chat? = null

    fun loadMessages(chatId: String) {
        chat = UserManager.chats.first { it.id == chatId }
        _user2.value = chat?.receiver
        val messages = chat?.messages
        this._messages.value = messages
    }

    fun sendMessage(message: String) {
        loadMessages(chat!!.id)
        chat?.sendMessage(message)
    }
}