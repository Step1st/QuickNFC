package com.example.quicknfc

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

    var isNFCAvailable = MutableStateFlow(false)

    private var _status = MutableStateFlow(Status.Reading)
    val status = _status.asStateFlow()

    private var _mode = MutableStateFlow(NfcMode.Read)
    val mode = _mode.asStateFlow()

    private var _tagMetadata = MutableStateFlow(TagMetadata(arrayOf<String>(), null, null, null, null, null, null))
    val tagMetadata = _tagMetadata.asStateFlow()

    private fun readTag(tag: Tag) {
        val newTagMetadata = TagMetadata(arrayOf<String>(), null, null, null, null, null, null)

        newTagMetadata.techAvailable = tagMetadataReader.getTechAvailable(tag)
        newTagMetadata.memorySize = tagMetadataReader.getTagMemorySize(tag)
        newTagMetadata.sak = tagMetadataReader.getSak(tag)
        newTagMetadata.atqa = tagMetadataReader.getAtqa(tag)
        newTagMetadata.isWritable = tagMetadataReader.getIsWritable(tag)
        newTagMetadata.canMakeReadOnly = tagMetadataReader.getCanMakeReadOnly(tag)
        newTagMetadata.ndefType = tagMetadataReader.getNdefTagType(tag)

        _tagMetadata.value = newTagMetadata
    }

    fun onTagDiscovered(tag: Tag) {
        when (_mode.value) {
            NfcMode.Read -> {
                readTag(tag)
            }
            NfcMode.Write -> {

                _mode.value = NfcMode.Read
            }
            NfcMode.Copy -> {

                _mode.value = NfcMode.Read
            }
        }
    }

    fun writeText(text: String) {
        _status.value = Status.Writing
        _mode.value = NfcMode.Write
    }

    fun cancelWrite() {
        _status.value = Status.Reading
        _mode.value = NfcMode.Read
    }

}