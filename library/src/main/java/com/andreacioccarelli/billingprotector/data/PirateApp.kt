package com.andreacioccarelli.billingprotector.data

import android.util.Base64


/**
 * Created by andrea on 2018/Jul.
 * Part of the package com.andreacioccarelli.billingprotector.data
 */
data class PirateApp(val packageName: String, val encodedPackageName: String, val criteria: SelectionCriteria, val name: String) {
    init {
        val check: String = Base64.decode(packageName.toByteArray(charset = Charsets.UTF_8), Base64.DEFAULT).toString()
        if (check != encodedPackageName) {
            throw SecurityException("Package names mismatch")
        }
    }
}