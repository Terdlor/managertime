package com.terdev.managertime.jpa.tm.repository

import com.terdev.managertime.jpa.tm.entity.DayEntity
import org.springframework.data.jpa.repository.JpaRepository

interface DayRepository : JpaRepository<DayEntity, Long> {

    fun findByUserIdAndDayAndMonthAndYear(userId: Long, day: Int, month: Int, year: Int): DayEntity?

}