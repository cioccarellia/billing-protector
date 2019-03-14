package com.andreacioccarelli.billingprotector.data

import com.andreacioccarelli.billingprotector.crypt.HashGeneratior

/**
 * Class representing a pirate app with built-in string sign check
 * */

data class PirateApp(
        override val field: String,
        override val hash: String,
        override val criteria: SelectionCriteria,
        override val name: String
) : AppDefinition() {

    init {
        val localComputedHash = HashGeneratior.hash(field)
        if (hash != localComputedHash) throw SecurityException("Mackage name checksum mismatch")
    }
}