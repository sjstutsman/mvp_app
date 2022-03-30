package com.csis365.mvpapp.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.csis365.mvpapp.R
import com.csis365.mvpapp.dto.Fruit
import com.google.android.material.snackbar.Snackbar


class ListsActivity : AppCompatActivity(), ListsView {

    val presenter = ListsPresenter(this)

    lateinit var container: View
    lateinit var rvFruits: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lists)

        bindViews()

        presenter.start()
    }

    override fun bindFruits(fruits: List<Fruit>) {
        rvFruits.layoutManager = LinearLayoutManager(this)
        rvFruits.adapter = FruitListAdapter(fruits)
    }

    override fun showError(errorMessage: String) {
        Snackbar.make(container, errorMessage, Snackbar.LENGTH_LONG).show()
    }

    private fun bindViews() {
        container = findViewById(R.id.container)
        rvFruits = findViewById(R.id.rv_fruits)
    }
}