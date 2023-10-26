package com.example.ebook

import android.content.Intent
import android.view.View
import androidx.appcompat.widget.AppCompatButton
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class EbookViewHolder (itemView: View) : RecyclerView.ViewHolder(itemView) {
    private var thumbnail: AppCompatImageView
    private var author: AppCompatTextView
    private var title: AppCompatTextView
    private var like: AppCompatImageView


    init{
        thumbnail = itemView.findViewById(R.id.thumbnail)
        author = itemView.findViewById(R.id.author)
        title = itemView.findViewById(R.id.title)
        like = itemView.findViewById(R.id.like)
    }

    fun bind(ebook: Items, ebookItemCallback: EbookItemCallback){
        author.text = ebook.volumeInfo?.authors.toString()
        title.text = ebook.volumeInfo?.title.toString()
        Glide.with(thumbnail.context).load(ebook.volumeInfo?.imageLinks?.thumbnail).into(thumbnail)
        like.setOnClickListener{ ebookItemCallback.onBookmarkClick(ebook) }
        thumbnail.setOnClickListener {
            val intent = Intent(itemView.context, InformationActivity::class.java)
            intent.putExtra("ebookThumbnail", ebook.volumeInfo?.imageLinks?.smallThumbnail)
            intent.putExtra("ebookTitle", ebook.volumeInfo?.title)
            intent.putExtra("ebookAuthor", ebook.volumeInfo?.authors?.joinToString())
            intent.putExtra("ebookPublishedDate", ebook.volumeInfo?.publishedDate)
            intent.putExtra("ebookDescription", ebook.volumeInfo?.description)
            itemView.context.startActivity(intent)
        }
    }
}