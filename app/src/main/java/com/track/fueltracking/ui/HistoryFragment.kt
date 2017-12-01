package com.track.fueltracking.ui

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.track.fueltracking.R
import com.track.fueltracking.ui.details.TrackedData
import kotlinx.android.synthetic.main.fragment_history.*
import java.util.*

/**
 * Created by Tarun on 11/25/17.
 */
class HistoryFragment : Fragment() {
    private lateinit var adapter: HistoryAdapter

    private lateinit var arrayList: ArrayList<TrackedData>

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater!!.inflate(R.layout.fragment_history, container, false)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        arrayList = ArrayList()
        setRecyclerView()

        val database = FirebaseDatabase.getInstance()
        val reference = database.reference

        reference.child("trackedData").child(FirebaseAuth.getInstance().currentUser!!.displayName).child(FirebaseAuth.getInstance().uid).addValueEventListener(object : ValueEventListener {
            override fun onCancelled(p0: DatabaseError?) {
                Log.d("databaseerror", p0!!.message)
                historyRecyclerView.visibility = View.GONE
                emptyTextView.visibility = View.VISIBLE

            }

            override fun onDataChange(p0: DataSnapshot?) {
                if (p0!!.exists()) {
                    for (snapshot in p0.children) {
                        val trackedData: TrackedData = snapshot.getValue(TrackedData::class.java)!!
                        arrayList.add(trackedData)
                    }

                    if (arrayList != null && arrayList.size > 0) {
                            Collections.reverse(arrayList)
                        if(historyRecyclerView!=null ) {
                            historyRecyclerView.visibility = View.VISIBLE
                            adapter.notifyDataSetChanged()
                            emptyTextView.visibility = View.GONE
                        }

                    } else {
                        if(historyRecyclerView!=null ) {
                            historyRecyclerView.visibility = View.GONE
                            emptyTextView.visibility = View.VISIBLE
                        }
                    }

                } else {
                    if(historyRecyclerView!=null ) {
                        historyRecyclerView.visibility = View.GONE
                        emptyTextView.visibility = View.VISIBLE
                    }
                }

            }

        })

    }

    private fun setRecyclerView() {
        historyRecyclerView.layoutManager = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
        adapter = HistoryAdapter(activity, arrayList)
        historyRecyclerView.adapter = adapter


    }


}