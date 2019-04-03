package com.andreacioccarelli.billingprotector.crypt

import java.security.MessageDigest
import kotlin.experimental.and


/**
 * Designed and Developed by Andrea Cioccarelli
 */

object HashGeneratior {
    fun hash(input: String): String {
        val md = MessageDigest.getInstance("MD5")
        md.update(input.toByteArray())
        val digest = md.digest()
        val sb = StringBuffer()
        for (b in digest) {
            sb.append(String.format("%02x", b and 0xff.toByte()))
        }

        return sb.toString()
    }
}