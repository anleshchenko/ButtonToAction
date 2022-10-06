package com.leshchenko.buttontoaction.data.repository.impl

import com.leshchenko.buttontoaction.data.entity.Action
import com.leshchenko.buttontoaction.data.network.api.ApiService
import com.leshchenko.buttontoaction.data.network.mapper.NetworkActionMapper
import com.leshchenko.buttontoaction.data.repository.ActionRepository
import javax.inject.Inject

class ActionRepositoryImpl @Inject constructor(
    private val apiService: ApiService,
    private val networkActionMapper: NetworkActionMapper
) : ActionRepository {

    override suspend fun getActions(): List<Action> =
        apiService
            .getActions()
            .map(networkActionMapper)
}