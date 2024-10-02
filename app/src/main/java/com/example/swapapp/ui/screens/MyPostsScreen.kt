package com.example.swapapp.ui.screens

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyPostsScreen(navController: NavController) {
    Scaffold(
        topBar = {
            TopAppBar(title = { Text("我的帖子") })
        }
    ) { paddingValues ->
        // blabla TODO
        Text(
            text = "我的帖子123",
            modifier = Modifier.padding(paddingValues)
        )
    }
}
