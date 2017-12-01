package com.track.fueltracking.ui.details

import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.View
import com.bumptech.glide.Glide
import com.track.fueltracking.R
import com.track.fueltracking.ui.map.Result
import kotlinx.android.synthetic.main.activity_details.*

/**
 * Created by Tarun on 12/1/17.
 */
class DetailsActivity : AppCompatActivity() , View.OnClickListener{

    private lateinit var response: Result

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)
        response = intent.extras.getParcelable("nearBySearchResponse")

        initUi()

    }

    private fun initUi() {
        if (response.photos != null && response.photos!!.isNotEmpty()) {

            val photoUrl = "https://maps.googleapis.com/maps/api/place/photo?sensor=false&key=AIzaSyCpLg5e063NMm1dAlfTQQGtjRRoeDsdBq4&photoreference=" + response.photos!![0].photo_reference + "&maxwidth=1000"
            Log.d("url", photoUrl.toString())

            Glide
                    .with(this)
                    .load(photoUrl)
                    .centerCrop()
                    .placeholder(R.drawable.placeholder)
                    .into(fuelImage)
        }

        if(response.opening_hours!=null)
        {
            if(response.opening_hours!!.open_now)
            {
                open.text="Open Now : Yes"
                open.setTextColor(ContextCompat.getColor(this@DetailsActivity,R.color.green))
            }
            else
            {
                open.text="Open Now : No"
                open.setTextColor(ContextCompat.getColor(this@DetailsActivity,R.color.red))
            }
        }

        else
        {
            open.visibility= View.GONE
        }

        name.text = response.name
        ratingNumber.text = response.rating.toString()
        ratingBar.numStars = 5
        ratingBar.rating = response.rating.toFloat()
        address.text = response.vicinity
        navigate.setOnClickListener(this)
    }

    override fun onClick(p0: View?) {
       when(p0?.id)
       {
           R.id.navigate -> {

           }
       }
    }


}