package com.terdev.managertime.daynow

import com.terdev.managertime.common.CallbackHandler
import com.terdev.managertime.common.HandlerName
import com.terdev.managertime.common.createMessage
import org.springframework.stereotype.Component
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText
import org.telegram.telegrambots.meta.api.objects.CallbackQuery
import org.telegram.telegrambots.meta.bots.AbsSender
import java.util.Date

@Component
class DayNowHandler : CallbackHandler {

    override val name: HandlerName = HandlerName.DAY_NOW

    override fun processCallbackData(
        absSender: AbsSender,
        callbackQuery: CallbackQuery,
        arguments: List<String>
    ) {

        val chatId = callbackQuery.message.chatId.toString()

        EditMessageText(
            chatId,
            callbackQuery.message.messageId,
            callbackQuery.inlineMessageId,
            "новый текст",
            null,
            false,
            callbackQuery.message.replyMarkup,
            emptyList()

        )

        absSender.execute(
            EditMessageText(
                chatId,
                callbackQuery.message.messageId,
                callbackQuery.inlineMessageId,
                "Сейчас ${Date()}",
                null,
                false,
                callbackQuery.message.replyMarkup,
                emptyList()
            )
        )
        when (arguments.first()) {
            ANSWER_TARGET.IN.text -> {
                absSender.execute(createMessage(chatId, "Добавлен приход"))
            }

            ANSWER_TARGET.OUT.text -> {
                absSender.execute(createMessage(chatId, "Добавлен уход"))
            }
        }
    }
}