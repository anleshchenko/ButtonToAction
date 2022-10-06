package com.leshchenko.buttontoaction.domain.interactor

import com.leshchenko.buttontoaction.data.entity.Action
import com.leshchenko.buttontoaction.data.entity.ActionType
import com.leshchenko.buttontoaction.data.repository.ContactsRepository
import com.leshchenko.buttontoaction.data.repository.CooldownRepository
import com.leshchenko.buttontoaction.data.repository.NotificationRepository
import javax.inject.Inject

class PerformActionInteractorImpl @Inject constructor(
    private val cooldownRepository: CooldownRepository,
    private val contactsRepository: ContactsRepository,
    private val notificationRepository: NotificationRepository,
) : PerformActionInteractor {

    override suspend fun performAction(action: Action) {
        cooldownRepository.refreshCooldown(action.type.name)
        when (action.type) {
            ActionType.NOTIFICATION -> notificationRepository.showNotification()
            ActionType.CALL -> contactsRepository.callToContact()
            else -> {
                // handled on view layer
            }
        }
    }

    override suspend fun isActionAllowed(): Boolean {
        // TODO Check internet connection
        return true
    }
}