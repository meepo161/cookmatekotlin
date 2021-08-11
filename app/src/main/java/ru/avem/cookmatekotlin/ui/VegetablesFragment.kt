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
import ru.avem.cookmatekotlin.utils.Singleton.mapVegetables
import ru.avem.cookmatekotlin.viewmodel.SharedViewModel

class VegetablesFragment : Fragment() {

    private var dataModel: ArrayList<CheckModel>? = null
    private lateinit var listView: ListView
    private lateinit var adapter: ListViewCheckBoxAdapter
    private var viewModel: SharedViewModel = SharedViewModel()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(requireActivity()).get(SharedViewModel::class.java)
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
                mapVegetables[it.name ?: ""] = false
            }
            adapter.notifyDataSetChanged()
        }
        initListView(view)

        return view
    }

    private fun initListView(view: View) {
        listView = view.findViewById(R.id.listView)
        dataModel = ArrayList()
        if (mapVegetables.isEmpty()) {
            IngredientsData.vegetablesIngredients.forEach {
                dataModel!!.add(
                    CheckModel(
                        it,
                        false
                    )
                )
                mapVegetables[it] = false
            }
        } else {
            IngredientsData.vegetablesIngredients.forEach {
                dataModel!!.add(
                    CheckModel(
                        it,
                        mapVegetables[it] ?: false
                    )
                )
            }
        }
        adapter = ListViewCheckBoxAdapter(dataModel!!, requireContext())
        listView.adapter = adapter

        listView.onItemClickListener = AdapterView.OnItemClickListener { _, _, position, _ ->
            val dataModel: CheckModel = dataModel!![position]
            dataModel.checked = !dataModel.checked
            mapVegetables[dataModel.name ?: ""] = dataModel.checked
            adapter.notifyDataSetChanged()
        }
    }
}
