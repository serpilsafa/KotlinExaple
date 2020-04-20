package com.safa.kotlinexample.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.*
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager

import com.safa.kotlinexample.R
import com.safa.kotlinexample.adapter.CountryRecycleViewAdapter
import com.safa.kotlinexample.viewmodel.CountryDetailViewModel
import com.safa.kotlinexample.viewmodel.ShowCountryListViewModel
import kotlinx.android.synthetic.main.fragment_show_countries_list2.*

class ShowCountriesListFragment : Fragment() {

    private val adapter = CountryRecycleViewAdapter(arrayListOf())
    private lateinit var viewModel: ShowCountryListViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_show_countries_list2, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

/*        goDetail.setOnClickListener {
            val action = ShowCountriesListFragmentDirections.actionShowCountriesListFragmentToCountryDetailFragment()
            findNavController().navigate(action)
        }*/

        viewModel = ViewModelProviders.of(this).get(ShowCountryListViewModel::class.java)
        viewModel.refleshData()

        recycleView.layoutManager = LinearLayoutManager(context)
        recycleView.adapter = adapter

        swipeRefreshLayout.setOnRefreshListener {
            viewModel.refreshDataFromApi()
            swipeRefreshLayout.isRefreshing = false
        }

        observeData()


    }

    fun observeData(){
        viewModel.countries.observe(viewLifecycleOwner, Observer {
            adapter.updateRecycleView(it)
        })
    }
}
