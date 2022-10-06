package com.leshchenko.buttontoaction.domain.interactor

import com.leshchenko.buttontoaction.data.entity.Action
import com.leshchenko.buttontoaction.data.repository.ActionRepository
import com.leshchenko.buttontoaction.data.repository.CooldownRepository
import com.leshchenko.buttontoaction.util.DateUtil
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class ObserveActionInteractorImpl @Inject constructor(
    private val actionRepository: ActionRepository,
    private val cooldownRepository: CooldownRepository,
) : ObserveActionInteractor {

    override fun observeAction(): Flow<Action?> = flow {
        val actions = actionRepository.getActions()
        while (true) {
            val filteredActions = filterEnabledActions(actions)
            val actionsWithCooldown = filteredActions.associateWith { action ->
                cooldownRepository.getCooldown(action.type.name, action.cooldown)
            }

            emit(getActiveAction(actionsWithCooldown))

            if (hasMoreActiveActions(actionsWithCooldown).not()) {
                getNearestCooldown(actionsWithCooldown)?.let { delay(it) }
            }
        }
    }

    private fun filterEnabledActions(actions: List<Action>): List<Action> {
        val currentDay = DateUtil.getCurrentDay()
        return actions.filter { action ->
            action.enabled && currentDay in action.validDays
        }
    }

    private fun getActiveAction(actionsWithCooldown: Map<Action, Long?>): Action? {
        val readyActions = actionsWithCooldown.filter { (_, cooldown) ->
            cooldown == null
        }.keys
        val maxPriority = readyActions.maxOfOrNull { it.priority }
        return readyActions
            .filter { it.priority == maxPriority }
            .randomOrNull()
    }

    private fun hasMoreActiveActions(actionsWithCooldown: Map<Action, Long?>): Boolean =
        actionsWithCooldown.filter { (_, cooldown) ->
            cooldown == null
        }.size > 1

    private fun getNearestCooldown(actionsWithCooldown: Map<Action, Long?>): Long? =
        actionsWithCooldown.values.asSequence().filterNotNull().minOrNull()
}