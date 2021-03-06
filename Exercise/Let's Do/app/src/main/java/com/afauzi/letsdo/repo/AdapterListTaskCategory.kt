package com.afauzi.letsdo.repo

import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.afauzi.letsdo.R
import com.afauzi.letsdo.data.ModelItemListTaskCategory
import com.amulyakhare.textdrawable.util.ColorGenerator

class AdapterListTaskCategory(
    private val context: Context,
    private val callClickListener: CallClickListener,
    private val itemsListTaskCategory: ArrayList<ModelItemListTaskCategory>
    ): RecyclerView.Adapter<AdapterListTaskCategory.ListTaskCategoryViewHolder>() {

    class ListTaskCategoryViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        // item categoryTask
        var nameTaskCategory: TextView = itemView.findViewById(R.id.tv_item_list_task_category)
        var dateTaskCategory: TextView = itemView.findViewById(R.id.tv_item_date)
        val linearLayout: LinearLayout = itemView.findViewById(R.id.ll_list_task_category)
        var btnRemove: Button = itemView.findViewById(R.id.btn_remove_item_task_category)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListTaskCategoryViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_task_category, parent, false)
        return ListTaskCategoryViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ListTaskCategoryViewHolder, position: Int) {
        val currentItem = itemsListTaskCategory[position]
        holder.nameTaskCategory.text = currentItem.category_name
        holder.dateTaskCategory.text = currentItem.date_created

        // generate random colors
        val generator = ColorGenerator.create(mutableListOf(
            0xff706897.toInt(),
            0xff9088D4.toInt(),
            0xff81B2F3.toInt(),
            0xffF6C192.toInt(),
            0xffF29999.toInt()
        ))
        val color = generator.randomColor
        holder.linearLayout.setBackgroundColor(color)

        holder.linearLayout.setOnClickListener {
           callClickListener.onClickListener(currentItem)
        }
        holder.linearLayout.setOnLongClickListener {
           callClickListener.onLongClickListener(currentItem)
            true
        }
        holder.btnRemove.setOnClickListener {
            callClickListener.onClickRemove(currentItem)
        }
    }

    override fun getItemCount(): Int {
        return itemsListTaskCategory.size
    }

    interface CallClickListener {
        fun onClickListener(data: ModelItemListTaskCategory)
        fun onLongClickListener(data: ModelItemListTaskCategory)
        fun onClickRemove(data: ModelItemListTaskCategory)
    }
}