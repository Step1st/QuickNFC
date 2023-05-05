package com.example.quicknfc.nfc

import android.nfc.NdefMessage
import android.nfc.Tag
import android.nfc.tech.Ndef
import android.nfc.tech.NdefFormatable

class TagWriter {
    fun writeToTag(tag: Tag, ndefMessage: NdefMessage): Boolean {
        try {
            if (tag.techList.contains("android.nfc.tech.Ndef")) {
                val tech = Ndef.get(tag)
                tech.connect()
                tech.writeNdefMessage(ndefMessage)
                tech.close()
                return true
            }
            else if (tag.techList.contains("android.nfc.tech.NdefFormatable")) {
                val tech = NdefFormatable.get(tag)
                tech.connect()
                tech.format(ndefMessage)
                tech.close()
                return true
            }
            else {
                return false
            }
        }
        catch (e: Exception) {
            e.printStackTrace()
            return false
        }
    }
}