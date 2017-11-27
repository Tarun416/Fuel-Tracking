package com.track.fueltracking.ui

import android.annotation.SuppressLint
import android.location.Location
import android.os.Bundle
import android.support.design.widget.TabLayout
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import com.google.android.gms.common.ConnectionResult
import com.google.android.gms.location.LocationRequest
import com.track.fueltracking.R
import kotlinx.android.synthetic.main.activity_home.*
import com.google.android.gms.location.LocationServices
import com.google.android.gms.common.api.GoogleApiClient
import com.google.android.gms.location.LocationListener


/**
 * Created by Tarun on 11/25/17.
 */
class HomeActivity : AppCompatActivity(), LocationListener,
        GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener, HomeContract.View {


    private lateinit var mCurrentLocation: Location
    private var city: String = ""


    private lateinit var presenter: HomePresenter


    private lateinit var pagerAdapter: PagerAdapter
    private lateinit var mLocationRequest: LocationRequest
    private lateinit var mGoogleApiClient: GoogleApiClient

    private val INTERVAL = (1000 * 10).toLong()
    private val FASTEST_INTERVAL = (1000 * 5).toLong()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        presenter = HomePresenter(this@HomeActivity)
        initUi()
        setViewPager()
        name.text = "Hello " + intent.extras.getString("username")
    }

    public override fun onStart() {
        super.onStart()
        if (mGoogleApiClient.isConnected) {
            startLocationUpdates()

        }
    }

    @SuppressLint("MissingPermission")
    protected fun startLocationUpdates() {
        val pendingResult = LocationServices.FusedLocationApi.requestLocationUpdates(
                mGoogleApiClient, mLocationRequest, this)

    }


    private fun initUi() {
        createLocationRequest()

        mGoogleApiClient = GoogleApiClient.Builder(this)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(LocationServices.API).build()
        mGoogleApiClient.connect()
    }


    protected fun createLocationRequest() {
        mLocationRequest = LocationRequest()
        mLocationRequest.interval = INTERVAL
        mLocationRequest.fastestInterval = FASTEST_INTERVAL
        mLocationRequest.priority = LocationRequest.PRIORITY_HIGH_ACCURACY
    }

    private fun setViewPager() {
        tab_layout.addTab(tab_layout.newTab().setText(getString(R.string.fuelPrice)))
        tab_layout.addTab(tab_layout.newTab().setText(getString(R.string.cashback)))
        tab_layout.tabGravity = TabLayout.GRAVITY_FILL

        pagerAdapter = PagerAdapter(supportFragmentManager)

        val priceFragment = PriceFragment()
        pagerAdapter.addFrag(priceFragment)

        val historyFragment = HistoryFragment()
        pagerAdapter.addFrag(historyFragment)

        pager.adapter = pagerAdapter

        pager.addOnPageChangeListener(TabLayout.TabLayoutOnPageChangeListener(tab_layout))
        tab_layout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab) {
                pager.currentItem = tab.position
            }

            override fun onTabUnselected(tab: TabLayout.Tab) {

            }

            override fun onTabReselected(tab: TabLayout.Tab) {

            }
        })
    }


    override fun onLocationChanged(p0: Location?) {
        mCurrentLocation = p0!!
        presenter.getCity("http://maps.googleapis.com/",p0.latitude.toString() + "," + p0.longitude.toString(), "AIzaSyDj_w10jPfH2bj4vELjmNn8U4KWQqx7rNo")
    }

    override fun onConnected(p0: Bundle?) {
       startLocationUpdates()
    }

    override fun onConnectionSuspended(p0: Int) {

    }

    override fun onConnectionFailed(p0: ConnectionResult) {

    }

    override fun onPause() {
        super.onPause()
        stopLocationUpdates()
    }

    protected fun stopLocationUpdates() {
        LocationServices.FusedLocationApi.removeLocationUpdates(
                mGoogleApiClient, this)
        //Log.d(FragmentActivity.TAG, "Location update stopped .......................")
    }

    public override fun onResume() {
        super.onResume()
        if (mGoogleApiClient.isConnected) {
            startLocationUpdates()
            // Log.d(FragmentActivity.TAG, "Location update resumed .....................")
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.onStop()

    }


    override fun passCity(city: String) {
      if(city!=null && city.isNotEmpty()) {
          location.setTextColor(ContextCompat.getColor(this,R.color.white))
          location.text = "Current Location : "+city
      }
        else
      {
          location.text = "Sorry, couldn't find your location"
          location.setTextColor(ContextCompat.getColor(this,R.color.red))
      }

    }

}