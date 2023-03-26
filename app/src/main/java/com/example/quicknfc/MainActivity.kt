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
import android.util.Log
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
    private lateinit var nfcPendingIntent: PendingIntent
    private lateinit var intentFilters: Array<IntentFilter>
    private lateinit var techLists: Array<Array<String>>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            QuickNFCTheme(dynamicColor = false) {
                QuickNFCApp(
                    isNfcAvailable = { viewModel.getIsNFCAvailable() }
                )
            }
        }

        initNfcAdapter()

        nfcAdapter = NfcAdapter.getDefaultAdapter(this)
//        if (nfcAdapter == null) {
//            viewModel.setIsNFCAvailable(false)
//        } else {
//            if (!nfcAdapter.isEnabled) {
//                viewModel.setIsNFCAvailable(false)
//            } else {
//                viewModel.setIsNFCAvailable(true)
//                val intent = Intent(this, javaClass).addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP)
//
//                nfcAdapter = NfcAdapter.getDefaultAdapter(this)
//                nfcIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_MUTABLE)
//            }
//        }

    }


    private fun initNfcAdapter() {
        val nfcManager = getSystemService(Context.NFC_SERVICE) as NfcManager
        nfcAdapter = nfcManager.defaultAdapter

        val intent = Intent(this, javaClass).addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP)
//        intent.action = NfcAdapter.ACTION_TECH_DISCOVERED
//        intent.action = NfcAdapter.ACTION_TAG_DISCOVERED

//        if (Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M) {
//            nfcPendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_MUTABLE)
//        }
//        else {
//            nfcPendingIntent = PendingIntent.getActivity(this, 0, intent, 0)
//        }
        nfcPendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_MUTABLE)
        intentFilters = arrayOf(
            IntentFilter(NfcAdapter.ACTION_NDEF_DISCOVERED),
            IntentFilter(NfcAdapter.ACTION_TECH_DISCOVERED),
            IntentFilter(NfcAdapter.ACTION_TAG_DISCOVERED)
        )

        techLists = arrayOf(
            arrayOf(IsoDep::class.java.name),
            arrayOf(NfcA::class.java.name),
            arrayOf(NfcB::class.java.name),
            arrayOf(NfcF::class.java.name),
            arrayOf(NfcV::class.java.name),
            arrayOf(NdefFormatable::class.java.name),
            arrayOf(MifareClassic::class.java.name),
            arrayOf(MifareUltralight::class.java.name)
            )
    }

    override fun onNewIntent(intent: Intent) {
        super.onNewIntent(intent)
        Log.d("NFC", "onNewIntent")
        Log.d("NFC", "Action: $intent")

        when (intent.action) {
            NfcAdapter.ACTION_NDEF_DISCOVERED -> {
                Log.d("NFC", "NDEF_DISCOVERED")
            }
            NfcAdapter.ACTION_TECH_DISCOVERED -> {
//                val tag : Tag? = intent.getParcelableExtra(NfcAdapter.EXTRA_TAG)
                Log.d("NFC", "TECH_DISCOVERED")
            }
            NfcAdapter.ACTION_TAG_DISCOVERED -> {
                Log.d("NFC", "TAG_DISCOVERED")
            }
        }

    }

    override fun onPause() {
        super.onPause()
        if (nfcAdapter.isEnabled) {
            nfcAdapter.disableForegroundDispatch(this)
        }
    }

    override fun onResume() {
        super.onResume()
        if (nfcAdapter.isEnabled) {
            viewModel.setIsNFCAvailable(true)
            nfcAdapter.enableForegroundDispatch(this, nfcPendingIntent, intentFilters, techLists)
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
