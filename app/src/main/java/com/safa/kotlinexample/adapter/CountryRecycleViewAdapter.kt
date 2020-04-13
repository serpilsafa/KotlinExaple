package com.safa.kotlinexample.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.safa.kotlinexample.R
import com.safa.kotlinexample.model.Country
import com.safa.kotlinexample.view.ShowCountriesListFragmentDirections
import kotlinx.android.synthetic.main.country_recycleview_row.view.*

class CountryRecycleViewAdapter(val countryList: ArrayList<Country>): RecyclerView.Adapter<CountryRecycleViewAdapter.MyViewHolder>() {

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.country_recycleview_row, parent, false)

        return MyViewHolder(view)
    }

    override fun getItemCount(): Int {
        return countryList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.itemView.country.text = countryList[position].countryName
        holder.itemView.capital.text = countryList[position].countryCapital


        holder.itemView.setOnClickListener {
            val action = ShowCountriesListFragmentDirections.actionShowCountriesListFragmentToCountryDetailFragment()
            Navigation.findNavController(it).navigate(action)
        }
    }

    fun updateRecycleView(newList: List<Country>){
        countryList.clear()
        countryList.addAll(newList)
        notifyDataSetChanged()
    }
}