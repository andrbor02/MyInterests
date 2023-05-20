package com.example.homework_2

import androidx.core.os.bundleOf
import androidx.fragment.app.testing.FragmentScenario
import com.example.feature_chat_ui.impl.presentation.ui.ChatFragment
import com.example.homework_2.screen.ChatScreen
import com.kaspersky.kaspresso.testcases.api.testcase.TestCase
import org.junit.Test

class ChatScreenTest : TestCase() {
    @Test
    fun showTasksForFirstProject_ByDefault() = run {
        FragmentScenario.launchInContainer(
            ChatFragment::class.java, bundleOf(
                "streamId" to 379888,
                "streamName" to "general"
            )
        )

        val chatScreen = ChatScreen()

        step("отображается чат") {
            chatScreen.chatList.isDisplayed()
            chatScreen.chatList.getSize() > 0
            flakySafely(5000) {}
        }
    }
}