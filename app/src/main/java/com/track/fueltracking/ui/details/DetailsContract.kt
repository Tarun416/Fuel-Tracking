package com.track.fueltracking.ui.details

/**
 * Created by Tarun on 12/1/17.
 */
interface DetailsContract
{
    interface View
    {
        fun showProgress()
        fun hideProgress()
        fun setPhoto(url : String)
    }

    interface Presenter
    {
        fun getPhoto(maxwidth : String , photoreference : String)
    }
}