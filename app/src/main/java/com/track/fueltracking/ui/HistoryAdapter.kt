package com.track.fueltracking.ui

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.track.fueltracking.R
import com.track.fueltracking.ui.details.TrackedData
import kotlinx.android.synthetic.main.history_items.view.*

/**
 * Created by Tarun on 12/1/17.
 */
class HistoryAdapter(val context: Context , val trackDataList : ArrayList<TrackedData>) : RecyclerView.Adapter<HistoryAdapter.ViewHolder>() {

    override fun onBindViewHolder(holder: ViewHolder?, position: Int) {

        val trackedata = trackDataList[position]
        holder!!.amount.text= trackedata.fuelType + " - "+trackedata.quantity+"Lt"+ " - Rs"+trackedata.amount
        holder.date.text=trackedata.time
    }

    override fun getItemCount(): Int {
        return trackDataList.size
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.history_items, parent, false))
    }


    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
       // val name = itemView.name
        val amount = itemView.amount
        val date = itemView.date
    }
}