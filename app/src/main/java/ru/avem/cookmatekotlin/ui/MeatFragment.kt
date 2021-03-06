package ru.avem.cookmatekotlin.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.Button
import android.widget.ListView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import ru.avem.cookmatekotlin.R
import ru.avem.cookmatekotlin.adapters.ListViewCheckBoxAdapter
import ru.avem.cookmatekotlin.data.IngredientsData
import ru.avem.cookmatekotlin.model.CheckModel
import ru.avem.cookmatekotlin.utils.Singleton.mapMeats
import ru.avem.cookmatekotlin.viewmodel.SharedViewModel


class MeatFragment : Fragment() {

    private var dataModel: ArrayList<CheckModel>? = null
    private lateinit var listView: ListView
    private lateinit var adapter: ListViewCheckBoxAdapter
    private var viewModel: SharedViewModel = SharedViewModel()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        viewModel = ViewModelProvider(requireActivity()).get(SharedViewModel::class.java)
        super.onViewCreated(view, savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_meat, container, false)

        val btnClear = view.findViewById<Button>(R.id.btn_clear_checkboxes)
        btnClear.setOnClickListener {
            dataModel!!.forEach {
                it.checked = false
                mapMeats[it.name ?: ""] = false
            }
            adapter.notifyDataSetChanged()
        }
        initListView(view)

        return view
    }

    private fun initListView(view: View) {
        listView = view.findViewById(R.id.listView)
        dataModel = ArrayList()
        if (mapMeats.isEmpty()) {
            IngredientsData.meatIngredients.forEach {
                dataModel!!.add(
                    CheckModel(
                        it,
                        false
                    )
                )
                mapMeats[it] = false
            }
        } else {
            IngredientsData.meatIngredients.forEach {
                dataModel!!.add(
                    CheckModel(
                        it,
                        mapMeats[it] ?: false
                    )
                )
            }
        }
        adapter = ListViewCheckBoxAdapter(dataModel!!, requireContext())
        listView.adapter = adapter

        listView.onItemClickListener = AdapterView.OnItemClickListener { _, _, position, _ ->
            val dataModel: CheckModel = dataModel!![position]
            dataModel.checked = !dataModel.checked
            mapMeats[dataModel.name ?: ""] = dataModel.checked
            adapter.notifyDataSetChanged()
        }
    }
}
