package com.track.fueltracking.ui.map

import com.track.fueltracking.ApiInterface
import com.track.fueltracking.ApiUtils
import com.track.fueltracking.FuelTrackingApplication
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import io.reactivex.subscribers.DisposableSubscriber

/**
 * Created by Tarun on 11/30/17.
 */
class MapPresenter(val view : MapContract.View) : MapContract.Presenter
{

    private lateinit var apiInterface: ApiInterface
    private val compositeDisposable = CompositeDisposable()

    override fun getNearByFuel(type: String, location: String, radius: String) {
       apiInterface=ApiUtils.getApiService("https://maps.googleapis.com/", FuelTrackingApplication.instance)
       val disposable =  apiInterface.getNearByFuel(type,radius,location)
                .subscribeOn(Schedulers.io())
               .observeOn(AndroidSchedulers.mainThread())
               .subscribeWith(object : DisposableSubscriber<NearBySearchResponse>()
               {
                   override fun onError(t: Throwable?) {

                   }

                   override fun onComplete() {

                   }

                   override fun onNext(t: NearBySearchResponse?) {
                     view.response(t!!)
                   }

               })
    }

    fun onStop()
    {
        compositeDisposable.clear()
    }

}