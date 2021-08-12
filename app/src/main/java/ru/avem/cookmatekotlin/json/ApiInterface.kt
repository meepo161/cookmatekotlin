package ru.avem.cookmatekotlin.json

import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import ru.avem.cookmatekotlin.json.Url.Companion.BASE_URL
import ru.avem.cookmatekotlin.json.Url.Companion.URL
import java.util.*
import kotlin.reflect.jvm.internal.impl.load.kotlin.JvmType

interface ApiInterface {

    @GET(URL)
    fun getRecipes() : Call<List<Recipes>>

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