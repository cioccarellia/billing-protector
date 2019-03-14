@file:Suppress("unused")

package com.andreacioccarelli.billingprotector

import android.content.Context
import android.content.pm.PackageManager
import com.andreacioccarelli.billingprotector.data.DetectionCause
import com.andreacioccarelli.billingprotector.data.PirateApp
import com.andreacioccarelli.billingprotector.data.SelectionCriteria
import com.andreacioccarelli.billingprotector.extensions.removeDuplicatedPackages
import com.andreacioccarelli.billingprotector.extensions.valueOrNull
import com.andreacioccarelli.billingprotector.utils.RootUtils
import com.andreacioccarelli.billingprotector.utils.assembleAppList


/**
 * Designed and Developed by Andrea Cioccarelli
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
        val pirateApps = assembleAppList(DetectionCause.PIRACY)

        installedApps.forEach { installedApp ->
            pirateApps.forEach {
                when (it.criteria) {
                    SelectionCriteria.CONTAINS -> {
                        if (installedApp.packageName.contains(it.field)) foundThreats.add(it)
                    }

                    SelectionCriteria.MATCH -> {
                        if (it.field == installedApp.packageName) foundThreats.add(it)
                    }

                    SelectionCriteria.CLASS_NAME -> {
                        if (installedApp.className != null) {
                            if (installedApp.className.contains(it.field)) foundThreats.add(it)
                        }
                    }

                    SelectionCriteria.LABEL_REGEXP -> {
                        val label = installedApp.loadLabel(context.packageManager).valueOrNull()
                        val nonLocalizedLabel = installedApp.nonLocalizedLabel.valueOrNull()

                        when  {
                            label.matches(it.field.toRegex())
                                || nonLocalizedLabel.matches(it.field.toRegex()) -> foundThreats.add(it)
                        }
                    }
                }
            }
        }

        return foundThreats.removeDuplicatedPackages()
    }
}