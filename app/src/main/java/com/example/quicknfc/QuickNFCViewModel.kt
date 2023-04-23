package com.example.quicknfc

import android.nfc.Tag
import android.nfc.tech.MifareUltralight
import android.nfc.tech.Ndef
import androidx.lifecycle.ViewModel
import com.example.quicknfc.nfc.TagController
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class QuickNFCViewModel: ViewModel() {

    enum class Mode {
        Read,
        Write,
        Copy
    }

    var mode = Mode.Read

    private val tagController = TagController()

    var isNFCAvailable = MutableStateFlow(false)

    private var _techAvailable = MutableStateFlow(arrayOf<String>())
    private var _isWritable = MutableStateFlow<Boolean?>(null)
    private var _canMakeReadOnly = MutableStateFlow<Boolean?>(null)
    private var _ndefType = MutableStateFlow<String?>(null)
    private var _memorySize = MutableStateFlow<Int?>(null)
    private var _sak = MutableStateFlow<String?>(null)
    private var _atqa = MutableStateFlow<String?>(null)

    val techAvailable = _techAvailable.asStateFlow()
    val isWritable = _isWritable.asStateFlow()
    val canMakeReadOnly = _canMakeReadOnly.asStateFlow()
    val ndefType = _ndefType.asStateFlow()
    val memorySize = _memorySize.asStateFlow()
    val sak = _sak.asStateFlow()
    val atqa = _atqa.asStateFlow()


    private fun getNdefInfo(tag: Tag) {
        clearNdefInfo()

        val ndef = Ndef.get(tag)
        if (ndef != null) {
            _isWritable.value = ndef.isWritable
            _canMakeReadOnly.value = ndef.canMakeReadOnly()
            _ndefType.value = getNdefTagType(ndef.type)
        }
        _techAvailable.value = separateName(tag.techList.asIterable())
        _memorySize.value = tagController.getTagMemorySize(tag)
        _sak.value = tagController.getSak(tag)
        _atqa.value = tagController.getAtqa(tag)
    }

    fun onTagDiscovered(tag: Tag) {
        getNdefInfo(tag)
    }

    private fun separateName(list: Iterable<String>): Array<String> {
        return list.map { it.split(".").last() }.toTypedArray()
    }

    private fun getNdefTagType(type: String): String {
        return when (type) {
            Ndef.NFC_FORUM_TYPE_1 -> "NFC Forum Type 1"
            Ndef.NFC_FORUM_TYPE_2 -> "NFC Forum Type 2"
            Ndef.NFC_FORUM_TYPE_3 -> "NFC Forum Type 3"
            Ndef.NFC_FORUM_TYPE_4 -> "NFC Forum Type 4"
            Ndef.MIFARE_CLASSIC -> "Mifare Classic"
            else -> ""
        }
    }

    private fun clearNdefInfo() {
        _isWritable.value = null
        _canMakeReadOnly.value = null
        _ndefType.value = null
        _memorySize.value = null
        _sak.value = null
        _atqa.value = null
    }

}