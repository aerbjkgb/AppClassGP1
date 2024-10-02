package com.example.swapapp.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.swapapp.ui.components.SearchBar
import com.example.swapapp.ui.components.ProductCard
import com.example.swapapp.data.models.Product
import com.example.swapapp.data.repository.ProductRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MarketScreen(navController: NavController) {
    val productRepository = ProductRepository()
    val coroutineScope = rememberCoroutineScope()
    var products by remember { mutableStateOf(emptyList<Product>()) }

    LaunchedEffect(Unit) {
        coroutineScope.launch(Dispatchers.IO) {
            val productList = productRepository.getAllProducts()
            products = productList
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(title = { Text("市場") })
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            SearchBar(
                onSearch = { query ->
                    // filter products 列表 (搜索用)
                }
            )
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = "最受歡迎的商品",
                style = MaterialTheme.typography.displayLarge,
                modifier = Modifier.padding(start = 16.dp)
            )
            Spacer(modifier = Modifier.height(8.dp))
            LazyColumn(
                contentPadding = PaddingValues(horizontal = 16.dp)
            ) {
                items(products) { product ->
                    ProductCard(product = product, onClick = {
                        navController.navigate("product_detail/${product.productId}")
                    })
                }
            }
        }
    }
}
