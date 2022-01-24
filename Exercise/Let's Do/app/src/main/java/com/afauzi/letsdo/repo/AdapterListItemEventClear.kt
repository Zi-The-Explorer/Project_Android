package com.afauzi.letsdo.repo

import android.content.Context
import android.graphics.Paint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.afauzi.letsdo.R
import com.afauzi.letsdo.data.ModelItemEventClear

class AdapterListItemEventClear(private val listItemEventClear: ArrayList<ModelItemEventClear>): RecyclerView.Adapter<AdapterListItemEventClear.ListItemEventClearViewHolder>() {
    class ListItemEventClearViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val textItemClear: TextView = itemView.findViewById(R.id.item_tv_clear)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ListItemEventClearViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_list_event_clear, parent, false)
        return ListItemEventClearViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ListItemEventClearViewHolder, position: Int) {
        val currentItem = listItemEventClear[position]
        holder.textItemClear.text = currentItem.item_name
        holder.textItemClear.paintFlags = holder.textItemClear.paintFlags or Paint.STRIKE_THRU_TEXT_FLAG
    }

    override fun getItemCount(): Int {
        return listItemEventClear.size
    }
}