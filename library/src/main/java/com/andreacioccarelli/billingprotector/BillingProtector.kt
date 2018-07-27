package com.andreacioccarelli.billingprotector

import android.content.Context
import android.content.pm.PackageManager
import com.andreacioccarelli.billingprotector.data.PirateApp
import com.andreacioccarelli.billingprotector.data.SelectionCriteria

/**
 * Created by andrea on 2018/Jul.
 * Part of the package com.andreacioccarelli.billingprotector
 */
class BillingProtector(val context: Context) {

    private val pirateApps = listOf(
            PirateApp("com.chelpus.lackypatch", "Y29tLmNoZWxwdXMubGFja3lwYXRjaA==", SelectionCriteria.MATCH, "Chelpus Lucky Patcher"),
            PirateApp("com.dimonvideo.luckypatcher", "Y29tLmRpbW9udmlkZW8ubHVja3lwYXRjaGVy", SelectionCriteria.MATCH, "Lucky Patcher"),
            PirateApp("com.forpda.lp", "Y29tLmZvcnBkYS5scA==", SelectionCriteria.MATCH, "4Pda Lucy Patcher"),
            PirateApp("com.android.vending.billing.InAppBillingService", "Y29tLmFuZHJvaWQudmVuZGluZy5iaWxsaW5nLkluQXBwQmlsbGluZ1NlcnZpY2U=", SelectionCriteria.SLICE, "Lucky Patcher"),
            PirateApp("jase.freedom", "amFzZS5mcmVlZG9t", SelectionCriteria.SLICE, "Freedom"),
            PirateApp("madkite.freedom", "bWFka2l0ZS5mcmVlZG9t", SelectionCriteria.SLICE, "Freedom"),
            PirateApp("uret.jasi2169.patcher", "dXJldC5qYXNpMjE2OS5wYXRjaGVy", SelectionCriteria.MATCH, "Uret Patcher"),
            PirateApp("org.creeplays.hack", "b3JnLmNyZWVwbGF5cy5oYWNr", SelectionCriteria.MATCH, "Creeplays Patcher"),
            PirateApp("com.android.vendinc", "Y29tLmFuZHJvaWQudmVuZGluYw==", SelectionCriteria.SLICE, "Fake Google Play Store"),
            PirateApp("apps.zhasik007.hack", "YXBwcy56aGFzaWswMDcuaGFjaw==", SelectionCriteria.MATCH, "CreeHack"),
            PirateApp("com.leo.playcard", "Y29tLmxlby5wbGF5Y2FyZA==", SelectionCriteria.MATCH, "Leo Playcard"),
            PirateApp("com.appsara.app", "Y29tLmFwcHNhcmEuYXBw", SelectionCriteria.MATCH, "AppSara")
    )

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

    fun getPirateAppsList(): List<PirateApp> {
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