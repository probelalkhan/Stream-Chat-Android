package io.getstream.streamchat1.ui.auth

import io.getstream.chat.android.client.token.TokenProvider
import io.getstream.streamchat1.data.StreamTokenApi
import kotlinx.coroutines.runBlocking

class StreamTokenProvider(
    private val api: StreamTokenApi
) {
    fun getTokenProvider(userId: String): TokenProvider{
        return object: TokenProvider{
            override fun loadToken(): String = runBlocking {
                api.getToken(userId).token
            }
        }
    }
}