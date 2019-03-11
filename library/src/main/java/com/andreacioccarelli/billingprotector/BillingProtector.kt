@file:Suppress("unused")

package com.andreacioccarelli.billingprotector

import android.content.Context
import android.content.pm.PackageManager
import com.andreacioccarelli.billingprotector.data.PirateApp
import com.andreacioccarelli.billingprotector.data.SelectionCriteria
import com.andreacioccarelli.billingprotector.data.createPirateAppsList
import com.andreacioccarelli.billingprotector.extensions.removeDuplicatedPackages
import com.andreacioccarelli.billingprotector.utils.RootUtils


/**
 * Created by andrea on 2018/Jul.
 * Part of the package com.andreacioccarelli.billingprotector
 */
class BillingProtector(private val context: Context) {

    /**
     * Returns a boolean that represents the device root state.
     * */
    fun isRootInstalled() = RootUtils.hasRootAccess()

    /**
     * Returns a String, representing the root binary path, if present.
     * */
    fun getRootBinaryPath() = RootUtils.extractPath()

    /**
     * Returns a boolean that indicates the presence of pirate apps in the host system
     * */
    fun arePirateAppsInstalled() = getPirateAppsList().isNotEmpty()

    /**
     * Returns a list of the installed apps detected as pirate software
     * */
    fun getPirateAppsList(): List<PirateApp> {
        val foundThreats = mutableListOf<PirateApp>()
        val installedApps = context.packageManager.getInstalledApplications(PackageManager.GET_META_DATA)
        val pirateApps = createPirateAppsList()

        installedApps.forEach { installedApp ->
            pirateApps.forEach {
                when (it.criteria) {
                    SelectionCriteria.SLICE -> {
                        if (installedApp.packageName.contains(it.field)) foundThreats.add(it)
                    }

                    SelectionCriteria.MATCH_PACKAGE -> {
                        if (it.field == installedApp.packageName) foundThreats.add(it)
                    }

                    SelectionCriteria.CLASS_NAME -> {
                        if (installedApp.className != null) {
                            if (installedApp.className.contains(it.field)) foundThreats.add(it)
                        }
                    }

                    SelectionCriteria.LABEL -> {
                        val label = installedApp.loadLabel(context.packageManager)
                        val nonLocalizedLabel = installedApp.nonLocalizedLabel

                        when (it.field) {
                            label, nonLocalizedLabel -> foundThreats.add(it)
                        }
                    }
                }
            }
        }

        return foundThreats.removeDuplicatedPackages()
    }
}