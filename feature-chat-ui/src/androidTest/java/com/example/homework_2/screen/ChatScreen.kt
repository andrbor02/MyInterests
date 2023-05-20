package com.example.homework_2.screen

import android.view.View
import com.example.feature_chat_ui.R
import com.example.feature_chat_ui.impl.presentation.ui.ChatFragment
import com.kaspersky.kaspresso.screens.KScreen
import io.github.kakaocup.kakao.common.views.KView
import io.github.kakaocup.kakao.recycler.KRecyclerItem
import io.github.kakaocup.kakao.recycler.KRecyclerView
import org.hamcrest.Matcher

class ChatScreen : KScreen<ChatScreen>() {
    override val layoutId: Int = R.layout.fragment_chat
    override val viewClass: Class<*> = ChatFragment::class.java

    val chatList = KRecyclerView({ withId(R.id.chat_recycler) }, { itemType { ChatItem(it) } })

    class ChatItem(parent: Matcher<View>) : KRecyclerItem<ChatItem>(parent) {
        val message = KView { withId(R.id.message) }
    }
}