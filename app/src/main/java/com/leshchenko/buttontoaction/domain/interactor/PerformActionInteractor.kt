package com.leshchenko.buttontoaction.domain.interactor

import com.leshchenko.buttontoaction.data.entity.Action

interface PerformActionInteractor {

    suspend fun performAction(action: Action)

    suspend fun isActionAllowed(): Boolean
}