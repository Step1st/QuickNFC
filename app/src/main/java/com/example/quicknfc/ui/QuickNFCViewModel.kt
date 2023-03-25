package com.example.quicknfc.ui

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel

class QuickNFCViewModel: ViewModel() {

    private var IsNFCAvailable: MutableState<Boolean>  = mutableStateOf(false)

    fun getIsNFCAvailable(): State<Boolean> {
        return IsNFCAvailable
    }

    fun setIsNFCAvailable(isAvailable: Boolean) {
        IsNFCAvailable.value = isAvailable
    }

}