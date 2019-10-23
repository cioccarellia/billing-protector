package com.andreacioccarelli.billingprotector.utils

import com.andreacioccarelli.billingprotector.assets.definitions
import com.andreacioccarelli.billingprotector.data.DetectionCause
import com.andreacioccarelli.billingprotector.data.PirateApp
import com.andreacioccarelli.billingprotector.data.SelectionCriteria
import com.andreacioccarelli.billingprotector.extensions.add

/**
 * Designed and Developed by Andrea Cioccarelli
 */

fun assembleAppList(cause: DetectionCause): List<PirateApp> {
    when (cause) {
        DetectionCause.PIRACY -> {
            return mutableListOf<PirateApp>().apply {
                add(definitions.slice.field1, definitions.slice.khash1, SelectionCriteria.CONTAINS, definitions.slice.kname1)
                add(definitions.slice.field2, definitions.slice.khash2, SelectionCriteria.CONTAINS, definitions.slice.kname2)
                add(definitions.slice.field3, definitions.slice.khash3, SelectionCriteria.CONTAINS, definitions.slice.kname3)
                add(definitions.slice.field4, definitions.slice.khash4, SelectionCriteria.CONTAINS, definitions.slice.kname4)
                add(definitions.slice.field5, definitions.slice.khash5, SelectionCriteria.CONTAINS, definitions.slice.kname5)
                add(definitions.slice.field6, definitions.slice.khash6, SelectionCriteria.CONTAINS, definitions.slice.kname6)

                add(definitions.match.field1, definitions.match.khash1, SelectionCriteria.MATCH, definitions.match.kname1)
                add(definitions.match.field2, definitions.match.khash2, SelectionCriteria.MATCH, definitions.match.kname2)
                add(definitions.match.field3, definitions.match.khash3, SelectionCriteria.MATCH, definitions.match.kname3)
                add(definitions.match.field4, definitions.match.khash4, SelectionCriteria.MATCH, definitions.match.kname4)
                add(definitions.match.field6, definitions.match.khash6, SelectionCriteria.MATCH, definitions.match.kname6)
                add(definitions.match.field7, definitions.match.khash7, SelectionCriteria.MATCH, definitions.match.kname7)
                add(definitions.match.field8, definitions.match.khash8, SelectionCriteria.MATCH, definitions.match.kname8)
                add(definitions.match.field9, definitions.match.khash9, SelectionCriteria.MATCH, definitions.match.kname9)
                add(definitions.match.field10, definitions.match.khash10, SelectionCriteria.MATCH, definitions.match.kname10)

                add(definitions.classname.field1, definitions.classname.khash1, SelectionCriteria.CLASS_NAME, definitions.classname.kname1)

                add(definitions.regexp.field1, definitions.regexp.khash1, SelectionCriteria.LABEL_REGEXP, definitions.regexp.kname1)
                add(definitions.regexp.field2, definitions.regexp.khash2, SelectionCriteria.LABEL_REGEXP, definitions.regexp.kname2)
            }
        }
    }
}