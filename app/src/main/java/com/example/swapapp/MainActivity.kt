package com.example.swapapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.swapapp.navigation.NavGraph
import com.example.swapapp.ui.theme.SwapAppTheme


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SwapAppTheme {
                NavGraph()
            }
        }
    }
}

