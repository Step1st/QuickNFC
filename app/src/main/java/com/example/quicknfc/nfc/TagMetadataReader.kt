package com.example.quicknfc.nfc


import android.nfc.Tag
import android.nfc.tech.MifareClassic
import android.nfc.tech.Ndef
import android.nfc.tech.NfcA
import android.util.Log


class TagMetadataReader {
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
            else -> null
        }
    }

    fun getIsWritable(tag: Tag): Boolean? {
        if (tag.techList.contains("android.nfc.tech.Ndef")) {
            val ndef = Ndef.get(tag)
            return ndef.isWritable
        }
        return null
    }

    fun getCanMakeReadOnly(tag: Tag): Boolean? {
        if (tag.techList.contains("android.nfc.tech.Ndef")) {
            val ndef = Ndef.get(tag)
            return ndef.canMakeReadOnly()
        }
        return null
    }

    fun getNdefTagType(tag: Tag): String? {
        if (tag.techList.contains("android.nfc.tech.Ndef")) {
            val ndef = Ndef.get(tag)
            return when (ndef.type) {
                Ndef.NFC_FORUM_TYPE_1 -> "NFC Forum Type 1"
                Ndef.NFC_FORUM_TYPE_2 -> "NFC Forum Type 2"
                Ndef.NFC_FORUM_TYPE_3 -> "NFC Forum Type 3"
                Ndef.NFC_FORUM_TYPE_4 -> "NFC Forum Type 4"
                Ndef.MIFARE_CLASSIC -> "Mifare Classic"
                else -> ""
            }
        }
        return null
    }

    fun getTechAvailable(tag: Tag): Array<String> {
        return separateName(tag.techList)
    }

    private fun separateName(list: Array<String>): Array<String> {
        return list.map { it.split(".").last() }.toTypedArray()
    }


}