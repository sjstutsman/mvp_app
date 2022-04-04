package com.csis365.mvpapp.view

import com.csis365.mvpapp.dto.Fruit
import com.csis365.mvpapp.dto.Joke
import com.csis365.mvpapp.dto.Nutrition
import com.csis365.mvpapp.service.FruitService
import com.csis365.mvpapp.service.JokeService
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.jetbrains.annotations.Contract
import org.junit.Test

class ListsPresenterTest {

    val fruitService: FruitService = mockk(relaxed = true)
    val jokeService: JokeService = mockk(relaxed = true)
    val view: ListsView = mockk(relaxed = true)
    val presenter = ListsPresenter(view, fruitService, jokeService)

    @Test
    fun `On start, call get fruits`() {
        presenter.start()

        verify { fruitService.getFruits(any(), any()) }
    }

    @Test
    fun `When get fruits, given response is success, then bind fruits on view`() {
        val fruits = buildFruits()

        every { fruitService.getFruits(any(), any()) } answers {
            firstArg<(List<Fruit>) -> Unit>().invoke(fruits)
        }

        presenter.start()

        verify { view.bindFruits(any()) }
    }


    private fun buildFruits(): List<Fruit> {
        return listOf(
            Fruit(
                id = 123,
                name = "Apple",
                genus = "AppleGenus",
                family = "AppleFamily",
                order = "AppleOrder",
                nutritions = buildNutritions()
            )
        )
    }

    private fun buildNutritions(): Nutrition {
        return Nutrition(
            carbohydrates = 1.2,
            protein = 3.4,
            fat = 5.6,
            calories = 7.8,
            sugar = 9.0
        )
    }
}