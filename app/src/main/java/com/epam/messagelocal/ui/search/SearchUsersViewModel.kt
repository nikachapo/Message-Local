package com.epam.messagelocal.ui.search

import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.epam.messagelocal.UserManager
import com.epam.messagelocal.model.Chat
import com.epam.messagelocal.model.User

class SearchUsersViewModel : ViewModel() {

    private val _searchQuery = MutableLiveData<String>()

    val searchedUsers = MediatorLiveData<List<User>>().apply {
        addSource(_searchQuery) { query ->
                value = UserManager.users.filter { it.username?.contains(query) == true }
        }
    }

    fun search(q: String) {
        _searchQuery.value = q
    }

    fun getChatIdWith(userToChat: User): String {
        var chatId: String? = null
        UserManager.chats.forEach {
            if (it.receiver.id == userToChat.id) {
                chatId = it.id
                return@forEach
            }
        }
        return if (chatId == null) {
            createNewChat(userToChat)
        } else chatId!!
    }

    private fun createNewChat(userToChat: User): String {
        val newChat = Chat(
            (1..100).random().toString(),
            UserManager.currentUser!!,
            userToChat
        )
        UserManager.chats.add(
            newChat
        )
        return newChat.id
    }
}