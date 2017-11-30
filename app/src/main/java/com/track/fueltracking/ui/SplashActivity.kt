package com.track.fueltracking.ui

import android.Manifest
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.track.fueltracking.R
import android.view.WindowManager
import android.os.Build
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import android.content.DialogInterface
import android.location.LocationManager
import android.provider.Settings
import android.support.v7.app.AlertDialog
import android.view.View
import com.track.fueltracking.CommonUtils
import com.track.fueltracking.PermissionUtils
import com.track.fueltracking.ui.home.HomeActivity


/**
 * Created by Tarun on 11/25/17.
 */
class SplashActivity : AppCompatActivity() {

    // [START declare_auth]
    private lateinit var mAuth: FirebaseAuth
    lateinit var  currentUser: FirebaseUser

    // [END declare_auth]
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            val w = window // in Activity's onCreate() for instance
            w.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS)
        }
        mAuth = FirebaseAuth.getInstance()

    }


    // [START on_start_check_user]
    public override fun onStart() {
        super.onStart()
        // Check if user is signed in (non-null) and update UI accordingly.
        currentUser = mAuth.currentUser!!
        checkPermissions()
    }

    private fun checkPermissions() {
        if (PermissionUtils.checkSelfPermission(this@SplashActivity,
                arrayOf(Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.ACCESS_FINE_LOCATION))) {
            takeUserToHomeScreen(currentUser)
        }
    }

    private fun takeUserToHomeScreen(currentUser: FirebaseUser?) {
        if (currentUser != null) {
            checkLocation(currentUser)
        } else {
            startActivity(Intent(this@SplashActivity, LoginActivity::class.java))
            finish()
        }
    }

    private fun checkLocation(currentUser: FirebaseUser?) {
        val lm = this.getSystemService(Context.LOCATION_SERVICE) as LocationManager
        var gps_enabled = false

        try {
            gps_enabled = lm.isProviderEnabled(LocationManager.GPS_PROVIDER)
        } catch (ex: Exception) {
        }

        if (!gps_enabled) {
            // notify user
            val dialog = AlertDialog.Builder(this)
            dialog.setMessage(this.resources.getString(R.string.gps_network_not_enabled))
            dialog.setPositiveButton(this.resources.getString(R.string.open_location_settings), { _, _ ->
                // TODO Auto-generated method stub
                val myIntent = Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS)
                startActivityForResult(myIntent, 1)
                //get gps
            })
            dialog.setNegativeButton(this.getString(R.string.Cancel), DialogInterface.OnClickListener { paramDialogInterface, paramInt ->
                // TODO Auto-generated method stub
            })
           // dialog.setOnDismissListener { finish() }
            dialog.show()

        } else {
            val intent = Intent(this@SplashActivity, HomeActivity::class.java)
            intent.putExtra("username", currentUser!!.displayName)
            startActivity(intent)
            finish()
        }
    }
    // [END on_start_check_user]

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(requestCode==1 && resultCode==0)
        {
            val intent = Intent(this@SplashActivity, HomeActivity::class.java)
            intent.putExtra("username", currentUser!!.displayName)
            startActivity(intent)
            finish()
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == PermissionUtils.PERMISSION_REQUEST) {
            if (PermissionUtils.checkDeniedPermissions(this@SplashActivity, permissions).size === 0) {
                //All the permissions are granted
                takeUserToHomeScreen(currentUser)
            } else {
                CommonUtils.displaySnackBarIndefinite(this@SplashActivity, R.string.some_permissions_denied, R.string.retry, View.OnClickListener { checkPermissions() })
            }
        } else {
            super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        }
    }


}