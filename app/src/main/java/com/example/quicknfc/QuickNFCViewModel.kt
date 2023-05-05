package com.example.quicknfc

import android.nfc.NdefMessage
import android.nfc.NdefRecord
import android.nfc.Tag
import androidx.lifecycle.ViewModel
import com.example.quicknfc.nfc.NfcMode
import com.example.quicknfc.nfc.TagMetadataReader
import com.example.quicknfc.nfc.TagMetadata
import com.example.quicknfc.nfc.TagPayloadReader
import com.example.quicknfc.nfc.TagWriter
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class QuickNFCViewModel(
    private val tagMetadataReader: TagMetadataReader = TagMetadataReader(),
    private val tagPayloadReader: TagPayloadReader = TagPayloadReader(),
    private val tagWriter: TagWriter = TagWriter(),
): ViewModel() {

    enum class Status {
        Reading,
        Writing,
        Error
    }

    var NdefMessage: NdefMessage? = null

    var isNFCAvailable = MutableStateFlow(false)

    private var _status = MutableStateFlow(Status.Reading)
    val status = _status.asStateFlow()

    private var mode = NfcMode.Read

    private var _tagMetadata = MutableStateFlow(TagMetadata(arrayOf(), null, null, null, null, null, null))
    val tagMetadata = _tagMetadata.asStateFlow()

    private fun readTag(tag: Tag) {
        val newTagMetadata = TagMetadata(arrayOf(), null, null, null, null, null, null)

        newTagMetadata.techAvailable = tagMetadataReader.getTechAvailable(tag)
        newTagMetadata.memorySize = tagMetadataReader.getTagMemorySize(tag)
        newTagMetadata.sak = tagMetadataReader.getSak(tag)
        newTagMetadata.atqa = tagMetadataReader.getAtqa(tag)
        newTagMetadata.isWritable = tagMetadataReader.getIsWritable(tag)
        newTagMetadata.canMakeReadOnly = tagMetadataReader.getCanMakeReadOnly(tag)
        newTagMetadata.ndefType = tagMetadataReader.getNdefTagType(tag)

        _tagMetadata.value = newTagMetadata
    }

    private fun writeToTag(tag: Tag) {
        if (tagWriter.writeToTag(tag, NdefMessage!!)) {
            _status.value = Status.Reading
        }
        else {
            _status.value = Status.Error
        }
        mode = NfcMode.Read
    }

    fun writeText(text: String) {
        NdefRecord.createTextRecord(null, text).let {
            NdefMessage = NdefMessage(it)
        }
        _status.value = Status.Writing
        mode = NfcMode.Write
    }

    fun writeUri(uri: String) {
        NdefRecord.createUri(uri).let {
            NdefMessage = NdefMessage(it)
        }
        _status.value = Status.Writing
        mode = NfcMode.Write
    }

    fun cancelWrite() {
        mode = NfcMode.Read
        _status.value = Status.Reading
    }

    fun onTagDiscovered(tag: Tag) {
        when (mode) {
            NfcMode.Read -> {
                readTag(tag)
            }
            NfcMode.Write -> {
                writeToTag(tag)
            }
            NfcMode.Copy -> {
                mode = NfcMode.Read
            }
        }
    }
}