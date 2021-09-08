package com.alivecor.app.util

import org.junit.Assert
import org.junit.Test
import java.time.Period
import java.util.*

class DateExtensionsKtTest {

    /**
     * Checking for a month age
     * example: DOB: 08-Aug-2021 = 08-07-2021
     * Suggestion: month number as to be reduced by 1, it because
     * doing Manual Calculation, We need to keep in mind about it.l6
     * @see java.util.Calendar.JANUARY - Months starts from 0 to 11
     */
    @Test
    fun differenceInTimeFrameFromNow() {
        val calendar = Calendar.getInstance()
        calendar.set(2020,8,8)

        val actualValue = differenceInTimeFrameFromNow(calendar.timeInMillis)
        println(actualValue)
        val expectedValue = Triple(1,0,0)
        println(expectedValue)
        Assert.assertEquals(actualValue, expectedValue)
    }

    /**
     * Checking for a month age
     * example: DOB: 08-Aug-2021 = 08-08-2021
     * @see java.time.Month.JANUARY - Months starts from 1 to 12
     */
    @Test
    fun differenceInPeriodFromNow() {
        val calendar = Calendar.getInstance()
        calendar.set(2020,8,8)

        val actualValue = differenceInPeriodFromNow(calendar.timeInMillis)
        println(actualValue)
        val expectedValue = Period.of(1,0,0)
        println(expectedValue)
        Assert.assertEquals(actualValue, expectedValue)
    }
}