package com.safa.kotlinexample.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.safa.kotlinexample.R
import com.safa.kotlinexample.databinding.CountryRecycleviewRowBinding
import com.safa.kotlinexample.model.Country
import com.safa.kotlinexample.util.PlaceholderDrawable
import com.safa.kotlinexample.util.downloadImage
import com.safa.kotlinexample.view.ShowCountriesListFragmentDirections
import kotlinx.android.synthetic.main.country_recycleview_row.view.*

class CountryRecycleViewAdapter(val countryList: ArrayList<Country>): RecyclerView.Adapter<CountryRecycleViewAdapter.MyViewHolder>(), OnClickListener {

     class MyViewHolder(var view: CountryRecycleviewRowBinding) : RecyclerView.ViewHolder(view.root) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        //val view = inflater.inflate(R.layout.country_recycleview_row, parent, false)
        val view = DataBindingUtil.inflate<CountryRecycleviewRowBinding>(inflater, R.layout.country_recycleview_row, parent, false)

        return MyViewHolder(view)
    }

    override fun getItemCount(): Int {
        return countryList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

        holder.view.country = countryList[position]
        holder.view.listener = this
        /*
        holder.itemView.countryText.text = countryList[position].countryName
        holder.itemView.capital.text = countryList[position].countryCapital


        holder.itemView.setOnClickListener {
            val action = ShowCountriesListFragmentDirections.actionShowCountriesListFragmentToCountryDetailFragment(
                uuid = countryList[position].uuid.toInt())

            Navigation.findNavController(it).navigate(action)
        }

        holder.itemView.flagImageView.downloadImage(countryList[position].countryFlag, PlaceholderDrawable(holder.itemView.context) )

         */
    }

    fun updateRecycleView(newList: List<Country>){
        countryList.clear()
        countryList.addAll(newList)
        notifyDataSetChanged()
    }

    override fun onClick(v: View) {
        val uuid = v.countryUUID.text.toString().toInt()
        val action = ShowCountriesListFragmentDirections.actionShowCountriesListFragmentToCountryDetailFragment(
            uuid = uuid)

        Navigation.findNavController(v).navigate(action)
    }


}