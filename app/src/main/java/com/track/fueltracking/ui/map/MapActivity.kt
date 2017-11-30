package com.track.fueltracking.ui.map

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.track.fueltracking.R
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.Marker
import com.track.fueltracking.ui.DetailsActivity


/**
 * Created by Tarun on 11/30/17.
 */
class MapActivity : AppCompatActivity(), MapContract.View, OnMapReadyCallback, GoogleMap.OnMarkerClickListener {


    private lateinit var googleMap: GoogleMap

    private lateinit var presenter: MapPresenter

    private lateinit var nearBySearhResponse: NearBySearchResponse

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_maps)

        // Get the SupportMapFragment and request notification
        // when the map is ready to be used.
        val mapFragment = supportFragmentManager
                .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)

        presenter = MapPresenter(this@MapActivity)
        presenter.getNearByFuel("gas_station", intent.extras.getDouble("latitude").toString() + "," + intent.extras.getDouble("longitude").toString(), "1000")
    }


    override fun onMapReady(p0: GoogleMap?) {
        this.googleMap = p0!!
        googleMap.setOnMarkerClickListener(this)
    }


    override fun onDestroy() {
        super.onDestroy()
        presenter.onStop()
    }


    override fun response(nearBySearchResponse: NearBySearchResponse) {

        this.nearBySearhResponse = nearBySearchResponse

        for (k in 0 until nearBySearchResponse.results.size) {

            val i = nearBySearchResponse.results[k]

            val markerOptions = MarkerOptions()
            val latLng = LatLng(i.geometry.location.lat, i.geometry.location.lng)

            // Position of Marker on Map
            markerOptions.position(latLng)
            // Adding Marker to the Camera.

            // Adding colour to the marker
            markerOptions.icon(BitmapDescriptorFactory.fromResource(R.drawable.fuel))
            val marker = googleMap.addMarker(markerOptions)
            marker.tag = k
            // move map camera
            googleMap.moveCamera(CameraUpdateFactory.newLatLng(latLng))
            googleMap.animateCamera(CameraUpdateFactory.zoomTo(14f))
        }
    }

    override fun onMarkerClick(p0: Marker?): Boolean {
        val position = p0!!.tag
        Log.d("position",position.toString())

        val intent = Intent(this@MapActivity,DetailsActivity::class.java)
        intent.putExtra("name",nearBySearhResponse.results[position as Int].name)
        startActivity(intent)
        finish()

        return false
    }


}