package com.terdev.managertime.daynow

import com.terdev.managertime.common.CommandName
import com.terdev.managertime.common.HandlerName
import com.terdev.managertime.common.createMessageWithInlineButtons
import com.terdev.managertime.jpa.tm.service.ActionService
import com.terdev.managertime.model.utils.createStringInfoAction
import java.time.LocalDate
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import org.telegram.telegrambots.extensions.bots.commandbot.commands.BotCommand
import org.telegram.telegrambots.meta.api.objects.Chat
import org.telegram.telegrambots.meta.api.objects.User
import org.telegram.telegrambots.meta.bots.AbsSender

@Component
class DayNowCommand : BotCommand(CommandName.DAY_NOW.text, "") {

    @Autowired
    lateinit var actionService: ActionService

    override fun execute(absSender: AbsSender, user: User, chat: Chat, arguments: Array<out String>) {

        val localDate = LocalDate.now()
        val actions = actionService.getActionByDay(user.id, localDate.dayOfMonth, localDate.monthValue, localDate.year)

        val callback = HandlerName.DAY_NOW.text
        absSender.execute(
            createMessageWithInlineButtons(
                chat.id.toString(),
                createStringInfoAction(actions),
                listOf(
                    listOf(
                        "$callback|${ANSWER_TARGET.IN.text}" to ANSWER_TARGET.IN.desc,
                        "$callback|${ANSWER_TARGET.OUT.text}" to ANSWER_TARGET.OUT.desc
                    ),
                )
            )
        )
    }
}