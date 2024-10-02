package com.example.swapapp.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ExchangeScreen(navController: NavController, productId: String) {
    Scaffold(
        topBar = {
            TopAppBar(title = { Text("交換商品") })
        }
    ) { paddingValues ->
        Column(modifier = Modifier.padding(paddingValues).padding(16.dp)) {
            Text(text = "選擇要交換的商品", style = MaterialTheme.typography.titleMedium)
            Spacer(modifier = Modifier.height(8.dp))
            // TODO: 列出用戶已發布的產品供選擇
            // 範例：
            // LazyColumn {
            //     items(userProducts) { product ->
            //         ProductCard(product = product, onClick = { /* 選擇此產品 */ })
            //     }
            // }

            Spacer(modifier = Modifier.height(16.dp))
            Button(onClick = {
                navController.navigate("post_product")
            }) {
                Text("發布新商品進行交換")
            }
            Spacer(modifier = Modifier.height(16.dp))
            Button(onClick = {
                // 實現交換請求邏輯，通知原發佈者
                // 然後導航回主頁或其他頁面
                navController.popBackStack("market", false)
            }) {
                Text("確認交換")
            }
        }
    }
}
