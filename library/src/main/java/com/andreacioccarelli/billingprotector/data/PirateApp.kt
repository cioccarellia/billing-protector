package com.andreacioccarelli.billingprotector.data

import android.util.Base64
import android.util.Log


/**
 * Created by andrea on 2018/Jul.
 * Part of the package com.andreacioccarelli.billingprotector.data
 */
data class PirateApp(val packageName: String, val encodedPackageName: String, val criteria: SelectionCriteria, val name: String) {
    init {
        val check = Base64.encodeToString(packageName.toByteArray(), Base64.DEFAULT)

        if (check.trim() != encodedPackageName.trim()) {
            Log.d("Security error", "pn=$packageName, Check=[$check], encodedPN=[$encodedPackageName]")
            throw SecurityException("Package names mismatch")
        }
    }
}

fun createPirateAppsList() = listOf(
        PirateApp("com.chelpus.lackypatch", "Y29tLmNoZWxwdXMubGFja3lwYXRjaA==", SelectionCriteria.MATCH, "Chelpus Lucky Patcher"),
        PirateApp("com.dimonvideo.luckypatcher", "Y29tLmRpbW9udmlkZW8ubHVja3lwYXRjaGVy", SelectionCriteria.MATCH, "Lucky Patcher"),
        PirateApp("com.forpda.lp", "Y29tLmZvcnBkYS5scA==", SelectionCriteria.MATCH, "4Pda Lucy Patcher"),
        PirateApp("com.android.vending.billing.InAppBillingService", "Y29tLmFuZHJvaWQudmVuZGluZy5iaWxsaW5nLkluQXBwQmlsbGluZ1NlcnZpY2U=", SelectionCriteria.SLICE, "Lucky Patcher"),
        PirateApp("jase.freedom", "amFzZS5mcmVlZG9t", SelectionCriteria.SLICE, "Freedom"),
        PirateApp("madkite.freedom", "bWFka2l0ZS5mcmVlZG9t", SelectionCriteria.SLICE, "Freedom"),
        PirateApp("uret.jasi2169.patcher", "dXJldC5qYXNpMjE2OS5wYXRjaGVy", SelectionCriteria.MATCH, "Uret Patcher"),
        PirateApp("org.creeplays.hack", "b3JnLmNyZWVwbGF5cy5oYWNr", SelectionCriteria.MATCH, "Creeplays Patcher"),
        PirateApp("com.android.vendinc", "Y29tLmFuZHJvaWQudmVuZGluYw==", SelectionCriteria.MATCH, "Fake Google Play Store"),
        PirateApp("apps.zhasik007.hack", "YXBwcy56aGFzaWswMDcuaGFjaw==", SelectionCriteria.MATCH, "CreeHack"),
        PirateApp("com.leo.playcard", "Y29tLmxlby5wbGF5Y2FyZA==", SelectionCriteria.MATCH, "Leo Playcard"),
        PirateApp("com.appsara.app", "Y29tLmFwcHNhcmEuYXBw", SelectionCriteria.MATCH, "AppSara")
)