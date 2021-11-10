package io.getstream.streamchat1

import android.app.Application
import io.getstream.chat.android.client.ChatClient
import io.getstream.chat.android.livedata.ChatDomain

class StreamChatApp: Application() {
    override fun onCreate() {
        super.onCreate()
        ChatClient.Builder("api-key-here", applicationContext).build()
        ChatDomain.Builder(ChatClient.instance(), applicationContext).build()
    }
}