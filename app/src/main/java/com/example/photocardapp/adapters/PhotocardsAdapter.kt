package com.example.photocardapp.adapters

import PhotocardItemClickListener
import android.provider.ContactsContract.CommonDataKinds.Photo
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.example.photocardapp.databinding.PhotocardBinding
import com.example.photocardapp.models.PhotocardModel

class PhotocardsAdapter() : ListAdapter<PhotocardModel, PhotocardsAdapter.PhotocardViewHolder>(PhotocardDiffCallback()) {
    private var photocardItemClickListener: PhotocardItemClickListener? = null

    fun setOnItemClickListener(listener: PhotocardItemClickListener) {
        photocardItemClickListener = listener
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PhotocardViewHolder {
        // Inflate the item layout using data binding
        val binding = PhotocardBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PhotocardViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PhotocardViewHolder, position: Int) {
        val photocard = getItem(position)
        holder.bind(photocard)
        holder.itemView.setOnClickListener {
            photocardItemClickListener?.onItemClick(photocard)
        }
    }

    // ViewHolder class
    class PhotocardViewHolder(private val binding: PhotocardBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(photocard: PhotocardModel) {
            binding.apply {
                idolNameTextView.text = photocard.idolName
                titleTextView.text = photocard.title
                dateReceivedTextView.text = photocard.dateReceived
                notesTextView.text = photocard.notes
                Glide.with(binding.root)
                    .load(photocard.imageUrl)
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
//                    .override(400,600)
                    .centerCrop()
                    .into(binding.imageView)
            }
        }
    }

    // DiffUtil callback for efficient item updates
    class PhotocardDiffCallback : DiffUtil.ItemCallback<PhotocardModel>() {
        override fun areItemsTheSame(oldItem: PhotocardModel, newItem: PhotocardModel): Boolean {
            // Compare if items represent the same object by checking the unique identifier
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: PhotocardModel, newItem: PhotocardModel): Boolean {
            // Compare if item contents are the same
            return oldItem.idolName == newItem.idolName && oldItem.title == newItem.title
        }
    }

}