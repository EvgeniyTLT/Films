package com.example.films

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
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
        val adapter = CustomAdapter(data)
        recyclerview.adapter = adapter

        val apiInterface = ApiInterface.create().getMovies()

        //apiInterface.enqueue( Callback<List<Movie>>())
        apiInterface.enqueue( object : Callback<List<Movie>> {
            override fun onResponse(call: Call<List<Movie>>?, response: Response<List<Movie>>?) {

//                if(response?.body() != null)
//                    recyclerAdapter.setMovieListItems(response.body()!!)
            }

            override fun onFailure(call: Call<List<Movie>>?, t: Throwable?) {

            }
        })
    }

    override fun onBackPressed() {
        super.onBackPressed()
        this.finishAffinity()
    }
}