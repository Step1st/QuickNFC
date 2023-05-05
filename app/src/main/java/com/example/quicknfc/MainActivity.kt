package com.example.quicknfc

import android.content.Context
import android.content.Intent
import android.nfc.NfcAdapter
import android.nfc.NfcManager
import android.os.Bundle
import android.provider.Settings
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.example.quicknfc.ui.theme.QuickNFCTheme


class MainActivity : ComponentActivity() {

    private val viewModel: QuickNFCViewModel by viewModels()

    private lateinit var nfcAdapter: NfcAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            QuickNFCTheme(dynamicColor = true) {
                QuickNFCApp(
                    viewModel = viewModel,
                    onNfcSettingsClick = { startActivity(Intent(Settings.ACTION_NFC_SETTINGS)) }
                )
            }
        }

        initNfcAdapter()
    }

    private fun initNfcAdapter() {
        val nfcManager = getSystemService(Context.NFC_SERVICE) as NfcManager
        nfcAdapter = nfcManager.defaultAdapter

        if (nfcAdapter == null) {
            finish()
        }
    }

    override fun onPause() {
        super.onPause()
        if (nfcAdapter.isEnabled) {
            nfcAdapter.disableReaderMode(this)
        }
    }

    override fun onResume() {
        super.onResume()
        if (nfcAdapter.isEnabled) {
            viewModel.isNFCAvailable.value = true
            nfcAdapter.enableReaderMode(this, viewModel::onTagDiscovered, NfcAdapter.FLAG_READER_NFC_A or NfcAdapter.FLAG_READER_NFC_B , null)
        }
        else {
            viewModel.isNFCAvailable.value = false
        }
    }
}


@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    QuickNFCTheme() {
        QuickNFCApp(viewModel = QuickNFCViewModel(), onNfcSettingsClick = { })
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreviewDark() {
    QuickNFCTheme(darkTheme = true) {
        QuickNFCApp(viewModel = QuickNFCViewModel(), onNfcSettingsClick = { })
    }
}
