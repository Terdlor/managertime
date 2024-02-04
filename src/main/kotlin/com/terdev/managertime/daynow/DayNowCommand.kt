package com.terdev.managertime.daynow

import com.terdev.managertime.common.CommandName
import com.terdev.managertime.createMessageWithInlineButtons
import com.terdev.managertime.common.HandlerName
import org.springframework.stereotype.Component
import org.telegram.telegrambots.extensions.bots.commandbot.commands.BotCommand
import org.telegram.telegrambots.meta.api.objects.Chat
import org.telegram.telegrambots.meta.api.objects.User
import org.telegram.telegrambots.meta.bots.AbsSender

@Component
class DayNowCommand : BotCommand(CommandName.DAY_NOW.text, "") {

    override fun execute(absSender: AbsSender, user: User, chat: Chat, arguments: Array<out String>) {
        val callback = HandlerName.DAY_NOW.text
        absSender.execute(
            createMessageWithInlineButtons(
                chat.id.toString(),
                "Текущий день",//TODO инфа текущего дня
                listOf(
                    listOf("$callback|${ANSWER_TARGET.IN.text}" to "Приход", "$callback|${ANSWER_TARGET.OUT.text}" to "Уход"),
                )
            )
        )
    }
}