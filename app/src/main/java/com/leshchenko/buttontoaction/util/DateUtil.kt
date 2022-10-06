package com.leshchenko.buttontoaction.util

import java.time.LocalDate

object DateUtil {

    fun getCurrentDay(): Int = LocalDate.now().dayOfWeek.value
}