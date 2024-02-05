package com.terdev.managertime.dbh2.day.entity

import com.j256.ormlite.field.DatabaseField
import com.j256.ormlite.table.DatabaseTable

@DatabaseTable(tableName = "DB_DAY")
class Day {

    @DatabaseField(generatedId = true, columnName = "ID")
    var id: Int = 1

    @DatabaseField(columnName = "USER_ID")
    var userId: Int = 1

    @DatabaseField(columnName = "DAY")
    var day: Int = 1

    @DatabaseField(columnName = "MONTH")
    var month: Int = 1

    @DatabaseField(columnName = "YEAR")
    var year: Int = 1

    @DatabaseField(columnName = "TOTAL_SECOND")
    var totalSecond: Long = 0

}