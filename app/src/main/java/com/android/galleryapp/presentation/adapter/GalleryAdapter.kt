package com.android.galleryapp.presentation.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.android.galleryapp.BR
import com.android.galleryapp.R
import com.android.galleryapp.databinding.ItemGalleryBinding
import com.android.galleryapp.domain.entity.GalleryItem

class GalleryAdapter (private var galleryList: ArrayList<GalleryItem>) : RecyclerView.Adapter<GalleryAdapter.GalleryViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GalleryViewHolder {
       val galleryViewHolderBinding = DataBindingUtil.inflate<ItemGalleryBinding>(LayoutInflater.from(parent.context), R.layout.item_gallery, parent, false)
       return GalleryViewHolder(galleryViewHolderBinding)
    }

    override fun onBindViewHolder(holder: GalleryViewHolder, position: Int) {
        holder.bind(galleryList[position])
    }

    override fun getItemId(position: Int): Long {
        return galleryList[position].id
    }

    override fun getItemViewType(position: Int): Int {
        return position
    }

    override fun getItemCount() = if (galleryList.isEmpty()) 0 else galleryList.size

    class GalleryViewHolder(private val binding: ItemGalleryBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(galleryItem : GalleryItem) {
            binding.setVariable(BR.galleryItem, galleryItem)
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setData(newListData: ArrayList<GalleryItem>) {
        galleryList = newListData
        notifyDataSetChanged()
    }
}