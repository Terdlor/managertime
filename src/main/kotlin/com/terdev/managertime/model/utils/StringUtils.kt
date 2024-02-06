package com.terdev.managertime.model.utils

import com.terdev.managertime.daynow.ANSWER_TARGET
import com.terdev.managertime.model.Action
import java.time.temporal.ChronoUnit
import org.springframework.util.CollectionUtils

fun createStringInfoAction(actions: List<Action>): String {
    val str = StringBuilder()
    str.append("Статистика за сегодня:\n")
    if (CollectionUtils.isEmpty(actions)) {
        str.append("\nДанных нет")
    }

    for (action in actions) {
        when(action.type){
            ANSWER_TARGET.IN.text -> str.append("\n${ANSWER_TARGET.IN.desc} - ${action.date}")
            ANSWER_TARGET.OUT.text -> str.append("\n${ANSWER_TARGET.OUT.desc} - ${action.date}")
        }
    }
    return str.toString().replace("_", "\\_")
        .replace("*", "\\*")
        .replace("[", "\\[")
        .replace("`", "\\`")
}

fun calculatePeriod(actions: List<Action>) {
    val sortedAction = actions.sortedBy { it.date }
}