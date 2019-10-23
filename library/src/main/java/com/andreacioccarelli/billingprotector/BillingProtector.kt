@file:Suppress("unused", "MemberVisibilityCanBePrivate", "NOTHING_TO_INLINE")

package com.andreacioccarelli.billingprotector

import android.content.Context
import android.content.pm.PackageManager
import com.andreacioccarelli.billingprotector.data.DetectionCause
import com.andreacioccarelli.billingprotector.data.PirateApp
import com.andreacioccarelli.billingprotector.data.SelectionCriteria
import com.andreacioccarelli.billingprotector.utils.RootUtils
import com.andreacioccarelli.billingprotector.utils.assembleAppList
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

/**
 * Designed and Developed by Andrea Cioccarelli
 */
class BillingProtector(
    private val context: Context,
    private val simulateSafeEnvironment: Boolean = false
) {
    /**
     * Lazily-evaluated and statically-generated pirate apps list
     * */
    private val pirateApps by lazy { assembleAppList(DetectionCause.PIRACY) }

    /**
     * Returns a boolean that represents the device root state.
     * */
    fun isRootInstalled(): Boolean = RootUtils.hasRootAccess()

    /**
     * Returns a String, representing the root binary path, if present.
     * */
    fun getRootBinaryPath(): String = RootUtils.extractPath()

    /**
     * Returns a list of the installed apps detected as pirate software
     * */
    fun getPirateAppsList(): List<PirateApp> {
        if (simulateSafeEnvironment) return emptyList()
        return startScan()
    }

    /**
     * Returns a list of the installed apps detected as pirate software
     * using kotlin coroutines
     * */
    suspend fun getPirateAppsListAsync(): List<PirateApp> {
        if (simulateSafeEnvironment) return emptyList()
        return withContext(CoroutineScope(Dispatchers.Default).coroutineContext) { startScan() }
    }

    /**
     * Internal function actually performing the security task
     * */
    private inline fun startScan(): List<PirateApp> {
        val foundThreats = mutableListOf<PirateApp>()

        for (installedApp in context.packageManager.getInstalledApplications(PackageManager.GET_META_DATA)) {
            pirateApps.forEach { pirateApp ->
                when (pirateApp.criteria) {
                    SelectionCriteria.CONTAINS -> {
                        if (installedApp.packageName.contains(pirateApp.field)) foundThreats.add(pirateApp)
                    }

                    SelectionCriteria.MATCH -> {
                        if (pirateApp.field == installedApp.packageName) foundThreats.add(pirateApp)
                    }

                    SelectionCriteria.CLASS_NAME -> {
                        installedApp.className?.let {
                            if (installedApp.className.contains(pirateApp.field)) {
                                foundThreats.add(pirateApp)
                            }
                        }
                    }

                    SelectionCriteria.LABEL_REGEXP -> {
                        val nonLocalizedLabel = installedApp.nonLocalizedLabel
                        val regexp = pirateApp.field.toRegex()

                        if (nonLocalizedLabel != null) {
                            if (nonLocalizedLabel.matches(regexp)) foundThreats.add(pirateApp)
                        } else {
                            val label = installedApp.loadLabel(context.packageManager)

                            if (label.matches(regexp)) foundThreats.add(pirateApp)
                        }
                    }
                }
            }
        }

        return foundThreats
    }
}