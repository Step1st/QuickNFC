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

    val tagMetadata by viewModel.tagMetadata.collectAsState()

    if (tagMetadata.techAvailable.isEmpty()) {
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
            TechAvailable(techAvailable = tagMetadata.techAvailable.asIterable())
            tagMetadata.ndefType?.let { DataFormat(type = it) }
            tagMetadata.memorySize?.let { MemorySize(memorySize = it) }
            tagMetadata.canMakeReadOnly?.let { CanMakeReadOnly(canMakeReadOnly = it) }
            tagMetadata.isWritable?.let { IsWritable(isWritable = it) }
            tagMetadata.sak?.let { Sak(sak = it) }
            tagMetadata.atqa?.let { Atqa(atqa = it) }
        }
    }
}



@Preview(showBackground = true)
@Composable
fun ReadScreenPreview() {
    ReadScreen(viewModel = QuickNFCViewModel())
}