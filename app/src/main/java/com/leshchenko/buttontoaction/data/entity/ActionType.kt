package com.leshchenko.buttontoaction.data.entity

import androidx.annotation.StringRes
import com.leshchenko.buttontoaction.R

enum class ActionType(@StringRes val nameRes: Int, val hasFilter: Boolean = false) {

    ANIMATION(nameRes = R.string.action_animation),
    TOAST(nameRes = R.string.action_toast, hasFilter = true),
    CALL(nameRes = R.string.action_call),
    NOTIFICATION(nameRes = R.string.action_notification)
}