package com.track.fueltracking.ui

/**
 * Created by Tarun on 11/28/17.
 */
interface HomeContract
{
    interface View
    {
        fun passCity( city : String)

    }

    interface Presenter
    {
        fun getCity(baseUrl : String ,latlng : String, api_key : String )
    }
}