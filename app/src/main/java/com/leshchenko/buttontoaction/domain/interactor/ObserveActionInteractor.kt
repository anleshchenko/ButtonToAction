package com.leshchenko.buttontoaction.domain.interactor

import com.leshchenko.buttontoaction.data.entity.Action
import kotlinx.coroutines.flow.Flow

interface ObserveActionInteractor {

    fun observeAction(): Flow<Action?>
}