package com.afauzi.letsdo.repo

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.afauzi.letsdo.R
import com.afauzi.letsdo.data.ModelItemListTaskCategory
import com.amulyakhare.textdrawable.util.ColorGenerator

class AdapterListTaskCategory(private val itemsListTaskCategory: ArrayList<ModelItemListTaskCategory>): RecyclerView.Adapter<AdapterListTaskCategory.ListTaskCategoryViewHolder>() {
    class ListTaskCategoryViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        var nameTaskCategory: TextView = itemView.findViewById(R.id.tv_item_list_task_category)
        val linearLayout: LinearLayout = itemView.findViewById(R.id.ll_list_task_category)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListTaskCategoryViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_task_category, parent, false)
        return ListTaskCategoryViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ListTaskCategoryViewHolder, position: Int) {
        val currentItem = itemsListTaskCategory[position]
        holder.nameTaskCategory.text = currentItem.name

        // generate random colors
        val generator = ColorGenerator.DEFAULT
        val color = generator.randomColor
        holder.linearLayout.setBackgroundColor(color)
    }

    override fun getItemCount(): Int {
        return itemsListTaskCategory.size
    }
}