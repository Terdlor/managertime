package com.terdev.managertime.dbh2.day.impl

import com.j256.ormlite.dao.BaseDaoImpl
import com.j256.ormlite.support.ConnectionSource
import com.terdev.managertime.dbh2.day.DayDao
import com.terdev.managertime.dbh2.day.entity.Day
import java.sql.SQLException

class DayDaoImpl(connectionSource: ConnectionSource?) :
    BaseDaoImpl<Day, Long>(connectionSource, Day::class.java), DayDao {

    @Throws(SQLException::class)
    override fun get(dey: Int, month: Int, year: Int): Day {
        return try {
            super.queryRawValue("select max(version) from DB_INFO")
        } catch (ex: SQLException) {
            0
        }
    }

    @Throws(SQLException::class)
    override fun updateTotal(dey: Int, month: Int, year: Int, total: Long): Long {
        TODO("Not yet implemented")
    }
}