package com.track.fueltracking.ui.details

import android.app.Dialog
import android.content.ActivityNotFoundException
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.location.Location
import android.net.Uri
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.View
import android.view.Window
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import android.widget.Toast
import com.bumptech.glide.Glide
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import com.track.fueltracking.CommonUtils
import com.track.fueltracking.R
import com.track.fueltracking.ui.home.HomeActivity
import com.track.fueltracking.ui.map.Result
import kotlinx.android.synthetic.main.activity_details.*
import java.util.*

/**
 * Created by Tarun on 12/1/17.
 */
class DetailsActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var response: Result

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)
        response = intent.extras.getParcelable("nearBySearchResponse")

        initUi()

    }

    private fun initUi() {
        if (response.photos != null && response.photos!!.isNotEmpty()) {

            val photoUrl = "https://maps.googleapis.com/maps/api/place/photo?sensor=false&key=AIzaSyCpLg5e063NMm1dAlfTQQGtjRRoeDsdBq4&photoreference=" + response.photos!![0].photo_reference + "&maxwidth=1000"
            Log.d("url", photoUrl.toString())

            Glide
                    .with(this)
                    .load(photoUrl)
                    .centerCrop()
                    .placeholder(R.drawable.placeholder)
                    .into(fuelImage)
        }

        if (response.opening_hours != null) {
            if (response.opening_hours!!.open_now) {
                open.text = "Open Now : Yes"
                open.setTextColor(ContextCompat.getColor(this@DetailsActivity, R.color.green))
            } else {
                open.text = "Open Now : No"
                open.setTextColor(ContextCompat.getColor(this@DetailsActivity, R.color.red))
            }
        } else {
            open.visibility = View.GONE
        }

        name.text = response.name
        ratingNumber.text = response.rating.toString()
        ratingBar.numStars = 5
        ratingBar.rating = response.rating.toFloat()
        address.text = response.vicinity
        navigate.setOnClickListener(this)
        pay.setOnClickListener(this)
        track.setOnClickListener(this)

    }

    override fun onClick(p0: View?) {
        when (p0?.id) {
            R.id.navigate -> {

            }

            R.id.pay -> {
                intent = Intent(Intent.ACTION_VIEW)
                intent.data = Uri.parse("upi://pay")
                try {
                    startActivity(intent)

                } catch (e: ActivityNotFoundException) {
                    startPlayStoreIntent()
                }


            }

            R.id.track -> {
                val dialog = Dialog(this@DetailsActivity)
                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
                dialog.setCancelable(true)
                dialog.setContentView(R.layout.custom_dialog)
                dialog.window.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

                val cancel = dialog.findViewById<Button>(R.id.cancel)
                val submit = dialog.findViewById<Button>(R.id.submit)
                val type = dialog.findViewById<Spinner>(R.id.fuel_type)
                val quantity = dialog.findViewById<Spinner>(R.id.quantity)
                val amount = dialog.findViewById<EditText>(R.id.amount)

                cancel.setOnClickListener {
                    dialog.dismiss()
                }

                submit.setOnClickListener {
                    if (amount.text.isEmpty()) {
                        Toast.makeText(this@DetailsActivity, "Please enter amount", Toast.LENGTH_LONG).show()
                    } else {
                        val location = Location("Location")
                        location.latitude = response.geometry.location.lat
                        location.longitude = response.geometry.location.lng

                        val trackedData = TrackedData(type.selectedItem.toString(), amount.text.toString(),response.geometry.location.lat.toString(),response.geometry.location.lng.toString(),Date().toString(),
                                quantity.selectedItem.toString())


                        val firebaseDatabase = FirebaseDatabase.getInstance()
                        val databaseReference = firebaseDatabase.reference
                        databaseReference.child("trackedData").child(FirebaseAuth.getInstance().currentUser!!.displayName).child((FirebaseAuth.getInstance().uid)).push().setValue(trackedData)
                        dialog.dismiss()
                        Toast.makeText(this@DetailsActivity, "Congrats..!! Cashback earned", Toast.LENGTH_LONG).show()
                        val intent = Intent(this@DetailsActivity, HomeActivity::class.java)
                        intent.putExtra("cashbackawarded", true)
                        startActivity(intent)
                        finishAffinity()
                    }
                }

                dialog.show()

            }
        }
    }

    private fun startPlayStoreIntent() {
        CommonUtils.displaySnackBar(this@DetailsActivity, R.string.no_app_found, R.string.install_app, View.OnClickListener {
            try {
                startActivity(Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=in.org.npci.upiapp")))
            } catch (anfe: ActivityNotFoundException) {
                startActivity(Intent(Intent.ACTION_VIEW, Uri.parse("http://play.google.com/store/apps/details?id=in.org.npci.upiapp")))
            }
        })

    }


}