package com.terdev.managertime

enum class CommandName(val text: String, val description: String) {
    START("start", "запуск"),
    SUM("sum", "Сумма нескольких чисел через пробелы"),
    BUTTONS("buttons", "Отображение кнопок"),
    QUIZ("quiz", "Пример опросника"),
    HELP("help", "Список команд")
}