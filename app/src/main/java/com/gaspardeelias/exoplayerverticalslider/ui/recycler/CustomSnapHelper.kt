package com.gaspardeelias.exoplayerverticalslider.ui.recycler

import androidx.recyclerview.widget.LinearSnapHelper
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CustomSnapHelper(val eventBus: CustomEventBus) : LinearSnapHelper() {

    override fun findTargetSnapPosition(
        layoutManager: RecyclerView.LayoutManager?,
        velocityX: Int,
        velocityY: Int
    ): Int {
        var pos = super.findTargetSnapPosition(layoutManager, velocityX, velocityY)
        if(pos >=0 ) {
            CoroutineScope(Dispatchers.Main).launch {
                eventBus.invokeEvent(RecyclerEvent.ItemVisible(pos))
            }
        }
        return pos
    }
}