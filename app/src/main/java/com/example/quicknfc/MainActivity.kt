package com.example.quicknfc

import android.nfc.NfcAdapter
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.tooling.preview.Preview
import com.example.quicknfc.ui.QuickNFCViewModel
import com.example.quicknfc.ui.theme.QuickNFCTheme


class MainActivity : ComponentActivity() {

    private val viewModel: QuickNFCViewModel by viewModels()

    private lateinit var nfcAdapter: NfcAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            QuickNFCTheme(dynamicColor = false) {
                QuickNFCApp(
                    isNfcAvailable = { viewModel.getIsNFCAvailable() }
                )
            }
        }

        nfcAdapter = NfcAdapter.getDefaultAdapter(this)
        if (nfcAdapter == null) {
            viewModel.setIsNFCAvailable(false)
        } else {
            if (!nfcAdapter.isEnabled) {
                viewModel.setIsNFCAvailable(false)
            } else {
                viewModel.setIsNFCAvailable(true)
            }
        }

    }
}


@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    QuickNFCTheme() {
        QuickNFCApp(isNfcAvailable = { mutableStateOf(true) })
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreviewDark() {
    QuickNFCTheme(darkTheme = true) {
        QuickNFCApp(isNfcAvailable = { mutableStateOf(true) })
    }
}
