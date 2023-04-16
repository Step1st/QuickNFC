package com.example.quicknfc

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.quicknfc.nfc.TagController

class QuickNFCViewModel: ViewModel() {

    private val tagController: TagController = TagController()

    private var isNFCAvailable: MutableState<Boolean>  = mutableStateOf(false)

    fun getIsNFCAvailable(): State<Boolean> {
        return isNFCAvailable
    }

    fun setIsNFCAvailable(isAvailable: Boolean) {
        isNFCAvailable.value = isAvailable
    }

    fun getTagController(): TagController {
        return tagController
    }

}