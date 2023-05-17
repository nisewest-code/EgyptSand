package com.egy.ptsa.nd.rv

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.Keep
import androidx.recyclerview.widget.RecyclerView
import com.egy.ptsa.nd.R

@Keep
class AdapterPuzzle(): RecyclerView.Adapter<HolderPuzzle>() {
    private var listPuzzle: MutableList<Int> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HolderPuzzle {
        return HolderPuzzle(LayoutInflater.from(parent.context).inflate(R.layout.layout_holder_item, parent, false))
    }

    override fun onBindViewHolder(holder: HolderPuzzle, position: Int) {
        holder.init(listPuzzle[position])
    }

    override fun getItemCount(): Int {
        return listPuzzle.size
    }

    fun updateList(list: List<Int>){
        listPuzzle = list.toMutableList()
        notifyDataSetChanged()
    }

    fun updateItem(position: Int){
        notifyItemChanged(position)
    }
}