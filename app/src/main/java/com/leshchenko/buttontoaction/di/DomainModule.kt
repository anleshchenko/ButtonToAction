package com.leshchenko.buttontoaction.di

import com.leshchenko.buttontoaction.domain.interactor.ObserveActionInteractor
import com.leshchenko.buttontoaction.domain.interactor.ObserveActionInteractorImpl
import com.leshchenko.buttontoaction.domain.interactor.PerformActionInteractor
import com.leshchenko.buttontoaction.domain.interactor.PerformActionInteractorImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface DomainModule {

    @Binds
    fun bindObserveInteractor(actionInteractor: ObserveActionInteractorImpl): ObserveActionInteractor

    @Binds
    fun bindPerformInteractor(actionInteractor: PerformActionInteractorImpl): PerformActionInteractor
}