package com.leshchenko.buttontoaction.data.network.mapper

import com.leshchenko.buttontoaction.data.entity.Action
import com.leshchenko.buttontoaction.data.entity.ActionType
import com.leshchenko.buttontoaction.data.network.entity.NetworkAction
import javax.inject.Inject

class NetworkActionMapper @Inject constructor() : (NetworkAction) -> Action {

    override fun invoke(action: NetworkAction): Action =
        Action(
            type = getActionType(action),
            enabled = action.enabled,
            priority = action.priority,
            validDays = action.validDays,
            cooldown = action.coolDown
        )

    private fun getActionType(action: NetworkAction): ActionType =
        when (action.type) {
            NetworkAction.Type.TOAST -> ActionType.TOAST
            NetworkAction.Type.ANIMATION -> ActionType.ANIMATION
            NetworkAction.Type.CALL -> ActionType.CALL
            NetworkAction.Type.NOTIFICATION -> ActionType.NOTIFICATION
        }

    companion object {

        private val TEST_DAYS = (1..7).toList()
        private val TEST_COOLDOWN = 3000L
        private val TEST_ENABLED = true
    }
}