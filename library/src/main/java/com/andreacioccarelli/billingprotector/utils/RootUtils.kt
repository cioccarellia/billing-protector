package com.andreacioccarelli.billingprotector.utils

import java.io.BufferedReader
import java.io.InputStreamReader

/**
 * Created by andrea on 2018/Jul.
 * Part of the package com.andreacioccarelli.billingprotector.utils
 */
object RootUtils {

    private val path: String
        get() {
            return try {
                val process = Runtime.getRuntime().exec("which su")

                val output = StringBuffer()
                val buffer = BufferedReader(InputStreamReader(process.inputStream))

                var l: String?
                do {
                    l = buffer.readLine()

                    if (l == null)
                        break

                    output.append(l)
                } while (true)

                output.toString()
            } catch (io: Exception) {
                ""
            }
        }

    val hasRootAccess: Boolean
        get() {
            val path = path
            if (path.length == 0) return false
            if (path == "") return false
            if (!path.contains("/")) return false
            if (!path.contains("su")) return false

            return true
        }
}