package com.andreacioccarelli.billingprotector.data

import android.util.Base64 import android.util.Log

/**
 * Class representing a pirate app with built-in string sign check
 * */

data class PirateApp(val field: String, val encodedField: String, val criteria: SelectionCriteria, val detectionName: String) {
    init {
        val check = Base64.encodeToString(field.toByteArray(), Base64.DEFAULT)
        if (check.trim() != encodedField.trim()) {
            Log.e("BillingProtector", "Field=[$field], Check=[$check], Encoded Field=[$encodedField]")
            throw SecurityException("Package names mismatch, apk file damaged or corrupted")
        }
    }
}

internal fun createPirateAppsList() = listOf(
        PirateApp("com.chelpus.lackypatch", "Y29tLmNoZWxwdXMubGFja3lwYXRjaA==", SelectionCriteria.MATCH_PACKAGE, "Lucky Patcher"),
        PirateApp("com.dimonvideo.luckypatcher", "Y29tLmRpbW9udmlkZW8ubHVja3lwYXRjaGVy", SelectionCriteria.MATCH_PACKAGE, "Lucky Patcher"),
        PirateApp("com.forpda.lp", "Y29tLmZvcnBkYS5scA==", SelectionCriteria.MATCH_PACKAGE, "4Pda Lucy Patcher"),
        PirateApp("com.android.vending.billing.InAppBillingService.", "Y29tLmFuZHJvaWQudmVuZGluZy5iaWxsaW5nLkluQXBwQmlsbGluZ1NlcnZpY2Uu", SelectionCriteria.SLICE, "Lucky Patcher"),
        PirateApp("ru.aaaaaaac.installer", "cnUuYWFhYWFhYWMuaW5zdGFsbGVy", SelectionCriteria.MATCH_PACKAGE, "Lucky Patcher Installer"),
        PirateApp("com.lp", "Y29tLmxw", SelectionCriteria.CLASS_NAME, "Lucky Patcher"),
        PirateApp("Lucky Patcher", "THVja3kgUGF0Y2hlcg==", SelectionCriteria.LABEL, "Lucky Patcher"),
        PirateApp("Lucky_Patcher", "THVja3lfUGF0Y2hlcg==", SelectionCriteria.LABEL, "Lucky Patcher"),

        // NOT the same as the one above. Using different chars
        PirateApp("Luckу_Pаtcher", "THVja9GDX1DQsHRjaGVy", SelectionCriteria.LABEL, "Lucky Patcher"),
        PirateApp("com.android.vending.licensing.ILicensingService", "Y29tLmFuZHJvaWQudmVuZGluZy5saWNlbnNpbmcuSUxpY2Vuc2luZ1NlcnZpY2U=", SelectionCriteria.MATCH_PACKAGE, "Lucky Patcher"),
        PirateApp("jase.freedom", "amFzZS5mcmVlZG9t", SelectionCriteria.SLICE, "Freedom"),
        PirateApp("madkite.freedom", "bWFka2l0ZS5mcmVlZG9t", SelectionCriteria.SLICE, "Freedom"),
        PirateApp("uret.jasi2169.patcher", "dXJldC5qYXNpMjE2OS5wYXRjaGVy", SelectionCriteria.MATCH_PACKAGE, "Uret Patcher"),
        PirateApp("org.creeplays.hack", "b3JnLmNyZWVwbGF5cy5oYWNr", SelectionCriteria.MATCH_PACKAGE, "Creeplays Patcher"),
        PirateApp("com.android.vendinc", "Y29tLmFuZHJvaWQudmVuZGluYw==", SelectionCriteria.MATCH_PACKAGE, "Fake Google Play Store"),
        PirateApp("apps.zhasik007.hack", "YXBwcy56aGFzaWswMDcuaGFjaw==", SelectionCriteria.MATCH_PACKAGE, "CreeHack"),
        PirateApp("com.leo.playcard", "Y29tLmxlby5wbGF5Y2FyZA==", SelectionCriteria.MATCH_PACKAGE, "Leo Playcard"),
        PirateApp("com.appsara.app", "Y29tLmFwcHNhcmEuYXBw", SelectionCriteria.MATCH_PACKAGE, "AppSara"),
        PirateApp("com.xmodgame", "Y29tLnhtb2RnYW1l", SelectionCriteria.MATCH_PACKAGE, "Xmod")
)