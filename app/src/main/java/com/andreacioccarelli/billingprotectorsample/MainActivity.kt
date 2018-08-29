package com.andreacioccarelli.billingprotectorsample

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import com.andreacioccarelli.billingprotector.BillingProtector

import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*

class MainActivity : AppCompatActivity() {

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        title = "Kotlin activity"
        val bp = BillingProtector(baseContext)

        val yvr = bp.isRootInstalled
        val yyz = bp.arePirateAppsInstalled
        val mex = bp.pirateAppsList

        mxp.text = "isRootInstalled: $yvr\narePirateAppsInstalled: $yyz\npirateAppsList: ${mex.map { it.packageName }}"

        fab.setOnClickListener {
            startActivity(Intent(this, SecondaryActivity::class.java))
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onBackPressed() {}
}
