package com.example.swapapp.ui.screens
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.swapapp.data.models.Product
import com.example.swapapp.data.repository.ProductRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProductDetailScreen(navController: NavController, productId: String) {
    val productRepository = ProductRepository()
    val coroutineScope = rememberCoroutineScope()
    var product by remember { mutableStateOf<Product?>(null) }

    LaunchedEffect(productId) {
        coroutineScope.launch(Dispatchers.IO) {
            val result = productRepository.getProductById(productId)
            product = result
        }
    }

    product?.let { product ->
        Scaffold(
            topBar = {
                TopAppBar(title = { Text(product.title) })
            }
        ) { paddingValues ->
            Column(modifier = Modifier.padding(paddingValues).padding(16.dp)) {
                AsyncImage(
                    model = product.imageUrl,
                    contentDescription = product.title,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(200.dp)
                )
                Spacer(modifier = Modifier.height(16.dp))
                Text(text = "提供者：${product.providerName}", style = MaterialTheme.typography.titleMedium)
                Text(text = "有效期限：${product.startDate} - ${product.endDate}", style = MaterialTheme.typography.titleMedium)
                Spacer(modifier = Modifier.height(16.dp))
                Text(text = product.description)
                Spacer(modifier = Modifier.height(16.dp))
                Button(
                    onClick = {
                        navController.navigate("exchange/${product.productId}")
                    },
                    modifier = Modifier.align(Alignment.CenterHorizontally)
                ) {
                    Text("交换")
                }
            }
        }
    } ?: run {
        // 显示加载或错误状态
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            CircularProgressIndicator()
        }
    }
}
