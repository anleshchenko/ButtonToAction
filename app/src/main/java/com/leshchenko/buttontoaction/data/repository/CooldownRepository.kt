package com.leshchenko.buttontoaction.data.repository

interface CooldownRepository {

    suspend fun getCooldown(key: String, cooldown: Long): Long?

    suspend fun refreshCooldown(key: String)
}