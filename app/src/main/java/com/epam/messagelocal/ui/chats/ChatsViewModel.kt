package com.epam.messagelocal.ui.chats

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.epam.messagelocal.UserManager
import com.epam.messagelocal.model.Chat

class ChatsViewModel : ViewModel() {

    private val _chats = MutableLiveData<List<Chat>>()

    val chats: LiveData<List<Chat>> = _chats

    init {
        UserManager.initFakeData()
        _chats.value = UserManager.chats
    }
}