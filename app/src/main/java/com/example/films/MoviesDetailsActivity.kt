package com.example.films

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import com.squareup.picasso.Picasso
import retrofit2.Call
import retrofit2.Response
import javax.security.auth.callback.Callback


class MoviesDetailsActivity : AppCompatActivity() {
    private lateinit var title: TextView
    private lateinit var releaseDate: TextView
    private lateinit var score: TextView
    private lateinit var overview: TextView
    private lateinit var banner: ImageView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movies_details)
        val id = intent.getIntExtra("id", 0)
        title = findViewById(R.id.movies_details_title)
        releaseDate = findViewById(R.id.movies_details_date)
        score = findViewById(R.id.movies_details_score)
        overview = findViewById(R.id.movies_body_overview)
        banner = findViewById(R.id.movies_details_image_banner)

        val apiInterface = id?.let { ApiInterface.create().getMoviesDetails(id, "12790cecfabd1bf1230c24684b802223") }
        apiInterface?.enqueue( object : retrofit2.Callback<MoviesDetails> {
            override fun onResponse(call: Call<MoviesDetails>?, response: Response<MoviesDetails>?) {
            title.text = response?.body()?.title
            releaseDate.text = response?.body()?.release_date.toString()
            score.text = response?.body()?.vote_average.toString()
            overview.text = response?.body()?.overview

            Picasso.get().load("https://image.tmdb.org/t/p/w500" + response?.body()?.backdrop_path).into(banner)
                Log.d("imMy", "${response?.body()?.backdrop_path}")
            }

            override fun onFailure(call: Call<MoviesDetails>, t: Throwable) {
                Log.d("test", "onFailire : ${t?.message}")
            }

        })

    }
}