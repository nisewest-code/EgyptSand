package com.egy.ptsa.nd.listener

import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.egy.ptsa.nd.databinding.ActivityGamePlayEgyptBinding

class TouchListener(
    private val binding: ActivityGamePlayEgyptBinding,
    private var callbackOnMove: (fromPosition: Int, toPosition: Int) -> Unit,
    private var callbackOnSwipe: (pos: Int) -> Unit
) : ItemTouchHelper.Callback() {
    override fun isLongPressDragEnabled() = true
    override fun isItemViewSwipeEnabled() = false

    override fun getMovementFlags(
        recyclerView: RecyclerView,
        viewHolder: RecyclerView.ViewHolder
    ): Int {
        val dragFlags =
            ItemTouchHelper.UP or ItemTouchHelper.DOWN or ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT
        val swipeFlags =
            if (isItemViewSwipeEnabled) ItemTouchHelper.START or ItemTouchHelper.END else 0
        return makeMovementFlags(dragFlags, swipeFlags)
    }

    override fun onMove(
        recyclerView: RecyclerView,
        viewHolder: RecyclerView.ViewHolder,
        target: RecyclerView.ViewHolder
    ): Boolean {
        if (viewHolder.itemViewType != target.itemViewType)
            return false
        val fromPosition = viewHolder.adapterPosition
        val toPosition = target.adapterPosition
        callbackOnMove(fromPosition, toPosition)
        recyclerView.adapter!!.notifyItemMoved(fromPosition, toPosition)

        return true
    }

    override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
        val position = viewHolder.adapterPosition
        callbackOnSwipe(position)
        binding.rvGane.adapter!!.notifyItemRemoved(position)
    }
}