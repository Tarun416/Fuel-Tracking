package com.track.fueltracking.ui

import com.track.fueltracking.ApiInterface
import com.track.fueltracking.ApiUtils
import com.track.fueltracking.FuelTrackingApplication
import com.track.fueltracking.ui.model.LocationModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import io.reactivex.subscribers.DisposableSubscriber

/**
 * Created by Tarun on 11/28/17.
 */
class HomePresenter(private var view : HomeContract.View) : HomeContract.Presenter
{

    private lateinit var apiInterface : ApiInterface
    private val compositeDisposable = CompositeDisposable()

    override fun getCity(baseUrl : String,latlng: String, api_key: String) {
        apiInterface=ApiUtils.getApiService(baseUrl, FuelTrackingApplication.instance)

        val disposable = apiInterface.getStateCityFromLocation(latlng,api_key)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableSubscriber<LocationModel>()
                {
                    override fun onError(t: Throwable?) {
                        view.passCity("")
                    }

                    override fun onNext(t: LocationModel?) {
                       val addressComponentList = t!!.results[0].address_components
                        for(i in addressComponentList)
                        {
                            i.types
                                    .filter { it.equals("locality", ignoreCase = true) }
                                    .forEach { view.passCity(i.long_name) }
                        }

                    }

                    override fun onComplete() {

                    }

                })

        compositeDisposable.add(disposable)


    }

    fun onStop()
    {
        compositeDisposable.clear()
    }

}