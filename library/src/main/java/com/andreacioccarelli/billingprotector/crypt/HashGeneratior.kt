package com.andreacioccarelli.billingprotector.crypt

import java.security.MessageDigest
import kotlin.experimental.and


/**
 * Designed and Developed by Andrea Cioccarelli
 */

object HashGeneratior {
    private const val HASH_METHOD = "MD5"

    fun hash(input: String): String {
        val md = MessageDigest.getInstance(HASH_METHOD)
        md.update(input.toByteArray())

        val digest = md.digest()
        val buffer = StringBuffer()
        val magicByte = 0xff.toByte()

        for (b in digest) {
            buffer.append(String.format("%02x", b and magicByte))
        }

        return buffer.toString()
    }
}