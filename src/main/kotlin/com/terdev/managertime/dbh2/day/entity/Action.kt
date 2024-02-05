package com.terdev.managertime.dbh2.day.entity

import com.j256.ormlite.field.DatabaseField
import com.j256.ormlite.table.DatabaseTable
import java.util.*

@DatabaseTable(tableName = "DB_INFO")
class Action {

    @DatabaseField(generatedId = true, columnName = "ID")
    var id: Int = 1

    @DatabaseField(columnName = "ID_DAY")
    var idDay: Int = 0

    @DatabaseField(columnName = "TYPE")
    var type: String = ""

    @DatabaseField(columnName = "DATE")
    var date: Date = Date()
}