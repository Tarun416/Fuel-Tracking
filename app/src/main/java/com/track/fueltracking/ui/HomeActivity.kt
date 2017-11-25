package com.track.fueltracking.ui

import android.os.Bundle
import android.support.design.widget.TabLayout
import android.support.v7.app.AppCompatActivity
import com.track.fueltracking.R
import kotlinx.android.synthetic.main.activity_home.*

/**
 * Created by Tarun on 11/25/17.
 */
class HomeActivity : AppCompatActivity()
{
    private lateinit var pagerAdapter: PagerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        setViewPager()

        name.text = "Hello "+intent.extras.getString("username")

    }

    private fun setViewPager() {
            tab_layout.addTab(tab_layout.newTab().setText(getString(R.string.fuelPrice)))
            tab_layout.addTab(tab_layout.newTab().setText(getString(R.string.cashback)))
            tab_layout.tabGravity = TabLayout.GRAVITY_FILL

            pagerAdapter = PagerAdapter(supportFragmentManager, tab_layout.tabCount)
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
}