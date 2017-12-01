package com.track.fueltracking.ui

import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.track.fueltracking.CommonUtils
import com.track.fueltracking.R
import com.track.fueltracking.ui.map.MapActivity
import kotlinx.android.synthetic.main.fragment_price.*

/**
 * Created by Tarun on 11/25/17.
 */
class PriceFragment : Fragment(), View.OnClickListener {

    private var latitude: Double = 0.0
    private var longitude: Double = 0.0

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater!!.inflate(R.layout.fragment_price, container, false)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initUi()

    }

    private fun initUi() {
        fuelIcon.setOnClickListener(this)
    }

    fun setPetrolPrice(price: Double) {
        if(isAdded)
        petrolPrice.text = getString(R.string.rupee) + price.toString()
    }

    fun setDieselPrice(price: Double) {
        if(isAdded)
        dieselPrice.text = getString(R.string.rupee) + price.toString()
    }

    override fun onClick(p0: View?) {
        when (p0?.id) {
            R.id.fuelIcon -> {

                if(!CommonUtils.isOnline(activity))
                {
                    Toast.makeText(activity,"Please check your internet connection",Toast.LENGTH_LONG).show()
                    return
                }

                if (latitude != 0.0) {
                    val intent = Intent(activity, MapActivity::class.java)
                    intent.putExtra("latitude", latitude)
                    intent.putExtra("longitude", longitude)
                    startActivity(intent)
                } else {
                    Toast.makeText(activity, "Not able to fetch location", Toast.LENGTH_LONG).show()
                }

            }
        }
    }


    fun setLatLon(latitude: Double, longitude: Double) {
        this.latitude = latitude
        this.longitude = longitude
    }
}



