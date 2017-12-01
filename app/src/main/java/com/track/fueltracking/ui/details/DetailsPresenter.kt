package com.track.fueltracking.ui.details

import com.track.fueltracking.ApiInterface
import com.track.fueltracking.ApiUtils
import com.track.fueltracking.FuelTrackingApplication

/**
 * Created by Tarun on 12/1/17.
 */
class DetailsPresenter(val view: DetailsContract.View) : DetailsContract.Presenter {
    private lateinit var apiInterface: ApiInterface

    override fun getPhoto(maxwidth: String, photoreference: String) {
        view.showProgress()
        apiInterface = ApiUtils.getApiService("https://maps.googleapis.com/", FuelTrackingApplication.instance)

       // val disposable = apiInterface.
    }
}