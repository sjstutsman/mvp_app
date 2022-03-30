package com.csis365.mvpapp.service

import com.csis365.mvpapp.dto.Joke
import com.csis365.mvpapp.network.RetrofitApiFactory
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class JokeService {

    val api = RetrofitApiFactory().getChuckNorrisJokeApi()

    fun getJoke(
        successCallback: (Joke) -> Unit,
        failureCallback: (errorMessage: String) -> Unit
    ) {
        api.getJoke().enqueue(object : Callback<Joke> {

            override fun onResponse(call: Call<Joke>, response: Response<Joke>) {

                if (response.isSuccessful) {
                    response.body()?.let {
                        successCallback(it)
                    } ?: run {
                        failureCallback("No jokes returned from service")
                    }
                } else {
                    failureCallback("Error getting jokes")
                }
            }

            override fun onFailure(call: Call<Joke>, t: Throwable) {
                failureCallback("Error: ${t.message}")
            }
        })

    }
}