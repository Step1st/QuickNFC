package com.example.quicknfc.nfc


import android.nfc.Tag
import android.nfc.tech.MifareClassic
import android.nfc.tech.NfcA
import android.util.Log


class TagController {

    fun getSak(tag: Tag): String? {
        if (tag.techList.contains("android.nfc.tech.NfcA")) {
            val nfcA = NfcA.get(tag)
            val sak = nfcA.sak
            return "0x${"%02x".format(sak)}"
        }
        return null
    }

    fun getAtqa(tag: Tag): String? {
        if (tag.techList.contains("android.nfc.tech.NfcA")) {
            val nfcA = NfcA.get(tag)
            val atqa = nfcA.atqa
            return "0x${"%04x".format((atqa[1].toInt().shl(8)+ atqa[0].toInt()))}"
        }
        return null
    }

    fun getTagMemorySize(tag: Tag): Int? {
        val techList = tag.techList


        return when {
            techList.contains("android.nfc.tech.MifareClassic") -> {
                val mifareClassic = MifareClassic.get(tag)
                mifareClassic.blockCount * MifareClassic.BLOCK_SIZE
            }
            techList.any { it in arrayOf("android.nfc.tech.Ndef", "android.nfc.tech.NdefFormatable") } -> {
                val nfcA = NfcA.get(tag)
                try {
                    nfcA.connect()

                    // Send the ISO/IEC 7816-4 SELECT command to the tag
                    val getVersion = byteArrayOf(0x30.toByte(), 0x03.toByte())
                    val response = nfcA.transceive(getVersion)
                    nfcA.close()

                    // Get the memory capacity of the tag from the response
                    val memorySize = response[2].toInt() * 8

                    // Print the memory size of the tag
                    Log.d("nfc", "Tag memory size: $memorySize bytes")
                    memorySize
                }
                catch (e: Exception) {
                    e.printStackTrace()
                    null
                }
            }
            else -> 57
        }
    }
}