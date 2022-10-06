package com.leshchenko.buttontoaction.di

import com.leshchenko.buttontoaction.data.repository.ActionRepository
import com.leshchenko.buttontoaction.data.repository.ContactsRepository
import com.leshchenko.buttontoaction.data.repository.CooldownRepository
import com.leshchenko.buttontoaction.data.repository.NotificationRepository
import com.leshchenko.buttontoaction.data.repository.impl.ActionRepositoryImpl
import com.leshchenko.buttontoaction.data.repository.impl.ContactsRepositoryImpl
import com.leshchenko.buttontoaction.data.repository.impl.CooldownRepositoryImpl
import com.leshchenko.buttontoaction.data.repository.impl.NotificationRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface RepositoryModule {

    @Binds
    fun bindActionRepository(actionRepository: ActionRepositoryImpl): ActionRepository

    @Binds
    fun bindCooldownRepository(cooldownRepository: CooldownRepositoryImpl): CooldownRepository

    @Binds
    fun bindNotificationRepository(notificationRepository: NotificationRepositoryImpl): NotificationRepository

    @Binds
    fun bindCallRepository(contactsRepository: ContactsRepositoryImpl): ContactsRepository
}