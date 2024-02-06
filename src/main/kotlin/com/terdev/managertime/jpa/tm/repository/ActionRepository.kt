package com.terdev.managertime.jpa.tm.repository

import com.terdev.managertime.jpa.tm.entity.ActionEntity
import org.springframework.data.jpa.repository.JpaRepository

interface ActionRepository : JpaRepository<ActionEntity, Long> {

    fun findByDayId(dayId: Long): List<ActionEntity>

}