package com.terdev.managertime.example

import com.terdev.managertime.common.CommandName
import com.terdev.managertime.common.createMessage
import org.springframework.stereotype.Component
import org.telegram.telegrambots.extensions.bots.commandbot.commands.BotCommand
import org.telegram.telegrambots.meta.api.objects.Chat
import org.telegram.telegrambots.meta.api.objects.User
import org.telegram.telegrambots.meta.bots.AbsSender

@Component
class SumCommand : BotCommand(CommandName.SUM.text, "") {
    override fun execute(absSender: AbsSender, user: User, chat: Chat, arguments: Array<out String>) {
        val numbers = arguments.map { it.toInt() }
        val sum = numbers.sum()
        absSender.execute(
            createMessage(
                chat.id.toString(),
                numbers.joinToString(separator = " + ", postfix = " = $sum"),
            )
        )
    }
}