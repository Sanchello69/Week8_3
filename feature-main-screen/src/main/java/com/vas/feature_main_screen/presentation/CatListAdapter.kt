package com.vas.feature_main_screen.presentation

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.vas.feature_main_screen.R
import com.vas.feature_main_screen.databinding.CatItemBinding
import com.vas.feature_main_screen.domain.model.CatModel

class CatListAdapter : PagingDataAdapter<CatModel, CatListAdapter.ViewHolder>(DiffUtilCallBack) {

    var onClickListener: OnCatClickListener? = null

    interface OnCatClickListener {
        fun onLikeClick(id: String)
        fun onNoLikeClick()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view = layoutInflater.inflate(
            R.layout.cat_item, parent, false
        )
        val binding = CatItemBinding.bind(view)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        getItem(position)?.let { holder.bind(it, onClickListener!!) }
    }

    inner class ViewHolder(private val binding: CatItemBinding) : RecyclerView.ViewHolder(binding.root) {

        //private val context = binding.root.context

        fun bind(item: CatModel, onCatClickListener: OnCatClickListener) {
            binding.catImageView.setImageURI(item.url)

            binding.likeImageView.setOnClickListener {
                onCatClickListener.onLikeClick(item.id)
            }
            binding.noLikeImageView.setOnClickListener {
                onCatClickListener.onNoLikeClick()
            }
        }
    }

    object DiffUtilCallBack : DiffUtil.ItemCallback<CatModel>() {
        override fun areItemsTheSame(oldItem: CatModel, newItem: CatModel): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: CatModel, newItem: CatModel): Boolean {
            return oldItem == newItem
        }
    }
}