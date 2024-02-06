package com.terdev.managertime.jpa.tm.entity

import java.util.*
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.SequenceGenerator
import javax.persistence.Table

@Entity
@Table(name = "TM_ACTION")
class ActionEntity {

    companion object {
        const val generator = "SQ_TM_ACTION_ID"

        const val ACTION_ID: String = "ACTION_ID"
        const val DAY_ID: String = "DAY_ID"
        const val TYPE: String = "TYPE_ACTION"
        const val DATE: String = "DATE_ACTION"
    }

    @Id
    @Column(name = ACTION_ID, nullable = false)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "generator_actionId")
    @SequenceGenerator(name = "generator_actionId", sequenceName = generator, allocationSize = 1)
    var id: Long = 1

    @Column(name = DAY_ID, nullable = false)
    var dayId: Long = 1

    @Column(name = TYPE, nullable = false)
    var type: String = ""

    @Column(name = DATE, nullable = false)
    var date: Date = Date()

}