package com.terdev.managertime.model

import java.util.*

data class Day (var userId: Long, var day: Int, var month: Int, var year: Int, var totalSecond: Int)
data class Action(var dayId: Long, var type: String, var date: Date)