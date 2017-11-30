package com.track.fueltracking.ui.map

/**
 * Created by Tarun on 11/30/17.
 */
interface MapContract
{
    interface View
    {
       fun response(nearBySearchResponse: NearBySearchResponse)
    }

    interface Presenter
    {
        fun getNearByFuel(type : String , location : String, radius : String )
    }
}