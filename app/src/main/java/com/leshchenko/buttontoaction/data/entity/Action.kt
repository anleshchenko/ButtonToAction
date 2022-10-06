package com.leshchenko.buttontoaction.data.entity

class Action(
    val type: ActionType,
    val enabled: Boolean,
    val priority: Int,
    val validDays: List<Int>,
    val cooldown: Long
)