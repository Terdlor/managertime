package com.terdev.managertime.model.utils

import com.terdev.managertime.daynow.AnswerTarget
import com.terdev.managertime.model.Action
import java.time.Duration
import java.time.format.DateTimeFormatter
import java.time.temporal.ChronoUnit
import org.springframework.util.CollectionUtils

fun createStringInfoAction(actions: List<Action>): String {
    val str = StringBuilder()
    str.append("Статистика за сегодня:\n")
    if (CollectionUtils.isEmpty(actions)) {
        str.append("\nДанных нет")
    }

    normalizationActions(actions)
    val total = completeTime(actions)

    val formatter = DateTimeFormatter.ofPattern("HH:mm:ss")

    for (action in actions.sortedBy { it.date }) {
        when (action.type) {
            AnswerTarget.IN.text -> str.append("\n${AnswerTarget.IN.desc} - ${action.date.format(formatter)} ${action.emoj}")
            AnswerTarget.OUT.text -> str.append("\n${AnswerTarget.OUT.desc} - ${action.date.format(formatter)} ${action.emoj}")
        }
    }

    str.append("\n\nИтого ")
    val duration = Duration.of(total, ChronoUnit.SECONDS)
    if (duration.toHours() > 0) {
        str.append("${duration.toHours()} часов ")
    }
    if (duration.toMinutes() > 0) {
        str.append("${duration.toMinutes() - duration.toHours() * 60} минут ")
    }
    str.append("${duration.toSeconds() - duration.toMinutes() * 60} секунд")

    return str.toString().replace("_", "\\_")
        .replace("*", "\\*")
        .replace("[", "\\[")
        .replace("`", "\\`")
}