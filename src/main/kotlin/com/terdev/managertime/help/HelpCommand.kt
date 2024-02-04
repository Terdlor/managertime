package com.terdev.managertime.help

import com.terdev.managertime.common.CommandName
import com.terdev.managertime.common.createMessage
import org.springframework.stereotype.Component
import org.telegram.telegrambots.extensions.bots.commandbot.commands.BotCommand
import org.telegram.telegrambots.meta.api.objects.Chat
import org.telegram.telegrambots.meta.api.objects.User
import org.telegram.telegrambots.meta.bots.AbsSender

@Component
class HelpCommand : BotCommand(CommandName.HELP.text, "") {

    override fun execute(absSender: AbsSender, user: User, chat: Chat, arguments: Array<out String>) {
        absSender.execute(createMessage(chat.id.toString(), getMessageHelp()))
    }

    private fun getMessageHelp(): String {
        val str = StringBuilder()
        str.append("Команды бота:\n")
        for (com in CommandName.values()) {
            str.append("\n/${com.text} - ${com.description}")
        }
        return str.toString().replace("_", "\\_")
            .replace("*", "\\*")
            .replace("[", "\\[")
            .replace("`", "\\`")
    }
}