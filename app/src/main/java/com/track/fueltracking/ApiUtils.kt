package com.track.fueltracking

/**
 * Created by Tarun on 11/28/17.
 */
class ApiUtils
{
    companion object {
        fun getApiService(baseUrl : String, application: FuelTrackingApplication) : ApiInterface
        {
            return RetrofitClient.getClient(baseUrl,application).create(ApiInterface::class.java)
        }
    }


}