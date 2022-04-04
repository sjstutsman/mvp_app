package com.csis365.mvpapp.view

import com.csis365.mvpapp.service.FruitService
import com.csis365.mvpapp.service.JokeService

class ListsPresenterFactory {

    companion object {
        fun createPresenter(view: ListsView): ListsPresenter {
            return ListsPresenter(view, FruitService(), JokeService())
        }
    }
}