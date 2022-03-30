package com.csis365.mvpapp.view

import com.csis365.mvpapp.service.FruitService
import com.csis365.mvpapp.service.JokeService

class ListsPresenter(val view: ListsView) {

    val fruitService = FruitService()
    val jokeService = JokeService()

    fun start() {
        fruitService.getFruits(
            successCallback = { fruits ->
                view.bindFruits(fruits)
            },

            failureCallback = { errorMessage ->
                view.showError(errorMessage)
            }
        )
    }
}