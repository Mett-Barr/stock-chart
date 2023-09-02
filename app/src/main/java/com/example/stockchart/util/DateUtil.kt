package com.example.stockchart.util

object DateUtil {

    private const val yearMonthFormatWithSlash = "%04d/%02d"

    // 將 yyyymm 轉換為相對於最舊日期的 X 值
    fun yearMonthToXValue(currentDate: String, oldestDate: String): Int {
        val currentYear = currentDate.substring(0..3).toInt()
        val currentMonth = currentDate.substring(4..5).toInt()

        val oldestYear = oldestDate.substring(0..3).toInt()
        val oldestMonth = oldestDate.substring(4..5).toInt()

        return ((currentYear - oldestYear) * 12) + (currentMonth - oldestMonth)
    }

    // 從 X 值轉換回日期 yyyy/mm
    fun xValueToYearMonth(xValue: Int, oldestDate: String): String {
        val oldestYear = oldestDate.substring(0..3).toInt()
        val oldestMonth = oldestDate.substring(4..5).toInt()

        val relativeYear = xValue / 12
        val relativeMonth = xValue % 12

        var year = oldestYear + relativeYear
        var month = oldestMonth + relativeMonth

        // 修正月份可能超過12的情況
        while (month > 12) {
            year += 1
            month -= 12
        }

        return String.format(yearMonthFormatWithSlash, year, month)
    }

    fun convertToYearMonthFormat(date: String): String {
        return if (date.length == 6) {
            "${date.substring(0..3)}/${date.substring(4..5)}"
        } else {
            throw IllegalArgumentException("Expected format yyyymm but got $date")
        }
    }
}