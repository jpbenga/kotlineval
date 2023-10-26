package com.example.ebook

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide

class InformationActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_information)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        setViewitems()
    }

    private fun setViewitems(){
        val ebookThumbnail = intent.getStringExtra("ebookThumbnail")
        val ebookTitle = intent.getStringExtra("ebookTitle")
        val ebookAuthor = intent.getStringExtra("ebookAuthor")
        val ebookPublishedDate = intent.getStringExtra("ebookPublishedDate")
        val ebookDescription = intent.getStringExtra("ebookDescription")

        // Associez les données aux vues correspondantes dans le layout
        val thumbnailImageView = findViewById<ImageView>(R.id.thumbnail_information)
        val titleTextView = findViewById<TextView>(R.id.title_information)
        val authorTextView = findViewById<TextView>(R.id.author_information)
        val publishedTextView = findViewById<TextView>(R.id.published_information)
        val descriptionTextView = findViewById<TextView>(R.id.description_information)

        // Affichez les données dans les vues
        Glide.with(this)
            .load(ebookThumbnail)
            .into(thumbnailImageView)
        titleTextView.text = ebookTitle
        authorTextView.text = ebookAuthor
        publishedTextView.text = ebookPublishedDate
        descriptionTextView.text = ebookDescription
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.itemId == android.R.id.home){
            finish()
        }
        return super.onOptionsItemSelected(item)
    }
}