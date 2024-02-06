package com.terdev.managertime.jpa.tm.entity

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.SequenceGenerator
import javax.persistence.Table

@Entity
@Table(name = "TM_DAY")
class DayEntity {

    companion object {
        const val generator = "SQ_TM_DAY_ID"

        const val DAY_ID: String = "DAY_ID"
        const val USER_ID: String = "USER_ID"
        const val DAY: String = "DAY_NUM"
        const val MONTH: String = "MONTH_NUM"
        const val YEAR: String = "YEAR_NUM"
        const val TOTAL_SECOND: String = "TOTAL_SECOND"
    }

    @Id
    @Column(name = DAY_ID, nullable = false)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "generator_dayId")
    @SequenceGenerator(name = "generator_dayId", sequenceName = generator, allocationSize = 1)
    var id: Long = 1

    @Column(name = USER_ID, nullable = false)
    var userId: Long = 1

    @Column(name = DAY, nullable = false)
    var day: Int = 1

    @Column(name = MONTH, nullable = false)
    var month: Int = 1

    @Column(name = YEAR, nullable = false)
    var year: Int = 1

    @Column(name = TOTAL_SECOND, nullable = false)
    var totalSecond: Int = 1

}