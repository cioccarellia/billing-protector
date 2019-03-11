package com.andreacioccarelli.billingprotector.extensions

/**
 * Designed and Developed by Andrea Cioccarelli
 */

fun String?.valueOrNull(): String = this as? String ?: "null"