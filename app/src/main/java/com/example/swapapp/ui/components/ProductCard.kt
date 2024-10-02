package com.example.swapapp.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.swapapp.data.models.Product
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow
import coil.compose.rememberImagePainter

@Composable
fun ProductCard(product: Product, onClick: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 8.dp)
            .clickable { onClick() },
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Row(modifier = Modifier.padding(8.dp)) {
            Image(
                painter = rememberImagePainter(data = product.imageUrl),
                contentDescription = product.title,
                modifier = Modifier.size(80.dp)
            )
            Spacer(modifier = Modifier.width(8.dp))
            Column(
                modifier = Modifier
                    .weight(1f)
                    .align(Alignment.CenterVertically)
            ) {
                Text(
                    text = product.title,
                    style = MaterialTheme.typography.titleMedium,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
                Text(text = "提供者：${product.providerName}", style = MaterialTheme.typography.bodyMedium)
                Text(text = "有效期限：${product.startDate} - ${product.endDate}", style = MaterialTheme.typography.bodyMedium)
            }
            var isFavorite by remember { mutableStateOf(product.isFavorite) }
            IconButton(onClick = {
                isFavorite = !isFavorite
                // 可在此處更新收藏狀態至資料庫
            }) {
                if (isFavorite) {
                    Icon(Icons.Filled.Favorite, contentDescription = "已收藏", tint = Color.Red)
                } else {
                    Icon(Icons.Outlined.FavoriteBorder, contentDescription = "未收藏")
                }
            }
        }
    }
}
