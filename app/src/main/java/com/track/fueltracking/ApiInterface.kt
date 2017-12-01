package com.track.fueltracking

import com.track.fueltracking.ui.home.model.DieselResponse
import com.track.fueltracking.ui.home.model.LocationModel
import com.track.fueltracking.ui.home.model.PriceResponse
import com.track.fueltracking.ui.map.NearBySearchResponse
import io.reactivex.Flowable
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Created by Tarun on 11/28/17.
 */

interface ApiInterface {
    @GET("maps/api/geocode/json")
    fun getStateCityFromLocation(@Query("api_key") apikey: String, @Query("latlng") latLng: String): Flowable<LocationModel>

    @GET("/price")
    fun getPetrolPrice(@Query("city") city: String, @Query("fuel_type") fuelType: String): Flowable<PriceResponse>


    @GET("/price")
    fun getDieselPrice(@Query("city") city: String, @Query("fuel_type") fuelType: String): Flowable<DieselResponse>

    @GET("maps/api/place/nearbysearch/json?key=AIzaSyCpLg5e063NMm1dAlfTQQGtjRRoeDsdBq4&sensor=true")
    fun getNearByFuel(@Query("type") type: String, @Query("radius") radius: String, @Query("location") location: String): Flowable<NearBySearchResponse>

    @GET("maps/api/place/photo?sensor=false&key=AIzaSyCpLg5e063NMm1dAlfTQQGtjRRoeDsdBq4")
    fun getFuelPhoto(@Query("maxwidth") maxWidth: String, @Query("photoreference") photoreference: String)

}

