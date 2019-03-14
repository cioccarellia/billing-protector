package com.andreacioccarelli.billingprotector.extensions

/**
 * Designed and Developed by Andrea Cioccarelli
 */

fun CharSequence?.valueOrNull(): String {
    val castResult: String? = this as? String
    return if (castResult.isNullOrBlank()) "null" else castResult
}