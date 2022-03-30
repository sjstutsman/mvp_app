package com.csis365.mvpapp.view

import com.csis365.mvpapp.dto.Fruit

interface ListsView {
    fun bindFruits(fruits: List<Fruit>)
    fun showError(errorMessage: String)
}
