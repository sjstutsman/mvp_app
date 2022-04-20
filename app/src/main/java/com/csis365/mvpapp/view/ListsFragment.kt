package com.csis365.mvpapp.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.csis365.mvpapp.R
import com.csis365.mvpapp.dto.Fruit
import com.csis365.mvpapp.dto.Joke
import com.google.android.material.snackbar.Snackbar

class ListsFragment : Fragment(), ListsView {

    lateinit var container: View
    lateinit var ivJokeIcon: ImageView
    lateinit var tvJokeOfTheDay: TextView
    lateinit var rvFruits: RecyclerView
    lateinit var rvCategories: RecyclerView

    lateinit var presenter: ListsPresenter

    companion object {
        @JvmStatic
        fun newInstance() = ListsFragment().apply {
            arguments = Bundle().apply {
                // If you have any arguments to pass into the fragment, you may do so in here like this:
//                putString(ARG_PARAM1, param1)
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            // if you have any arguments, pull them out here
//            param1 = it.getString(ARG_PARAM1)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_lists, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        presenter = ListsPresenterFactory.createPresenter(this, requireContext())

        bindViews()
        presenter.start()
    }

    override fun bindFruits(fruits: List<Fruit>) {
        rvFruits.layoutManager = LinearLayoutManager(requireContext())
        rvFruits.adapter = FruitListAdapter(fruits)
    }

    override fun bindJoke(joke: Joke) {
        Glide
            .with(this)
            .load(joke.iconUrl)
            .placeholder(R.drawable.ic_launcher_background)
            .into(ivJokeIcon)
        tvJokeOfTheDay.text = joke.joke
    }

    override fun bindCategories(categories: List<String>) {
        rvCategories.layoutManager = LinearLayoutManager(requireContext())
        rvCategories.adapter = CategoriesAdapter(categories) { category ->
            presenter.getJoke(category)
        }
    }

    override fun showError(errorMessage: String) {
        Snackbar.make(container, errorMessage, Snackbar.LENGTH_LONG).show()
    }

    private fun bindViews() {
        container = requireView().findViewById(R.id.container)
        ivJokeIcon = requireView().findViewById(R.id.iv_joke_icon)
        tvJokeOfTheDay = requireView().findViewById(R.id.tv_joke_of_the_day)
        rvFruits = requireView().findViewById(R.id.rv_fruits)
        rvCategories = requireView().findViewById(R.id.rv_categories)
    }
}