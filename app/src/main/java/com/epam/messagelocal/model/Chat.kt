package com.epam.messagelocal.model

data class Chat(val id: String, val sender: User, val receiver: User) {

    val lastMessage: Message?
        get() = if (messages.isEmpty()){
            null
        } else {
            messages.last()
        }

    val messages = mutableListOf<Message>()

    fun sendMessage(messageText: String) {
        messages.add(Message(System.currentTimeMillis(), messageText, sender.id, receiver.id))
    }

    fun deleteMessage(message: Message) {
        messages.remove(message)
    }

}