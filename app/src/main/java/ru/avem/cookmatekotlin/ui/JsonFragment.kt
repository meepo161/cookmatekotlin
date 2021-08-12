package ru.avem.cookmatekotlin.ui

import android.graphics.BitmapFactory
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
import ru.avem.cookmatekotlin.json.Recipes
import ru.avem.cookmatekotlin.model.JsonViewModel
import java.net.URL


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
                override fun onResponse(call: Call<Recipes>, response: Response<Recipes>) {
                    tvRequest.text = "name = ${response.body()!!.meals!![0].strMeal}"
                    Picasso.get().load("${response.body()!!.meals!![0].strMealThumb}")
                        .into(ivRequest)
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

