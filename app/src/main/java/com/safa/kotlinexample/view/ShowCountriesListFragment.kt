package com.safa.kotlinexample.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.*
import androidx.navigation.fragment.findNavController

import com.safa.kotlinexample.R
import kotlinx.android.synthetic.main.fragment_show_countries_list2.*

class ShowCountriesListFragment : Fragment() {

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

        goDetail.setOnClickListener {
            val action = ShowCountriesListFragmentDirections.actionShowCountriesListFragmentToCountryDetailFragment()
            findNavController().navigate(action)
        }
    }

}
