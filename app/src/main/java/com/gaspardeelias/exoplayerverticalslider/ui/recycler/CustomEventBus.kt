package com.gaspardeelias.exoplayerverticalslider.ui.recycler

import android.view.View
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow

class CustomEventBus {

    private val _events = MutableSharedFlow<RecyclerEvent>()
    val events = _events.asSharedFlow()

    suspend fun invokeEvent(event: RecyclerEvent) = _events.emit(event)
}

sealed class RecyclerEvent() {
    class ItemVisible(val pos: Int): RecyclerEvent()
    class AttachPlayer(val view: View?, val pos: Int): RecyclerEvent()
}