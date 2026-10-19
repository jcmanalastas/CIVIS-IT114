package com.civis.it114

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import com.civis.it114.ui.CivisNavigation
import com.civis.it114.ui.rememberCivisAppState
import com.civis.it114.ui.theme.CIVISTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            CIVISTheme {
                CivisApp()
            }
        }
    }
}

@Composable
fun CivisApp() {
    val appState = rememberCivisAppState()

    CivisNavigation(
        appState = appState
    )
}