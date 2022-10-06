package com.leshchenko.buttontoaction.data.repository

import com.leshchenko.buttontoaction.data.entity.Action

interface ActionRepository {

    suspend fun getActions(): List<Action>
}