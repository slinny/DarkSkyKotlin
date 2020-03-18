package com.example.android.darkskykotlin.util

import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*


public fun getDayOfWeek(dateInt: Int?): String? {
    var mDays: HashMap<Int, String> = HashMap<Int, String>()
    mDays.put(2, "MON")
    mDays.put(3, "TUES")
    mDays.put(4, "WED")
    mDays.put(5, "THU")
    mDays.put(6, "FRI")
    mDays.put(7, "SAT")
    mDays.put(1, "SUN")
    val nowTimeLong = (dateInt!!.toLong()) * 1000
    val format = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.US)

    try {
        val now: Date = format.parse(format.format(nowTimeLong))
        val calendar = Calendar.getInstance()
        calendar.time = now
        return mDays[calendar.get(Calendar.DAY_OF_WEEK)]
    } catch (e: ParseException) {

    }

    return mDays[Calendar.DAY_OF_WEEK]
}