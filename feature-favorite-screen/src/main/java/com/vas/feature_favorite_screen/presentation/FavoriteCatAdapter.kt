package com.vas.feature_favorite_screen.presentation

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.facebook.drawee.view.SimpleDraweeView
import com.vas.feature_favorite_screen.R
import com.vas.feature_favorite_screen.domain.model.FavoriteCatModel

class FavoriteCatAdapter: RecyclerView.Adapter<ItemCatViewHolder>(){

    var data = listOf<FavoriteCatModel>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemCatViewHolder =
        ItemCatViewHolder.from(parent)

    override fun onBindViewHolder(holder: ItemCatViewHolder, position: Int) {
        val item = data[position]
        holder.bind(item)
    }

    override fun getItemCount() = data.size


}

class ItemCatViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){

    private val photoCat: SimpleDraweeView = itemView.findViewById(R.id.catImageView)

    fun bind(item: FavoriteCatModel) {

        photoCat.setImageURI(item.imageId)

    }

    companion object {
        fun from(parent: ViewGroup): ItemCatViewHolder {
            val layoutInflater = LayoutInflater.from(parent.context)
            val view = layoutInflater
                .inflate(R.layout.favorite_item, parent, false)
            return ItemCatViewHolder(view)
        }
    }
}