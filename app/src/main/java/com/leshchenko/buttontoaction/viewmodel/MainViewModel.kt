package com.leshchenko.buttontoaction.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.leshchenko.buttontoaction.data.entity.Action
import com.leshchenko.buttontoaction.data.entity.ActionType
import com.leshchenko.buttontoaction.domain.interactor.ObserveActionInteractor
import com.leshchenko.buttontoaction.domain.interactor.PerformActionInteractor
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val observeActionInteractor: ObserveActionInteractor,
    private val performActionInteractor: PerformActionInteractor,
) : ViewModel() {

    private val _actionStateFlow = MutableStateFlow<Action?>(null)
    val actionStateFlow = _actionStateFlow.asStateFlow()

    private val _animationSharedFlow = MutableSharedFlow<Unit>()
    val animationSharedFlow = _animationSharedFlow.asSharedFlow()

    private val _toastSharedFlow = MutableSharedFlow<Unit>()
    val toastSharedFlow = _toastSharedFlow.asSharedFlow()

    init {
        viewModelScope.launch(Dispatchers.Default) {
            observeActionInteractor
                .observeAction()
                .collect { _actionStateFlow.value = it }
        }
    }

    fun onButtonClicked(action: Action) {
        viewModelScope.launch(Dispatchers.Default) {
            if (action.type.hasFilter && performActionInteractor.isActionAllowed().not()) {
                return@launch
            }

            when (action.type) {
                ActionType.ANIMATION -> _animationSharedFlow.emit(Unit)
                ActionType.TOAST -> _toastSharedFlow.emit(Unit)
                else -> Unit
            }
            performActionInteractor.performAction(action)
        }
    }
}