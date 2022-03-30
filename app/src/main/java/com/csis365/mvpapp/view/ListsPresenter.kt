package com.csis365.mvpapp.view

import com.csis365.mvpapp.service.FruitService
import com.csis365.mvpapp.service.JokeService

class ListsPresenter(val view: ListsView) {

    val fruitService = FruitService()
    val jokeService = JokeService()

    fun start() {
        getFruits()
        getJokes()
    }

    private fun getFruits() {
        fruitService.getFruits(
            successCallback = { fruits ->
                view.bindFruits(fruits)
            },

            failureCallback = { errorMessage ->
                view.showError(errorMessage)
            }
        )
    }

    private fun getJokes() {
        jokeService.getJoke(
            successCallback = { joke ->
                view.bindJoke(joke)
            },

            failureCallback = { errorMessage ->
                view.showError(errorMessage)
            }
        )
    }
}
