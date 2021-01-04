package com.epam.messagelocal

import com.epam.messagelocal.model.Chat
import com.epam.messagelocal.model.Message
import com.epam.messagelocal.model.User

object UserManager {

    var currentUser: User? = null

    val testUser1 = User(
        "111", "testEmail2@gmail.com", "testUser1",
        "https://icons-for-free.com/iconfiles/png/512/business+costume+male+man+office+user+icon-1320196264882354682.png"
    )
    val testUser2 = User(
        "222", "testEmail2@gmail.com", "testUser1",
        "https://icons-for-free.com/iconfiles/png/512/business+costume+male+man+office+user+icon-1320196264882354682.png"
    )

    val testUser3 = User(
        "333", "testEmail3@gmail.com", "testUser3",
        "https://icons-for-free.com/iconfiles/png/512/business+costume+male+man+office+user+icon-1320196264882354682.png"
    )
    val users = listOf(
        testUser1,
        testUser2,
        testUser3
    )

    lateinit var chats: MutableList<Chat>

    val messages = mutableListOf<Message>()

    fun initFakeData() {
        chats = mutableListOf()
        currentUser?.let {
            val fakeMessages = getFakeMessages(it)

            val chat = Chat(
                "1",
                it, testUser1
            ).apply { messages.addAll(fakeMessages) }

            val chat1 = Chat(
                "2",
                it, testUser2
            ).apply { messages.addAll(fakeMessages) }

            this.chats.add(chat)
            this.chats.add(chat1)

        }
    }

    private fun getFakeMessages(it: User): MutableList<Message> {
        return mutableListOf(
            Message(
                1111111111L,
                "message 1",
                "111",
                it.id
            ),
            Message(
                1111111111L,
                "message 2",
                "111",
                it.id
            ),
            Message(1111111111L, "message 3", "111", it.id)
        )
    }
}