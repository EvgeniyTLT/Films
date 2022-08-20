package com.example.films

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle


class MoviesActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registration)
    }

    override fun onBackPressed() {
        super.onBackPressed()
        this.finishAffinity()
    }
}