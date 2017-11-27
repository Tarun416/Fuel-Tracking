package com.track.fueltracking

import com.track.fueltracking.ui.model.LocationModel
import io.reactivex.Flowable
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Created by Tarun on 11/28/17.
 */

interface ApiInterface {
    @GET("maps/api/geocode/json")
    fun getStateCityFromLocation(@Query("latlng") latLng: String, @Query("api_key") apikey: String): Flowable<LocationModel>
}

//AIzaSyDj_w10jPfH2bj4vELjmNn8U4KWQqx7rNo