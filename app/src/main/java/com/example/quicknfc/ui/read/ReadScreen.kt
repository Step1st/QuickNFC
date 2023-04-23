package com.example.quicknfc.ui.screen

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.quicknfc.QuickNFCViewModel
import com.example.quicknfc.ui.read.components.*

@Composable
fun ReadScreen(viewModel: QuickNFCViewModel) {

    val techAvailable by viewModel.techAvailable.collectAsState()
    val canMakeReadOnly by viewModel.canMakeReadOnly.collectAsState()
    val isWritable by viewModel.isWritable.collectAsState()
    val type by viewModel.ndefType.collectAsState()
    val memorySize by viewModel.memorySize.collectAsState()
    val sak by viewModel.sak.collectAsState()
    val atqa by viewModel.atqa.collectAsState()



    if (techAvailable.isEmpty()) {
        Column(
            modifier = Modifier
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(text = "No NFC tag detected")
        }
    } else {
        Column(
            modifier = Modifier
                .fillMaxSize(),
        ) {
            TechAvailable(techAvailable = techAvailable.asIterable())
            type?.let { DataFormat(type = it) }
            memorySize?.let { MemorySize(memorySize = it) }
            canMakeReadOnly?.let { CanMakeReadOnly(canMakeReadOnly = it) }
            isWritable?.let { IsWritable(isWritable = it) }
            sak?.let { Sak(sak = it) }
            atqa?.let { Atqa(atqa = it) }
        }
    }
}



@Preview(showBackground = true)
@Composable
fun ReadScreenPreview() {
    ReadScreen(viewModel = QuickNFCViewModel())
}