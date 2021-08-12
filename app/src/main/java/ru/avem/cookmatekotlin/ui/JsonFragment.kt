package ru.avem.cookmatekotlin.ui

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.squareup.picasso.Picasso
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import ru.avem.cookmatekotlin.R
import ru.avem.cookmatekotlin.json.ApiInterface
import ru.avem.cookmatekotlin.json.Meal
import ru.avem.cookmatekotlin.json.Recipes
import ru.avem.cookmatekotlin.model.JsonViewModel
import ru.avem.cookmatekotlin.utils.Utils.Companion.toStringOrDefault


class JsonFragment : Fragment() {

    companion object {
        fun newInstance() = JsonFragment()
    }

    private lateinit var viewModel: JsonViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_json, container, false)
        var btnRequest = view.findViewById<Button>(R.id.btn_json)
        var tvRequest = view.findViewById<TextView>(R.id.tv_json)
        var ivRequest = view.findViewById<ImageView>(R.id.iv_json)
        btnRequest.setOnClickListener {
            var apiInterface = ApiInterface.create().getRecipes()
            apiInterface.enqueue(object : Callback<Recipes> {
                @SuppressLint("SetTextI18n")
                override fun onResponse(call: Call<Recipes>, response: Response<Recipes>) {
                    var mealList = mutableListOf<Meal>()
                    var responseList = response.body()
                    var recipe = Meal()
                    responseList!!.meals.forEach {
                        recipe = Meal(
                            it.idMeal,
                            it.strMeal,
                            it.strDrinkAlternate,
                            it.strCategory,
                            it.strArea,
                            it.strInstructions,
                            it.strMealThumb,
                            it.strTags,
                            it.strYoutube,
                            it.strIngredient1,
                            it.strIngredient2,
                            it.strIngredient3,
                            it.strIngredient4,
                            it.strIngredient5,
                            it.strIngredient6,
                            it.strIngredient7,
                            it.strIngredient8,
                            it.strIngredient9,
                            it.strIngredient10,
                            it.strIngredient11,
                            it.strIngredient12,
                            it.strIngredient13,
                            it.strIngredient14,
                            it.strIngredient15,
                            it.strIngredient16,
                            it.strIngredient17,
                            it.strIngredient18,
                            it.strIngredient19,
                            it.strIngredient20,
                            it.strMeasure1,
                            it.strMeasure2,
                            it.strMeasure3,
                            it.strMeasure4,
                            it.strMeasure5,
                            it.strMeasure6,
                            it.strMeasure7,
                            it.strMeasure8,
                            it.strMeasure9,
                            it.strMeasure10,
                            it.strMeasure11,
                            it.strMeasure12,
                            it.strMeasure13,
                            it.strMeasure14,
                            it.strMeasure15,
                            it.strMeasure16,
                            it.strMeasure17,
                            it.strMeasure18,
                            it.strMeasure19,
                            it.strMeasure20,
                            it.strSource,
                            it.strImageSource,
                            it.strCreativeCommonsConfirmed,
                            it.dateModified
                        )
                        mealList.add(recipe)
                    }
                    tvRequest.text = recipe.strMeal + "\n" + recipe.strInstructions
                    Picasso.get().load(recipe.strMealThumb).into(ivRequest)
                }

                override fun onFailure(call: Call<Recipes>, t: Throwable) {
                    tvRequest.text = "Faliure"
                }
            })
        }
        return view
    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(JsonViewModel::class.java)
    }

}

