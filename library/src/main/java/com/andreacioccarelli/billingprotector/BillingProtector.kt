package com.andreacioccarelli.billingprotector

import android.content.Context
import android.content.pm.PackageManager
import com.andreacioccarelli.billingprotector.data.PirateApp
import com.andreacioccarelli.billingprotector.data.SelectionCriteria
import com.andreacioccarelli.billingprotector.data.createPirateAppsList
import com.andreacioccarelli.billingprotector.utils.RootUtils


/**
 * Created by andrea on 2018/Jul.
 * Part of the package com.andreacioccarelli.billingprotector
 */
class BillingProtector(private val context: Context) {

    private val pirateApps: List<PirateApp> = createPirateAppsList()

    val isRootInstalled = RootUtils.hasRootAccess

    val arePirateAppsInstalled: Boolean
        get() {
            val appList = context.packageManager.getInstalledApplications(PackageManager.GET_META_DATA)

            for (app in appList) {
                pirateApps.map {
                    when (it.criteria) {
                        SelectionCriteria.SLICE -> {
                            if (it.packageName.contains(app.packageName)) return true
                        }

                        SelectionCriteria.MATCH -> {
                            if (it.packageName == app.packageName) return true
                        }
                    }
                }
            }
            return false
        }

    val pirateAppsList: List<PirateApp>
        get() {
            val foundThreats = mutableListOf<PirateApp>()
            val appList = context.packageManager.getInstalledApplications(PackageManager.GET_META_DATA)

            for (app in appList) {
                pirateApps.map {
                    when (it.criteria) {
                        SelectionCriteria.SLICE -> {
                            if (it.packageName.contains(app.packageName)) foundThreats.add(it)
                        }

                        SelectionCriteria.MATCH -> {
                            if (it.packageName == app.packageName) foundThreats.add(it)
                        }
                    }
                }
            }
            return foundThreats
        }
}