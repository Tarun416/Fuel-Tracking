package com.track.fueltracking.ui

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.track.fueltracking.R
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.firebase.auth.FirebaseAuth
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import android.content.Intent
import android.util.Log
import com.google.firebase.auth.FirebaseUser
import android.widget.Toast
import android.view.View
import com.google.firebase.auth.GoogleAuthProvider
import com.track.fueltracking.ui.home.HomeActivity
import kotlinx.android.synthetic.main.activity_login.*

/**
 * Created by Tarun on 11/25/17.
 */
class LoginActivity : AppCompatActivity() , View.OnClickListener {

    companion object {
        private val TAG = "LoginActivity"
        private val RC_SIGN_IN = 9001
    }


    // [START declare_auth]
    private lateinit var mAuth: FirebaseAuth
    // [END declare_auth]

    private lateinit var mGoogleSignInClient: GoogleSignInClient

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        // [START config_signin]
        // Configure Google Sign In
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build()
        // [END config_signin]

        mGoogleSignInClient = GoogleSignIn.getClient(this, gso)

        // [START initialize_auth]
        mAuth = FirebaseAuth.getInstance()
        googleSignInButton.setOnClickListener(this)
        // [END initialize_auth]

    }

    // [START on_start_check_user]
    public override fun onStart() {
        super.onStart()
        // Check if user is signed in (non-null) and update UI accordingly.
        val currentUser = mAuth.currentUser
        takeUserToHomeScreen(currentUser)
    }
    // [END on_start_check_user]


    private fun takeUserToHomeScreen(currentUser: FirebaseUser?) {
        if(currentUser!=null)
        {
            val intent = Intent(this@LoginActivity, HomeActivity::class.java)
            intent.putExtra("username",currentUser.displayName)
            startActivity(intent)
            finish()
        }
    }

    // [START signin]
    private fun signIn() {
        val signInIntent = mGoogleSignInClient.signInIntent
        startActivityForResult(signInIntent, RC_SIGN_IN)
    }
    // [END signin]


    // [START onactivityresult]
    public override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent) {
        super.onActivityResult(requestCode, resultCode, data)

        // Result returned from launching the Intent from GoogleSignInApi.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            val task = GoogleSignIn.getSignedInAccountFromIntent(data)
            try {
                // Google Sign In was successful, authenticate with Firebase
                val account = task.getResult(ApiException::class.java)
                firebaseAuthWithGoogle(account)
            } catch (e: ApiException) {
                progressBar.visibility=View.GONE
                // Google Sign In failed, update UI appropriately
                Log.w(LoginActivity.TAG, "Google sign in failed", e)
                // [START_EXCLUDE]
                Toast.makeText(this@LoginActivity,"Google sign in failed",Toast.LENGTH_LONG).show()
                // [END_EXCLUDE]
            }

        }
    }
    // [END onactivityresult]

    private fun firebaseAuthWithGoogle(acct: GoogleSignInAccount) {
        Log.d(LoginActivity.TAG, "firebaseAuthWithGoogle:" + acct.id!!)
        // [START_EXCLUDE silent]
        progressBar.visibility=View.VISIBLE
        // [END_EXCLUDE]

        val credential = GoogleAuthProvider.getCredential(acct.idToken, null)
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {
                        // Sign in success, update UI with the signed-in user's information
                        Log.d(LoginActivity.TAG, "signInWithCredential:success")
                        val user = mAuth.currentUser
                        takeUserToHomeScreen(user)
                    } else {
                        progressBar.visibility=View.GONE
                        // If sign in fails, display a message to the user.
                        Log.w(LoginActivity.TAG, "signInWithCredential:failure", task.exception)
                        Toast.makeText(this@LoginActivity, "Authentication failed.",
                                Toast.LENGTH_SHORT).show()
                        Toast.makeText(this@LoginActivity,"Authentication failed",Toast.LENGTH_LONG).show()
                    }

                    // [START_EXCLUDE]
                    progressBar.visibility= View.GONE
                    // [END_EXCLUDE]
                }
    }

    override fun onClick(p0: View?) {
      when(p0!!.id)
      {
          R.id.googleSignInButton ->  {
              progressBar.visibility=View.VISIBLE
              signIn()
          }
      }
    }


}