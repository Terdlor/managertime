package com.terdev.managertime.jpa.tm.service

import com.terdev.managertime.jpa.tm.entity.ActionEntity
import com.terdev.managertime.jpa.tm.entity.DayEntity
import com.terdev.managertime.jpa.tm.repository.ActionRepository
import com.terdev.managertime.jpa.tm.repository.DayRepository
import com.terdev.managertime.model.Action
import com.terdev.managertime.model.Day
import java.time.LocalDateTime
import java.util.stream.Collectors
import org.mapstruct.Mapper
import org.mapstruct.factory.Mappers
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
class ActionService {

    @Autowired
    lateinit var actionRepository: ActionRepository

    @Autowired
    lateinit var dayRepository: DayRepository


    fun getActionByDay(userId: Long, day: Int, month: Int, year: Int): List<Action> {
        val dayEntity = dayRepository.findByUserIdAndDayAndMonthAndYear(userId, day, month, year)
        if (dayEntity == null) {
            return emptyList()
        }
        return actionRepository.findByDayId(dayEntity.id).stream()
            .map { ActionMapper.INSTANCE.toModel(it) }
            .collect(Collectors.toList())
    }

    fun saveAction(userId: Long, day: Int, month: Int, year: Int, date: LocalDateTime, type: String) {
        val dayEntity = dayRepository.findByUserIdAndDayAndMonthAndYear(userId, day, month, year) ?: dayRepository.save(
            DayMapper.INSTANCE.toEntity(Day(userId, day, month, year, 0))
        )
        actionRepository.save(ActionMapper.INSTANCE.toEntity(Action(dayEntity.id, type, date)))
    }
}

@Mapper
interface ActionMapper {
    companion object {
        val INSTANCE: ActionMapper = Mappers.getMapper(ActionMapper::class.java)
    }

    fun toModel(actionEntity: ActionEntity): Action
    fun toEntity(action: Action): ActionEntity
}

@Mapper
interface DayMapper {
    companion object {
        val INSTANCE: DayMapper = Mappers.getMapper(DayMapper::class.java)
    }

    fun toModel(dayEntity: DayEntity): Day
    fun toEntity(day: Day): DayEntity
}