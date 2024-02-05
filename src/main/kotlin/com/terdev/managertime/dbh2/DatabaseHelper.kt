package com.terdev.managertime.dbh2

import com.j256.ormlite.jdbc.JdbcPooledConnectionSource
import com.j256.ormlite.support.ConnectionSource
import com.j256.ormlite.table.TableUtils
import com.terdev.managertime.dbh2.system.impl.DbInfoDaoImpl
import com.terdev.managertime.dbh2.system.entity.DbInfo
import org.springframework.beans.factory.annotation.Value

class DatabaseHelper private constructor() {

    companion object {

        @Value("\${spring.datasource.username}")
        private val username: String = "sa"

        @Value("\${spring.datasource.password}")
        private val password: String = "111"

        @Value("\${spring.datasource.url}")
        private val url: String = "jdbc:h2:file:./data/testdb;AUTO_SERVER=TRUE"

        private var connectionSource: JdbcPooledConnectionSource? = null

        private fun instance(): ConnectionSource {
            if (connectionSource == null || !connectionSource?.isOpen("")!!) {
                connectionSource = JdbcPooledConnectionSource(url, username, password)
                //truncateAll()
                when (DbInfoDaoImpl(connectionSource).lastVersion()) {
                    0L -> update1()
                    else -> println("---действий по изменению бд не требуется---")
                }
            }
            return connectionSource!!
        }

        fun close() {
            connectionSource?.close()
        }

        private fun update1() {
            TableUtils.createTableIfNotExists(connectionSource, DbInfo::class.java)
            DbInfoDaoImpl(connectionSource).save(1)
            println("---обновление бд до v1---")
        }


        private fun truncateAll() {
            TableUtils.dropTable(DbInfoDaoImpl(connectionSource), true)
            println("---бд очищена---")
        }
    }
}