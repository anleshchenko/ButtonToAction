package com.leshchenko.buttontoaction.data.repository.impl

import android.content.Context
import androidx.core.content.edit
import com.leshchenko.buttontoaction.BuildConfig
import com.leshchenko.buttontoaction.data.repository.CooldownRepository
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class CooldownRepositoryImpl @Inject constructor(
    @ApplicationContext context: Context
) : CooldownRepository {

    private val preferences =
        context.getSharedPreferences(BuildConfig.APPLICATION_ID, Context.MODE_PRIVATE)

    override suspend fun getCooldown(key: String, cooldown: Long): Long? =
        preferences
            .getLong(key, -1L)
            .takeUnless { it == -1L }
            ?.let { lastRefreshed ->
                val timePassed = System.currentTimeMillis() - lastRefreshed
                if (timePassed > cooldown) {
                    null
                } else {
                    cooldown - timePassed
                }
            }

    override suspend fun refreshCooldown(key: String) =
        preferences.edit {
            putLong(key, System.currentTimeMillis())
        }
}