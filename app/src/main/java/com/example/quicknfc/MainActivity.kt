package com.example.quicknfc

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.example.quicknfc.ui.theme.QuickNFCTheme


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            QuickNFCTheme(dynamicColor = false) {
                QuickNFCApp()
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    QuickNFCTheme() {
        QuickNFCApp()
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreviewDark() {
    QuickNFCTheme(darkTheme = true) {
        QuickNFCApp()
    }
}
