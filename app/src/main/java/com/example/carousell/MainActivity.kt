package com.example.carousell

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.AttributeSet
import android.view.View

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val articlesFragment = ArticlesFragment()
        supportFragmentManager.beginTransaction().replace(R.id.container , articlesFragment)
            .addToBackStack(null).commit()
    }

}