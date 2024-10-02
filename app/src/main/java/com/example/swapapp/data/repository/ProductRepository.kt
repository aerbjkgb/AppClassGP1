package com.example.swapapp.data.repository

import com.example.swapapp.data.models.Product
import java.sql.ResultSet
import java.sql.SQLException
import java.util.*

class ProductRepository {
    fun getAllProducts(): List<Product> {
        val products = mutableListOf<Product>()
        val connection = DatabaseManager.getConnection()
        val statement = connection?.createStatement()
        try {
            val resultSet = statement?.executeQuery("SELECT * FROM Product")
            while (resultSet?.next() == true) {
                products.add(mapToProduct(resultSet))
            }
        } catch (e: SQLException) {
            e.printStackTrace()
        } finally {
            statement?.close()
            connection?.close()
        }
        return products
    }

    fun getProductById(productId: String): Product? {
        val connection = DatabaseManager.getConnection()
        val statement = connection?.prepareStatement("SELECT * FROM Product WHERE product_id = ?")
        try {
            statement?.setString(1, productId)
            val resultSet = statement?.executeQuery()
            if (resultSet?.next() == true) {
                return mapToProduct(resultSet)
            }
        } catch (e: SQLException) {
            e.printStackTrace()
        } finally {
            statement?.close()
            connection?.close()
        }
        return null
    }

    fun addProduct(product: Product) {
        val connection = DatabaseManager.getConnection()
        val sql = "INSERT INTO Product (product_id, user_id, category_id, title, description, image_url, start_date, end_date, is_public) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)"
        val statement = connection?.prepareStatement(sql)
        try {
            statement?.setString(1, product.productId)
            statement?.setString(2, product.userId)
            statement?.setString(3, product.categoryId)
            statement?.setString(4, product.title)
            statement?.setString(5, product.description)
            statement?.setString(6, product.imageUrl)
            statement?.setString(7, product.startDate)
            statement?.setString(8, product.endDate)
            statement?.setBoolean(9, product.isPublic)
            statement?.executeUpdate()
        } catch (e: SQLException) {
            e.printStackTrace()
        } finally {
            statement?.close()
            connection?.close()
        }
    }

    private fun mapToProduct(resultSet: ResultSet): Product {
        return Product(
            productId = resultSet.getString("product_id"),
            userId = resultSet.getString("user_id"),
            providerName = resultSet.getString("provider_name"),
            categoryId = resultSet.getString("category_id"),
            title = resultSet.getString("title"),
            description = resultSet.getString("description"),
            imageUrl = resultSet.getString("image_url"),
            startDate = resultSet.getString("start_date"),
            endDate = resultSet.getString("end_date"),
            isPublic = resultSet.getBoolean("is_public"),
            isFavorite = resultSet.getBoolean("is_favorite") // 假設資料庫有此欄位
        )
    }
}
