package com.track.fueltracking

import com.track.fueltracking.ui.home.model.DieselResponse
import com.track.fueltracking.ui.home.model.LocationModel
import com.track.fueltracking.ui.home.model.PriceResponse
import io.reactivex.Flowable
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Created by Tarun on 11/28/17.
 */

interface ApiInterface {
    @GET("maps/api/geocode/json")
    fun getStateCityFromLocation(@Query("api_key") apikey: String,@Query("latlng") latLng: String ): Flowable<LocationModel>

    @GET("/price")
    fun getPetrolPrice(@Query("city") city: String, @Query("fuel_type") fuelType: String): Flowable<PriceResponse>


    @GET("/price")
    fun getDieselPrice(@Query("city") city: String,@Query("fuel_type") fuelType: String): Flowable<DieselResponse>

}

