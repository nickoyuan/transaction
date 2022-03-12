package com.cba.transactionaccount.ui
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel


abstract class ViewModelUDF<State, Event> : ViewModel() {

    protected abstract val initialState: State

    val uiState: LiveData<State> get() = _uiState
    private val _uiState by lazy { MutableLiveData(initialState) }

    fun emitEvent(viewEvent: Event) {
        val oldState = _uiState.value ?: initialState
        val newState = handleStateUpdate(viewEvent, oldState)

        _uiState.postValue(newState)

        handleSideEffects(viewEvent, oldState, newState)
    }

    //@Warning("Don't call th   is function directly. Emit an Event instead.")
    protected abstract fun handleStateUpdate(viewEvent: Event, oldState: State): State

    //@Warning("Don't call this function directly. Emit an Event instead.")
    protected abstract fun handleSideEffects(viewEvent: Event, oldState: State, newState: State)
}