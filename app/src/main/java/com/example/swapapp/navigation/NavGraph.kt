package com.example.swapapp.navigation

import BottomNavigationBar
import NavItem
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.*
import com.example.swapapp.ui.screens.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.navigation.navArgument

@Composable
fun NavGraph() {
    val navController = rememberNavController()
    val items = listOf(
        NavItem("market", Icons.Default.Home, "市場"),
        NavItem("wishlist", Icons.Default.Favorite, "願望清單"),
        NavItem("my_posts", Icons.Default.List, "我的帖子"),
        NavItem("notifications", Icons.Default.Notifications, "通知"),
        NavItem("account", Icons.Default.Person, "我的帳戶")
    )

    Scaffold(
        bottomBar = {
            BottomNavigationBar(navController = navController, items = items)
        }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = "market",
            modifier = Modifier.padding(innerPadding)
        ) {
            composable("market") { MarketScreen(navController) }
            composable("wishlist") { WishlistScreen(navController) }
            composable("my_posts") { MyPostsScreen(navController) }
            composable("notifications") { NotificationsScreen(navController) }
            composable("account") { AccountScreen(navController) }
            composable(
                "product_detail/{productId}",
                arguments = listOf(navArgument("productId") { type = NavType.StringType })
            ) { backStackEntry ->
                val productId = backStackEntry.arguments?.getString("productId") ?: ""
                ProductDetailScreen(navController, productId)
            }
            composable(
                "exchange/{productId}",
                arguments = listOf(navArgument("productId") { type = NavType.StringType })
            ) { backStackEntry ->
                val productId = backStackEntry.arguments?.getString("productId") ?: ""
                ExchangeScreen(navController, productId)
            }
            composable("post_product") {
                PostProductScreen(navController)
            }
        }
    }
}
