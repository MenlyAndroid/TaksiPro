package ru.taksi.pro.android.mvvm.helpers

import android.annotation.SuppressLint
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

object TextFormatHelper {
    fun dateFormat(string: String): String {
        var clean = string.replace("[^\\d.]|\\.".toRegex(), "")
        if (clean.length in 3..4) {
            return String.format(
                "%s/%s", clean.substring(0, 2),
                clean.substring(2, clean.length)
            )
        } else if (clean.length > 4) {
            return String.format(
                "%s/%s/%s", clean.substring(0, 2),
                clean.substring(2, 4),
                clean.substring(4, clean.length)
            )
        } else {
            return clean
        }
    }

    fun serialEndNumberFormat(string: String): String {
        var clean = string.replace(" ", "")
        if (clean.length > 4) {
            return String.format(
                "%s %s", clean.substring(0, 4),
                clean.substring(4, clean.length)
            )
        }
        return clean
    }

//    fun createPassportData(
//        address: String,
//        date: String,
//        number: String,
//        series: String
//    ): Passport {
//        return Passport(address, date, number, series)
//    }

    @SuppressLint("SimpleDateFormat")
    fun createDateForApi(date: String?): String {
        date?.let {
            return try {
                val clean = date?.replace("[^\\d.]|\\.".toRegex(), "")
                val format = SimpleDateFormat("ddMMyyyy")
                val realDate = format.parse(clean)
                val newFormat = SimpleDateFormat("yyyy-MM-dd")
                newFormat.format(realDate)
            } catch (e: ParseException) {
                e.printStackTrace()
                "2000-01-01"
            }
        }?: return "2000-01-01"
    }

    fun createStringAddress(
        city: String?,
        district: String?,
        street: String?,
        home: String?,
        apartments: String?
    ): String {
        val sb = StringBuilder()
        city?.let {
            sb.append("г. $it")
        }
        district?.let {
            sb.append(", $it район")
        }
        street?.let {
            sb.append(", ул. $it")
        }
        home?.let {
            sb.append(", д. $it")
        }
        apartments?.let {
            sb.append(", кв. $it")
        }
        return sb.toString()
    }

    fun getSeriesFromPassportData(string: String?): String {
        val clean = string?.replace(" ", "")
        return clean?.substring(0, 4) ?: ""
    }

    fun getNumberFromPassportData(string: String?): String {
        val clean = string?.replace(" ", "")
        return clean?.substring(4, clean.length) ?: ""
    }
}