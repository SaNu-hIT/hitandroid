package com.intellyze.recharge.utls

import android.app.Application
import java.security.MessageDigest
import java.util.*
import kotlin.experimental.and

class Utility {
    companion object {
        protected val hexArray = "0123456789ABCDEF".toCharArray()

        fun isNullOrEmpty(str: String?): Boolean {
            if (str != null && !str.isEmpty())
                return false
            return true
        }

        fun hashString(input: String?): String {
            val HEX_CHARS = "0123456789ABCDEF"
            val bytes = MessageDigest
                .getInstance("SHA-512")
                .digest(input?.toByteArray())
            val result = StringBuilder(bytes.size * 2)

            bytes.forEach {
                val i = it.toInt()
                result.append(HEX_CHARS[i shr 4 and 0x0f])
                result.append(HEX_CHARS[i and 0x0f])
            }

            return result.toString()
        }


        fun bytesToHex(bytes: ByteArray): String {
            val hexChars = CharArray(bytes.size * 2)
            for (j in bytes.indices) {
                val v = (bytes[j] and 0xFF.toByte()).toInt()

                hexChars[j * 2] = hexArray[v ushr 4]
                hexChars[j * 2 + 1] = hexArray[v and 0x0F]
            }
            return String(hexChars)
        }






    }
}