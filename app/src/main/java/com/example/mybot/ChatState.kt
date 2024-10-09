package com.example.mybot

import android.graphics.Bitmap
import com.example.mybot.data.Chat

data class ChatState(
    val chatList : MutableList<Chat> = mutableListOf(),
    val prompt : String = "",
    val bitmap : Bitmap? = null
)
