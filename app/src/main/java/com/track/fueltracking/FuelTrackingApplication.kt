package com.track.fueltracking

import android.app.Application

/**
 * Created by Tarun on 11/28/17.
 */
class FuelTrackingApplication : Application()
{
    companion object {

        lateinit var instance: FuelTrackingApplication
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
    }



}