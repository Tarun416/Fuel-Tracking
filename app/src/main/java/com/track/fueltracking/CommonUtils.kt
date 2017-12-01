package com.track.fueltracking

import android.app.Activity
import android.content.Context
import android.net.ConnectivityManager
import android.support.design.widget.Snackbar
import android.view.View

/**
 * Created by Tarun on 11/27/17.
 */
class CommonUtils
{

    companion object {
        fun displaySnackBarIndefinite(activity: Activity?, message: Int, option: Int, onClickListener: View.OnClickListener) {
            try {
                if (activity != null) {
                    Snackbar.make(activity.findViewById(android.R.id.content), message, Snackbar.LENGTH_INDEFINITE).setAction(option, onClickListener).show()
                }
            } catch (e: Exception) {

                e.printStackTrace()
            }

        }


        fun displaySnackBar(activity: Activity?, message: Int, option: Int, onClickListener: View.OnClickListener) {
            try {
                if (activity != null) {
                    Snackbar.make(activity.findViewById(android.R.id.content), message, Snackbar.LENGTH_LONG).setAction(option, onClickListener).show()
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }

        }

        fun isOnline(context: Context): Boolean {
            val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            val netInfo = connectivityManager.activeNetworkInfo
            return netInfo != null && netInfo.isConnected
        }


    }

}