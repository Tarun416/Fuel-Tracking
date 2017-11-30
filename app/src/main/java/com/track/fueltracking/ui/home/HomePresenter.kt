package com.track.fueltracking.ui.home

import com.track.fueltracking.ApiInterface
import com.track.fueltracking.ApiUtils
import com.track.fueltracking.FuelTrackingApplication
import com.track.fueltracking.ui.home.model.DieselResponse
import com.track.fueltracking.ui.home.model.LocationModel
import com.track.fueltracking.ui.home.model.PriceResponse
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import io.reactivex.subscribers.DisposableSubscriber

/**
 * Created by Tarun on 11/28/17.
 */
class HomePresenter(private var view: HomeContract.View) : HomeContract.Presenter {


    private lateinit var apiInterface: ApiInterface
    private lateinit var apiInterface1: ApiInterface
    private lateinit var apiInterface2: ApiInterface

    private val compositeDisposable = CompositeDisposable()
    private lateinit var city: String

    override fun getCity(baseUrl: String, latlng: String, api_key: String) {
        apiInterface = ApiUtils.getApiService(baseUrl, FuelTrackingApplication.instance)

        val disposable = apiInterface.getStateCityFromLocation(api_key, latlng)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableSubscriber<LocationModel>() {
                    override fun onError(t: Throwable?) {
                        view.passCity("")
                    }

                    override fun onNext(t: LocationModel?) {

                        if (t!!.results.isNotEmpty()) {
                            val addressComponentList = t!!.results[0].address_components
                            for (i in addressComponentList) {
                                i.types
                                        .filter { it.equals("locality", ignoreCase = true) }
                                        .forEach { view.passCity(i.long_name) }
                            }
                        }

                    }

                    override fun onComplete() {

                    }

                })

        compositeDisposable.add(disposable)
    }

    override fun getPetrolPrice(city: String) {
        view.showProgress()
        apiInterface1 = ApiUtils.getApiService("https://fuelpriceindia.herokuapp.com/", FuelTrackingApplication.instance)

        this.city = city

        if (this.city.equals("Bengaluru", true))
            this.city = "Bangalore"

        val disposable1 = apiInterface1.getPetrolPrice(this.city, "petrol")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableSubscriber<PriceResponse>() {
                    override fun onNext(t: PriceResponse?) {
                        view.showPetrolPrice(t!!.petrol)
                        getDieselPrice()
                    }

                    override fun onComplete() {

                    }

                    override fun onError(t: Throwable?) {
                        view.hideProgress()
                    }

                })

        compositeDisposable.add(disposable1)


    }

    override fun getDieselPrice() {

        apiInterface2 = ApiUtils.getApiService("https://fuelpriceindia.herokuapp.com/", FuelTrackingApplication.instance)

        this.city = city

        if (this.city.equals("Bengaluru", true))
            this.city = "Bangalore"

        val disposable1 = apiInterface2.getDieselPrice(this.city, "diesel")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableSubscriber<DieselResponse>() {
                    override fun onNext(t: DieselResponse?) {
                        view.hideProgress()
                        view.showDieselPrice(t!!.diesel)

                    }

                    override fun onComplete() {

                    }

                    override fun onError(t: Throwable?) {
                        view.hideProgress()
                    }

                })

        compositeDisposable.add(disposable1)
    }


    fun onStop() {
        compositeDisposable.clear()
    }

}