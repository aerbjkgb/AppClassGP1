package com.example.swapapp.ui.screens

import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WishlistScreen(navController: NavController) {
    Scaffold(
        topBar = {
            TopAppBar(title = { Text("通知") })
        }
    ) { paddingValues ->
        // wishlist blabla 12345678
        Text(
            text = "通知",
            modifier = Modifier.padding(paddingValues)
        )
    }
}
