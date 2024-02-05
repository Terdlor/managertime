package com.terdev.managertime.dbh2.day

import com.j256.ormlite.dao.Dao
import com.terdev.managertime.dbh2.day.entity.Day
import java.sql.SQLException

interface DayDao : Dao<Day, Long> {

    @Throws(SQLException::class)
    fun get(dey: Int, month: Int, year: Int): Day

    @Throws(SQLException::class)
    fun updateTotal(dey: Int, month: Int, year: Int, total: Long): Long
}