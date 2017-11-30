package com.track.fueltracking.ui

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.track.fueltracking.R
import kotlinx.android.synthetic.main.fragment_price.*

/**
 * Created by Tarun on 11/25/17.
 */
class PriceFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater!!.inflate(R.layout.fragment_price, container, false)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }

    fun setPetrolPrice(price: Double) {
        petrolPrice.text = getString(R.string.rupee)+price.toString()
    }

    fun setDieselPrice(price: Double) {
        dieselPrice.text =  getString(R.string.rupee)+price.toString()
    }


}