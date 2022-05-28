package com.afauzi.letsdo.repo

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.recyclerview.widget.RecyclerView
import com.afauzi.letsdo.R
import com.afauzi.letsdo.data.ModelItemEvent
import com.afauzi.letsdo.data.ModelItemListTaskCategory
import com.afauzi.letsdo.main.view.event.EventActivity
import com.amulyakhare.textdrawable.util.ColorGenerator

class AdapterListItemEvent(
    private val context: Context,
    private val callClickListener: CallClickListenerEvent,
    private val listItemEvent: ArrayList<ModelItemEvent>
    ) : RecyclerView.Adapter<AdapterListItemEvent.ListItemEventViewHolder>() {

    class ListItemEventViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val radioGroup: RadioGroup = itemView.findViewById(R.id.enableRadioGroup)
        val title: RadioButton = itemView.findViewById(R.id.item_event_title)
        val itemGroup: LinearLayout = itemView.findViewById(R.id.ll_item_event)
        val desc: TextView = itemView.findViewById(R.id.item_event_desc)
        val date: TextView = itemView.findViewById(R.id.item_event_date)
        val icTime: ImageView = itemView.findViewById(R.id._item_icon_time)
        val divider: View = itemView.findViewById(R.id.dividerDescriptionEvent)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListItemEventViewHolder {
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.item_list_event, parent, false)
        return ListItemEventViewHolder(itemView)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ListItemEventViewHolder, position: Int) {
        val currentItem = listItemEvent[position]
        holder.title.text = currentItem.event_name

        holder.title.setOnClickListener {
            callClickListener.onClickListenerEvent(currentItem)
        }

        holder.desc.text = currentItem.desc
        holder.date.text = currentItem.time

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
        holder.icTime.setColorFilter(bgColor)

        if (currentItem.desc == "") {
            holder.desc.text = "no description!"
        }
    }

    override fun getItemCount(): Int {
        return listItemEvent.size
    }

    interface CallClickListenerEvent {
        fun onClickListenerEvent(data: ModelItemEvent)
    }
}