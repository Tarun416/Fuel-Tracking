package com.track.fueltracking.ui

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.track.fueltracking.R
import kotlinx.android.synthetic.main.activity_details.*

/**
 * Created by Tarun on 12/1/17.
 */
class DetailsActivity : AppCompatActivity()
{
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)
        name.text=intent.extras.getString("name")
    }

}