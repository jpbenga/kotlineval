package com.example.ebook

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class FavorisAdapter( private val ebooks: MutableList<Items>, private val favorisItemCallback: FavorisItemCallback) : RecyclerView.Adapter<FavorisViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavorisViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view: View = inflater.inflate(R.layout.list_favoris, parent, false)
        return FavorisViewHolder(view)
    }

    override fun getItemCount(): Int {
        return ebooks.size
    }

    override fun onBindViewHolder(holder: FavorisViewHolder, position: Int) {
        holder.bind(ebooks[position], favorisItemCallback)

    }
}