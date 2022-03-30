package com.csis365.mvpapp.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.csis365.mvpapp.R


class ListsActivity : AppCompatActivity() {
    
    val presenter = ListsPresenter()
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}