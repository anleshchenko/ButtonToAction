package com.leshchenko.buttontoaction.data.network.entity

import com.google.gson.annotations.SerializedName

class NetworkAction(
    @SerializedName("type") val type: Type,
    @SerializedName("enabled") val enabled: Boolean,
    @SerializedName("priority") val priority: Int,
    @SerializedName("valid_days") val validDays: List<Int>,
    @SerializedName("cool_down") val coolDown: Long,
) {

    enum class Type {

        @SerializedName("animation")
        ANIMATION,
        @SerializedName("toast")
        TOAST,
        @SerializedName("call")
        CALL,
        @SerializedName("notification")
        NOTIFICATION,
    }
}