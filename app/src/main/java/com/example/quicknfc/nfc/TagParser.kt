package com.example.quicknfc.nfc

import android.nfc.NfcAdapter
import android.nfc.Tag
import android.util.Log

class TagParser: NfcAdapter.ReaderCallback {

    override fun onTagDiscovered(tag: Tag) {
        Log.d("NFC", "Tag discovered: ${tag.techList.joinToString(", ")}")
    }
}