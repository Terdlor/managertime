package com.terdev.managertime.model.utils

import com.terdev.managertime.daynow.AnswerTarget
import com.terdev.managertime.model.Action
import java.time.Duration
import java.time.LocalDateTime
import java.time.LocalTime
import org.springframework.util.CollectionUtils

fun normalizationActions(actions: List<Action>) {
    var startMarker = true
    for (action in actions.sortedBy { it.date }) {
        if (startMarker && action.type == AnswerTarget.IN.text) {
            action.correct = true
            action.emoj = "\uD83D\uDCBC"
            startMarker = false
            continue
        }
        if (!startMarker && action.type == AnswerTarget.OUT.text) {
            action.correct = true
            action.emoj = "\uD83C\uDF92️"
            startMarker = true
            continue
        }
        action.correct = false
        action.emoj = "\uD83D\uDEB7"
    }
}

fun completeTime(actions: List<Action>): Long {
    if (CollectionUtils.isEmpty(actions)) {
        return 0
    }
    val startDayTime = LocalDateTime.now().with(LocalTime.MIN)

    var duration = 0L
    var lastAction = actions[0]

    for (action in actions.sortedBy { it.date }) {
        if (action.correct) {
            if (action.type == AnswerTarget.IN.text) {
                duration -= Duration.between(startDayTime, action.date).toSeconds()
            }
            if (action.type == AnswerTarget.OUT.text) {
                duration += Duration.between(startDayTime, action.date).toSeconds()
            }
            lastAction = action
        }
    }
    if (lastAction.type == AnswerTarget.IN.text) {
        duration += Duration.between(startDayTime, LocalDateTime.now()).toSeconds()
    }

    return duration
}