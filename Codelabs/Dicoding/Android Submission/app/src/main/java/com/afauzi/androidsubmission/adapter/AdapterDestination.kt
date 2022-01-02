package com.afauzi.androidsubmission.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.afauzi.androidsubmission.R
import com.afauzi.androidsubmission.model.ModelDestination
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

class AdapterDestination(private val listDataDestination: ArrayList<ModelDestination>): RecyclerView.Adapter<AdapterDestination.ViewHolder>() {

    interface ClickItemCuy {
        fun onItemClick(data: ModelDestination)
    }

    private lateinit var onItemClickCallback: ClickItemCuy

    fun setOnItemClickCallBack(onItemClickCallback: ClickItemCuy) {
        this.onItemClickCallback = onItemClickCallback
    }

    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val tvNameDestinationView: TextView = itemView.findViewById(R.id.item_name_destination)
        val tvLocationDestinationView: TextView = itemView.findViewById(R.id.item_location_destination)
        val ivImageDestinationView: ImageView = itemView.findViewById(R.id.item_image_destination)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.item_destination, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val destination = listDataDestination[position]

        Glide.with(holder.itemView.context)
            .load(destination.imageDestination)
            .apply(RequestOptions().override(200, 150))
            .into(holder.ivImageDestinationView)

        holder.tvNameDestinationView.text = destination.nameDestination
        holder.tvLocationDestinationView.text = destination.locationDestination

        holder.itemView.setOnClickListener {
            onItemClickCallback.onItemClick(listDataDestination[holder.adapterPosition])
        }
    }

    override fun getItemCount(): Int {
        return listDataDestination.size
    }

}