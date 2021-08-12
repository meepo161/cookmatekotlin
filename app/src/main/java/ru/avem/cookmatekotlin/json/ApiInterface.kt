package ru.avem.cookmatekotlin.json

import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.POST
import ru.avem.cookmatekotlin.json.Url.Companion.BASE_URL
import ru.avem.cookmatekotlin.json.Url.Companion.URL


interface ApiInterface {

    @POST(URL)
    fun getRecipes(): Call<Recipes>

    companion object {
        fun create() : ApiInterface {
            val retrofit = Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(BASE_URL)
                .build()
            return retrofit.create(ApiInterface::class.java)

        }
    }
}