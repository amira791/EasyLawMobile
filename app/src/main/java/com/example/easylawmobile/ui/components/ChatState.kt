package com.example.easylawmobile.ui.components

import android.graphics.Bitmap
import com.example.easylawmobile.data.models.Chat


data class ChatState (
    val chatList: MutableList<Chat> = mutableListOf(),
    val prompt: String = "",
    val bitmap: Bitmap? = null
)