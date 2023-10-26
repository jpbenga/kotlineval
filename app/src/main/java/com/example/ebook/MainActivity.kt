package com.example.ebook

import EbookAdapter
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.widget.AppCompatButton
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.ebook.databinding.ActivityMainBinding
import com.google.android.material.textfield.TextInputLayout
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    companion object {
        var itemsList = mutableListOf<Items>()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setContentView(R.layout.activity_main)
        setViewItems()
    }

    private fun setViewItems(){
        findViewById<Button>(R.id.main_search_button).setOnClickListener{
            callService()
            goToNextActivity()
        }
    }

    private fun callService(){
        val searchCriteria = findViewById<TextInputLayout>(R.id.search_button)
        val searchInputText = searchCriteria.editText?.text.toString()
        val service: EbookApi.EbookService = EbookApi().getClient().create(EbookApi.EbookService::class.java)
        val call: Call<EbookJson2KtKotlin> = service.getEbooks(searchInputText)
        call.enqueue(object: Callback<EbookJson2KtKotlin>{
            override fun onResponse(
                call: Call<EbookJson2KtKotlin>,
                response: Response<EbookJson2KtKotlin>
            ) {
                processResponse(response)
                searchEnded()
                goToNextActivity()
            }
            override fun onFailure(call: Call<EbookJson2KtKotlin>, t: Throwable) {
                processFailure(t)
            }
        })
    }

    private fun searchEnded() {
        binding.searchButton.visibility = View.VISIBLE
    }

    private fun processResponse(response: Response<EbookJson2KtKotlin>){
        if(response.body() != null){
            val body = response.body()
            if(body?.items?.isNotEmpty() == true) {
                itemsList = body.items
            }
        }
    }

    private fun processFailure(t: Throwable){
        Toast.makeText(this, "Erreur", Toast.LENGTH_LONG).show()
    }

    private fun goToNextActivity(){
        val intent = Intent(this, SearchActivity::class.java)
        startActivity(intent)
    }
}