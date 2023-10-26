package com.example.ebook

import android.view.View
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide


class FavorisViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    private var thumbnail: AppCompatImageView
    private var author: AppCompatTextView
    private var title: AppCompatTextView
    private var like: AppCompatImageView

    init{
        thumbnail = itemView.findViewById(R.id.thumbnail_favoris)
        author = itemView.findViewById(R.id.author_favoris)
        title = itemView.findViewById(R.id.title_favoris)
        like = itemView.findViewById(R.id.like_favoris)
    }

    fun bind(ebook: Items, favorisItemCallback: FavorisItemCallback) {
        author.text = ebook.volumeInfo?.authors?.joinToString() ?: ""
        title.text = ebook.volumeInfo?.title ?: ""
        Glide.with(thumbnail.context).load(ebook.volumeInfo?.imageLinks?.smallThumbnail).into(thumbnail)

        like.setOnClickListener { favorisItemCallback.onBookmarkClick(ebook) }
    }
}