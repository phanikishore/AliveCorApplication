package com.alivecor.app.util

import android.annotation.SuppressLint
import android.os.Build
import androidx.annotation.RequiresApi
import java.text.SimpleDateFormat
import java.time.Instant
import java.time.LocalDate
import java.time.Period
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import java.util.*

/**
 * DateExtensions used for computing all the date related operations.
 */

const val DOB_FORMAT = "dd-MMM-yyyy"

@SuppressLint("SimpleDateFormat")
fun getSimpleDateFormat(format: String): SimpleDateFormat = SimpleDateFormat(format)

fun Long.toFormattedString(format: String): String = getSimpleDateFormat(format).format(Date(this))

fun String.toDateLong(format: String): Long? {
    return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
        val localDate = LocalDate.parse(this, DateTimeFormatter.ofPattern(format))
        val calender = Calendar.getInstance()
        calender.set(localDate.year, localDate.monthValue, localDate.dayOfMonth)
        calender.timeInMillis
    } else {
        val date = getSimpleDateFormat(format).parse(this)
        date?.time
    }

}

//Reference: https://howtodoinjava.com/java/calculate-age-from-date-of-birth/
fun Long.getAge(): Triple<Int, Int, Int> {
    return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
        differenceInPeriodFromNow(this)?.let { period ->
            Triple(period.years, period.months, period.days)
        } ?: kotlin.run {
            differenceInTimeFrameFromNow(this)
        }
    } else {
        differenceInTimeFrameFromNow(this)
    }
}

/**
 * Runs Below APIs of Build.VERSION_CODES.O
 */
fun differenceInTimeFrameFromNow(timeInMillis: Long): Triple<Int, Int, Int> {
    val dob = Calendar.getInstance()
    dob.timeInMillis = timeInMillis
    val today = Calendar.getInstance()

    //Difference in Years
    var years = today[Calendar.YEAR] - dob[Calendar.YEAR]
    val currentMonth = today[Calendar.MONTH] + 1
    val dobMonth = dob[Calendar.MONTH] + 1

    //Difference in Months
    var months = currentMonth - dobMonth
    if (months < 0) {
        years = years.minus(1)
        months = 12 - dobMonth + currentMonth
        if (today[Calendar.DATE] < dob[Calendar.DATE])
            months = months.minus(1)
    } else if (months == 0 && (today[Calendar.DATE] < dob[Calendar.DATE])) {
        years = years.minus(1)
        months = 11
    }

    //Difference in Days
    var days = 0
    when {
        today[Calendar.DATE] > dob[Calendar.DATE] -> {
            days = today[Calendar.DATE] - dob[Calendar.DATE]
        }
        today[Calendar.DATE] < dob[Calendar.DATE] -> {
            val day = today[Calendar.DAY_OF_MONTH]
            today.set(Calendar.MONTH, -1)
            days = today.getActualMaximum(Calendar.DAY_OF_MONTH) - dob[Calendar.DAY_OF_MONTH] + day
        }
        months == 12 -> {
            years = years.plus(1)
            months = 0
        }
    }
    return Triple(years, months, days)
}

@RequiresApi(Build.VERSION_CODES.O)
fun differenceInPeriodFromNow(timeInMillis: Long): Period? {
    val dobLocalDate =
        Instant.ofEpochMilli(timeInMillis).atZone(ZoneId.systemDefault()).toLocalDate()
    return Period.between(dobLocalDate, LocalDate.now())
}