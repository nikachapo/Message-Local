package com.epam.messagelocal.model

data class Message(val date: Long, val text: String, val senderId: String, val receiverId: String)