package com.example.splash.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.splash.databinding.GridViewItemBinding
import com.example.splash.network.UnsplashPhoto


/**
 * This class implements a [RecyclerView] which uses Data Binding to present [List] data,
 * including computing diffs between lists
 */
class PhotoGridAdapter :
    ListAdapter<UnsplashPhoto,
            PhotoGridAdapter.UnsplashPhotosViewHolder>(DiffCallback) {

    class UnsplashPhotosViewHolder(
        private var binding: GridViewItemBinding
        ) : RecyclerView.ViewHolder(binding.root) {
            fun bind(unsplashPhoto: UnsplashPhoto) {
                binding.photo = unsplashPhoto
                /**
                 * This is important, because it forces the data binding to execute immediately,
                 * which allows the RecyclerView to make the correct view size measurements
                 */
                binding.executePendingBindings()
            }
        }

    /**
     * Create new [RecyclerView] item views(invoked by the layout manager)
     */
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): UnsplashPhotosViewHolder {
        return UnsplashPhotosViewHolder(
            GridViewItemBinding.inflate(
                LayoutInflater.from(parent.context))
        )
    }

    /**
     * Replaces the contents of a view (invoked by the layout manager)
     */
    override fun onBindViewHolder(holder: UnsplashPhotosViewHolder, position: Int) {
        val unsplashPhoto = getItem(position)
        holder.bind(unsplashPhoto)
    }

    /**
     * Allows the RecyclerView to determine which items have been changed when the [List] of [UnsplashPhoto]
     * has been updated
     */
    companion object DiffCallback : DiffUtil.ItemCallback<UnsplashPhoto>() {
        override fun areItemsTheSame(oldItem: UnsplashPhoto, newItem: UnsplashPhoto): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: UnsplashPhoto, newItem: UnsplashPhoto): Boolean {
            return oldItem.urls == newItem.urls
        }
    }
}