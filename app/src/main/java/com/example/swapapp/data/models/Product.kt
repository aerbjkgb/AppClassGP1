package com.example.swapapp.data.models

data class Product(
    val productId: String,
    val userId: String,
    val providerName: String,
    val categoryId: String,
    val title: String,
    val description: String,
    val imageUrl: String,
    val startDate: String,
    val endDate: String,
    val isPublic: Boolean,
    val isFavorite: Boolean = false // 預設未收藏
)
