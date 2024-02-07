package com.terdev.managertime.daynow

import com.terdev.managertime.common.CallbackHandler
import com.terdev.managertime.common.HandlerName
import com.terdev.managertime.jpa.tm.service.ActionService
import com.terdev.managertime.model.utils.createStringInfoAction
import java.time.Instant
import java.time.LocalDateTime
import java.time.ZoneId
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import org.telegram.telegrambots.meta.api.methods.AnswerCallbackQuery
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText
import org.telegram.telegrambots.meta.api.objects.CallbackQuery
import org.telegram.telegrambots.meta.bots.AbsSender

@Component
class DayNowHandler : CallbackHandler {

    override val name: HandlerName = HandlerName.DAY_NOW

    @Autowired
    lateinit var actionService: ActionService

    override fun processCallbackData(
        absSender: AbsSender,
        callbackQuery: CallbackQuery,
        arguments: List<String>
    ) {
        val localDate = Instant.ofEpochMilli(callbackQuery.message.date * 1000L)
            .atZone(ZoneId.systemDefault())
            .toLocalDate()

        val chatId = callbackQuery.message.chatId.toString()

        if (AnswerTarget.UPDATE.text != arguments.first())
            if (LocalDateTime.now().toLocalDate().isEqual(localDate)){
                actionService.saveAction(
                    callbackQuery.from.id,
                    localDate.dayOfMonth,
                    localDate.monthValue,
                    localDate.year,
                    LocalDateTime.now(),
                    arguments.first()
                )
            } else {
                absSender.execute(
                    AnswerCallbackQuery(callbackQuery.id, "Это не сегодня", true, null, 0)
                )
                return
            }

        val actions = actionService.getActionByDay(
            callbackQuery.from.id,
            localDate.dayOfMonth,
            localDate.monthValue,
            localDate.year
        )

        try {
            val newString = createStringInfoAction(actions, localDate)
            if (newString.equals(callbackQuery.message.text)) {
                absSender.execute(
                    EditMessageText(
                        chatId,
                        callbackQuery.message.messageId,
                        callbackQuery.inlineMessageId,
                        newString,
                        "Markdown",
                        false,
                        callbackQuery.message.replyMarkup,
                        emptyList()
                    )
                )
            }
        } finally {
            absSender.execute(AnswerCallbackQuery(callbackQuery.id))
        }
    }
}