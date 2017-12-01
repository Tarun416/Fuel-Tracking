package com.track.fueltracking.ui.details

import android.location.Location
import com.google.android.gms.maps.model.LatLng

/**
 * Created by Tarun on 12/1/17.
 */
data class TrackedData(var fuelType : String, var amount : String , var latitude : String , var longitude : String,
                        var time : String , var quantity : String)
{

   constructor() : this("","","","","","")
}

