package com.andreacioccarelli.billingprotector.utils

import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader

/**
 * Created by andrea on 2018/Jul.
 * Part of the package com.andreacioccarelli.billingprotector.utils
 */
internal object RootUtils {

    private const val INSPECTION_COMMAND = "which su"

    /**
     * Requests the root binary path by using unix which command, that
     * returns the path of a specified executable, in this case, su
     *
     * This is usually placed in directories like /system/bin,
     * /system/xbin, /su, etc..
     *
     * While checking the path, root access is not requested or logged
     *
     * @return  The path of the binary, or an empty string if
     *          nothing is found in the host system
     * */
    internal fun extractPath(): String {
        return try {
            val process = Runtime.getRuntime().exec(INSPECTION_COMMAND)

            val outputBuffer = StringBuffer()
            val bufferedReader = BufferedReader(InputStreamReader(process.inputStream))

            var line: String?

            do {
                line = bufferedReader.readLine()

                if (line == null)
                    break

                outputBuffer.append(line)
            } while (true)

            outputBuffer.toString()
        } catch (io: IOException) {
            ""
        } catch (nil: NullPointerException) {
            ""
        }
    }


    /**
     * Determines wheter or not the device has root access by
     * analyzing the path of the su binary.
     *
     * @return  A Boolean representing the device root state
     * */
    internal fun hasRootAccess(): Boolean {
        val path = extractPath()
        return when {
            path.isEmpty() -> false
            !path.contains("/") -> false
            !path.contains("su") -> false
            else -> true
        }
    }
}