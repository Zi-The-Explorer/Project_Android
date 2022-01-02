package com.afauzi.recyclerview.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.afauzi.recyclerview.model.ModelHero
import com.afauzi.recyclerview.R
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.ib.custom.toast.CustomToastView

class CardHeroAdapter(private val listHero: ArrayList<ModelHero>): RecyclerView.Adapter<CardHeroAdapter.CardHerViewHolder>() {
    class CardHerViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var imgPhoto: ImageView = itemView.findViewById(R.id.img_item_photo)
        var tvName: TextView = itemView.findViewById(R.id.tv_item_name)
        var tvDetail: TextView = itemView.findViewById(R.id.tv_item_detail)
        var btnFavorite: Button = itemView.findViewById(R.id.btn_set_favorite)
        var btnShare: Button = itemView.findViewById(R.id.btn_set_share)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardHerViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.item_card_heroes, parent, false)
        return CardHerViewHolder(view)
    }

    override fun onBindViewHolder(holder: CardHerViewHolder, position: Int) {
        val hero = listHero[position]

        Glide.with(holder.itemView.context)
            .load(hero.imageHero)
            .apply(RequestOptions().override(350, 550))
            .into(holder.imgPhoto)

        holder.tvName.text = hero.nameHero
        holder.tvDetail.text = hero.detailHero

        holder.btnFavorite.setOnClickListener {
            CustomToastView.makeText(
                holder.itemView.context,
                Toast.LENGTH_SHORT,
                CustomToastView.DEFAULT,
                "Favorite" + listHero[holder.adapterPosition].nameHero,
                false).show();
        }

        holder.btnShare.setOnClickListener {
            CustomToastView.makeText(
                holder.itemView.context,
                Toast.LENGTH_SHORT,CustomToastView.DEFAULT,
                "Share" + listHero[holder.adapterPosition].nameHero,
                false).show();
        }

        holder.itemView.setOnClickListener {
            CustomToastView.makeText(holder.itemView.context,
                Toast.LENGTH_SHORT,CustomToastView.DEFAULT,
                "Kamu Memilih" + listHero[holder.adapterPosition].nameHero,
                false).show();
        }

    }

    override fun getItemCount(): Int {
        return listHero.size
    }
}