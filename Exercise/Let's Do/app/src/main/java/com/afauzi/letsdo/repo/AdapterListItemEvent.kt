package com.afauzi.letsdo.repo

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.afauzi.letsdo.R
import com.afauzi.letsdo.data.ModelItemEvent
import com.amulyakhare.textdrawable.util.ColorGenerator

class AdapterListItemEvent(private val listItemEvent: ArrayList<ModelItemEvent>) :
    RecyclerView.Adapter<AdapterListItemEvent.ListItemEventViewHolder>() {
    class ListItemEventViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val title: RadioButton = itemView.findViewById(R.id.item_event_title)
        val desc: TextView = itemView.findViewById(R.id.item_event_desc)
        val date: TextView = itemView.findViewById(R.id.item_event_date)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListItemEventViewHolder {
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.item_list_event, parent, false)
        return ListItemEventViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ListItemEventViewHolder, position: Int) {
        val currentItem = listItemEvent[position]
        holder.title.text = currentItem.event_name
        holder.desc.text = currentItem.desc
        holder.date.text = currentItem.item_date_created

        // generate random colors
        val generator = ColorGenerator.create(
            mutableListOf(
                0xff706897.toInt(),
                0xff9088D4.toInt(),
                0xff81B2F3.toInt(),
                0xffF6C192.toInt(),
                0xffF29999.toInt()
            )
        )
        val bgColor = generator.randomColor
        holder.date.setBackgroundColor(bgColor)
    }

    override fun getItemCount(): Int {
        return listItemEvent.size
    }
}