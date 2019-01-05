package com.andreacioccarelli.billingprotector.extensions

import com.andreacioccarelli.billingprotector.data.PirateApp
import com.andreacioccarelli.billingprotector.data.SelectionCriteria

/**
 * Designed and Developed by Andrea Cioccarelli
 */

fun MutableList<PirateApp>.removeDuplicatedPackages(): List<PirateApp> {
    val list = mutableListOf<PirateApp>()

    forEach {
        if (it.criteria == SelectionCriteria.MATCH ||
                it.criteria == SelectionCriteria.SLICE ||
                !list.containsPackage(it.field)) {
            list.add(it)
        }
    }

    return list.toList()
}

fun MutableList<PirateApp>.containsPackage(pk: String): Boolean {
    for (item in this) {
        if (item.name == pk) return true
    }

    return false
}