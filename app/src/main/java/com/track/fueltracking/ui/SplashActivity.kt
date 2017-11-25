package com.track.fueltracking.ui

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.track.fueltracking.R
import android.view.WindowManager
import android.os.Build
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser


/**
 * Created by Tarun on 11/25/17.
 */
class SplashActivity : AppCompatActivity() {

    // [START declare_auth]
    private lateinit var mAuth: FirebaseAuth
    // [END declare_auth]
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            val w = window // in Activity's onCreate() for instance
            w.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS)
        }
        mAuth = FirebaseAuth.getInstance()

        startActivity(Intent(this@SplashActivity, LoginActivity::class.java))
        finish()
    }


    // [START on_start_check_user]
    public override fun onStart() {
        super.onStart()
        // Check if user is signed in (non-null) and update UI accordingly.
        val currentUser = mAuth.currentUser
        takeUserToHomeScreen(currentUser)
    }

    private fun takeUserToHomeScreen(currentUser: FirebaseUser?) {
        if(currentUser!=null)
        {
            val intent = Intent(this@SplashActivity,HomeActivity::class.java)
            intent.putExtra("username",currentUser.displayName)
            startActivity(intent)
            finish()
        }
        else
        {
            startActivity(Intent(this@SplashActivity, LoginActivity::class.java))
            finish()
        }
    }
    // [END on_start_check_user]

}