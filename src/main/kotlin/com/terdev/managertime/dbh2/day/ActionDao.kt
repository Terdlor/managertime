package com.terdev.managertime.dbh2.day

import com.j256.ormlite.dao.Dao
import com.terdev.managertime.dbh2.day.entity.Action
import com.terdev.managertime.dbh2.day.entity.Day
import java.sql.SQLException

interface ActionDao : Dao<Action, Long> {

    @Throws(SQLException::class)
    fun add(dey: Day, type: String)

    @Throws(SQLException::class)
    fun getAll(dey: Int, month: Int, year: Int): List<Action>
}