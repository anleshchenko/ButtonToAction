package com.leshchenko.buttontoaction.data.network.api

import com.leshchenko.buttontoaction.data.network.entity.NetworkAction
import retrofit2.http.GET

interface ApiService {

    @GET("/androidexam/butto_to_action_config.json")
    suspend fun getActions(): List<NetworkAction>
}