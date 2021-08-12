package ru.avem.cookmatekotlin.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import ru.avem.cookmatekotlin.R
import ru.avem.cookmatekotlin.json.ApiInterface
import ru.avem.cookmatekotlin.json.Recipes
import ru.avem.cookmatekotlin.model.JsonViewModel


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
        var apiInterface: Call<List<Recipes>> = ApiInterface.create().getRecipes()
        btnRequest.setOnClickListener {

            apiInterface.enqueue(object : Callback<List<Recipes>> {
                override fun onResponse(
                    call: Call<List<Recipes>>,
                    response: Response<List<Recipes>>
                ) {
                    tvRequest.text = response.toString()
                }

                override fun onFailure(call: Call<List<Recipes>>, t: Throwable) {
                    tvRequest.text = t.message
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

