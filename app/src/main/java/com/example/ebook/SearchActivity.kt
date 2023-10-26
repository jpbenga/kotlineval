package com.example.ebook

import EbookAdapter
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.widget.AppCompatTextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class SearchActivity : AppCompatActivity() {

    private val idKey = "ID_KEY"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        setViewItems()
    }

    private fun setViewItems(){
        val recyclerView = findViewById<RecyclerView>(R.id.ebook_RV)
        val adapter = EbookAdapter(MainActivity.itemsList, object: EbookItemCallback{
            override fun onBookmarkClick(ebook: Items) {
                setFavoris()
                afficherFavoris()
                //Log.d("ok", "-----------LIKE-----------")
            }
        })

        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter
    }

    private fun setFavoris(){
        val sharedPreferences = getSharedPreferences("ebooks_favoris", MODE_PRIVATE)
        if (sharedPreferences.contains(idKey)) {
            val titre = sharedPreferences.getString(idKey, "")
            val message = "Cet élément est déjà en favoris : $titre"
            Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
        } else {
            val titre = findViewById<AppCompatTextView>(R.id.title).text.toString()
            sharedPreferences
                .edit()
                .putString(idKey, titre)
                .apply()
            val message = "Élément ajouté aux favoris : $titre"
            Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
        }
    }
    private fun afficherFavoris() {
        val sharedPreferences = getSharedPreferences("ebooks_favoris", MODE_PRIVATE)
        val contenuFavoris = sharedPreferences.getString(idKey, null)

        if (contenuFavoris != null) {
            println("Contenu des préférences : $contenuFavoris")
        } else {
            println("Pas de contenu dans les préférences")
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.itemId == android.R.id.home){
            finish()
        }
        return super.onOptionsItemSelected(item)
    }

}