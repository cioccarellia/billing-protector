package com.andreacioccarelli.billingprotectorsample

import android.annotation.SuppressLint
import android.os.Bundle
import android.app.Activity
import android.content.Context
import android.os.Vibrator
import android.support.design.widget.FloatingActionButton
import android.widget.TextView
import android.widget.Toast

import com.andreacioccarelli.billingprotector.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.concurrent.TimeUnit
import kotlin.coroutines.CoroutineContext
import kotlin.system.measureTimeMillis

class DetectionActivity : Activity() {

    private lateinit var bp: BillingProtector

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity)

        bp = BillingProtector(this)
        runRefresh()

        val fab = findViewById<FloatingActionButton>(R.id.fab)
        fab.setOnClickListener {
            val vib = getSystemService(Context.VIBRATOR_SERVICE) as Vibrator
            vib.vibrate(100)

            runRefresh()
        }
    }

    private fun runRefresh() = CoroutineScope(Dispatchers.Main).launch {
        val millis = measureTimeMillis {  updateData() }
        val seconds = TimeUnit.MILLISECONDS.toSeconds(millis)

        runOnUiThread {
            Toast.makeText(this@DetectionActivity, "Re-computed. time: ${seconds}s", Toast.LENGTH_SHORT).show()
        }
    }

    private var i = 0
    @SuppressLint("SetTextI18n")
    private suspend fun updateData() {
        val mxp = findViewById<TextView>(R.id.mxp)
        mxp.text = "i=${i++}\nisRootInstalled: ${bp.isRootInstalled()}\npirateAppsList: ${bp.getPirateAppsListAsync()}"
    }
}
