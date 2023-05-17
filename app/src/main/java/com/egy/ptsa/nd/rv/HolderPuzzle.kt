package com.egy.ptsa.nd.rv

import android.view.View
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView

class HolderPuzzle(itemView: View,) : RecyclerView.ViewHolder(itemView) {

    fun init(model: Int){
//        itemView.setBackgroundResource(model)
        (itemView as ImageView).setImageResource(model)
    }
}