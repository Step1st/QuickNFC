package com.example.quicknfc

import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.nfc.NfcAdapter
import android.nfc.NfcManager
import android.nfc.Tag
import android.nfc.tech.*
import android.os.Build
import android.os.Bundle
import android.provider.Settings
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.tooling.preview.Preview
import com.example.quicknfc.nfc.TagParser
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
                    isNfcAvailable = viewModel.getIsNFCAvailable(),
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
            viewModel.setIsNFCAvailable(true)
            nfcAdapter.enableReaderMode(this, TagParser(), NfcAdapter.FLAG_READER_NFC_A or NfcAdapter.FLAG_READER_NFC_B , null)
        }
        else {
            viewModel.setIsNFCAvailable(false)
        }
    }
}


@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    QuickNFCTheme() {
        QuickNFCApp(isNfcAvailable = remember{ mutableStateOf(true) }, onNfcSettingsClick = { })
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreviewDark() {
    QuickNFCTheme(darkTheme = true) {
        QuickNFCApp(isNfcAvailable = remember{ mutableStateOf(true) }, onNfcSettingsClick = { })
    }
}
