package com.track.fueltracking.ui.home

/**
 * Created by Tarun on 11/28/17.
 */
interface HomeContract
{
    interface View
    {
        fun passCity( city : String)
        fun showProgress()
        fun hideProgress()
        fun showPetrolPrice(price : Double)
        fun showDieselPrice(price : Double)
    }

    interface Presenter
    {
        fun getCity(baseUrl : String ,latlng : String, api_key : String )
        fun getPetrolPrice(city : String)
        fun getDieselPrice()
    }
}