package com.afauzi.recyclerview.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.afauzi.recyclerview.model.ModelHero
import com.afauzi.recyclerview.R
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

class GridHeroAdapter(val listHero: ArrayList<ModelHero>): RecyclerView.Adapter<GridHeroAdapter.GridViewHolder>() {

    private lateinit var onItemClickCallBackCuy: OnItemClickCallBackCuy

    fun setOnItemClickCallback(onItemClickCallBackCuy: OnItemClickCallBackCuy) {
        this.onItemClickCallBackCuy = onItemClickCallBackCuy
    }


    class GridViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imgPhoto:  ImageView = itemView.findViewById(R.id.item_grid_photo)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GridViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.item_grid_heroes, parent, false)
        return GridViewHolder(view)
    }

    override fun onBindViewHolder(holder: GridViewHolder, position: Int) {
        Glide.with(holder.itemView.context)
            .load(listHero[position].imageHero)
            .apply(RequestOptions().override(350, 550))
            .into(holder.imgPhoto)


        holder.itemView.setOnClickListener {
            onItemClickCallBackCuy.onItemClickedIniCuy(listHero[holder.adapterPosition])
        }
    }

    override fun getItemCount(): Int {
        return listHero.size
    }

    interface OnItemClickCallBackCuy{
        fun onItemClickedIniCuy(data: ModelHero)
    }

}