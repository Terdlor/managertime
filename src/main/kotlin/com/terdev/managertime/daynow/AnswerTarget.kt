package com.terdev.managertime.daynow

enum class AnswerTarget(val text: String, val desc: String) {
    IN("in", "Приход"),
    OUT("out", "Уход"),
    UPDATE("update", "Обновить")
}