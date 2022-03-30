package com.csis365.mvpapp.network

import com.csis365.mvpapp.dto.Fruit
import com.csis365.mvpapp.dto.Joke
import retrofit2.Call
import retrofit2.http.GET

interface ChuckNorrisJokesApi {

    @GET("jokes/random")
    fun getJoke(): Call<Joke>
}