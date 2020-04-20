package com.safa.kotlinexample.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders

import com.safa.kotlinexample.R
import com.safa.kotlinexample.databinding.FragmentCountryDetailBinding
import com.safa.kotlinexample.util.PlaceholderDrawable
import com.safa.kotlinexample.util.downloadImage
import com.safa.kotlinexample.viewmodel.CountryDetailViewModel
import kotlinx.android.synthetic.main.fragment_country_detail.*

class CountryDetailFragment : Fragment() {
     var uuid: Int = 0
    lateinit var viewModel: CountryDetailViewModel
    lateinit var dataBinding:FragmentCountryDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        dataBinding = DataBindingUtil.inflate(inflater,R.layout.fragment_country_detail, container, false)
       // return inflater.inflate(R.layout.fragment_country_detail, container, false)
        return dataBinding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        arguments?.let {
            uuid = CountryDetailFragmentArgs.fromBundle(it).uuid
        }

        viewModel = ViewModelProviders.of(this).get(CountryDetailViewModel::class.java)
        viewModel.getCountry(uuid)

        observe()
    }

    fun observe(){
        viewModel.county.observe(viewLifecycleOwner, Observer {
            /*
            countryName.text = it.countryName
            countryCapital.text = it.countryCapital
            countryCurrency.text = it.countryCurrency
            countryLanguage.text = it.countryLanguage
            countryZone.text = it.countryZone
            countryFlag.downloadImage(it.countryFlag, PlaceholderDrawable(countryFlag.context))

             */

            dataBinding.selectedCountry = it

        })
    }
}
