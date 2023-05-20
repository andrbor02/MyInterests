package com.example.feature_chat_ui.impl.presentation.utils

import com.example.feature_chat.impl.domain.model.DateModel
import com.example.feature_chat.impl.domain.model.MessageModel
import com.example.feature_chat.impl.domain.model.TopicModel
import com.example.feature_chat_ui.impl.presentation.model.topic.TopicDelegateItem
import com.example.feature_chat_ui.impl.presentation.recycler.delegates.DelegateItem
import com.example.fintechrecyclerview.delegates.date.DateDelegateItem
import com.example.fintechrecyclerview.delegates.expense.MessageDelegateItem
import javax.inject.Inject

interface ChatCombiner {
    operator fun invoke(messagesList: List<MessageModel>): List<DelegateItem>
}

internal class ChatCombinerImpl @Inject constructor() : ChatCombiner {
    override fun invoke(messagesList: List<MessageModel>): List<DelegateItem> {
        if (messagesList.isEmpty()) {
            return emptyList()
        }
        val delegateItemList: MutableList<DelegateItem> = mutableListOf()

        // Add first message
        val firstMessage = messagesList[0]
        var currentTopic = firstMessage.topic
        var currentDate = firstMessage.date
        delegateItemList.add(
            TopicDelegateItem(
                id = FIRST_TOPIC_ID,
                value = TopicModel(currentTopic)
            )
        )
        delegateItemList.add(
            DateDelegateItem(
                id = FIRST_DATE_ID,
                value = DateModel(id = FIRST_DATE_ID, convertNumericMonthToWord(currentDate))
            )
        )
        delegateItemList.add(MessageDelegateItem(id = firstMessage.id, value = firstMessage))

        // Add the rest
        for (i in 1 until messagesList.size) {
            val message = messagesList[i]

            if (message.topic != currentTopic) {
                currentTopic = message.topic
                delegateItemList.add(
                    TopicDelegateItem(
                        id = i,
                        value = TopicModel(currentTopic)
                    )
                )
            }

            if (message.date != currentDate) {
                currentDate = message.date
                delegateItemList.add(
                    DateDelegateItem(
                        id = i,
                        value = DateModel(id = i, convertNumericMonthToWord(currentDate))
                    )
                )
            }
            delegateItemList.add(MessageDelegateItem(id = message.id, value = message))
        }

        return delegateItemList
    }

    private fun convertNumericMonthToWord(numericDate: String): String {
        val numericDay = numericDate.split(" ").first()
        val numericMonth = numericDate.split(" ").last()
        val wordMonth = datesMap[numericMonth] ?: numericDate
        return "$numericDay $wordMonth"
    }

    private companion object {
        private const val FIRST_DATE_ID = 0
        private const val FIRST_TOPIC_ID = 0

        private val datesMap = mapOf(
            "01" to "Янв",
            "02" to "Фев",
            "03" to "Марта",
            "04" to "Апр",
            "05" to "Мая",
            "06" to "Июня",
            "07" to "Июля",
            "08" to "Авг",
            "09" to "Сент",
            "10" to "Окт",
            "11" to "Нояб",
            "12" to "Дек",
        )
    }

}