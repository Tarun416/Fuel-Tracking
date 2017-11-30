package com.track.fueltracking.ui

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.track.fueltracking.R
import com.track.fueltracking.ui.map.NearBySearchResponse
import com.track.fueltracking.ui.map.Result
import kotlinx.android.synthetic.main.activity_details.*

/**
 * Created by Tarun on 12/1/17.
 */
class DetailsActivity : AppCompatActivity()
{
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)

      val response = intent.extras.getParcelable<Result>("nearBySearchResponse")

        name.text=response.name
    }

}