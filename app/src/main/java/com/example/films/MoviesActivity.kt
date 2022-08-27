package com.example.films

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class MoviesActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movies)
        val recyclerview = findViewById<RecyclerView>(R.id.recyclerview)
        recyclerview.layoutManager = LinearLayoutManager(this)
        val data = ArrayList<ItemsViewModel>()
        for (i in 1..20) {
            data.add(
                ItemsViewModel(
                    com.firebase.ui.auth.R.drawable.common_full_open_on_phone,
                    "Item $i"
                )
            )
        }

        val apiInterface = ApiInterface.create().getMovies("12790cecfabd1bf1230c24684b802223")

        //apiInterface.enqueue( Callback<List<Movie>>())
        apiInterface.enqueue(object : Callback<Movies> {
            override fun onResponse(call: Call<Movies>?, response: Response<Movies>?) {
                Log.d("testLogs", "onResponse success ${response?.body()?.results}")
                val adapter = CustomAdapter(response?.body()?.results)
                recyclerview.adapter = adapter

//
            }

            override fun onFailure(call: Call<Movies>?, t: Throwable?) {
                Log.d("testLogs", "onFailure: ${t?.message} ")
            }
        })
    }

    override fun onBackPressed() {
        super.onBackPressed()
        this.finishAffinity()
    }
}