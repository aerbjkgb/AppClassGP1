package com.example.swapapp.data.repository

import android.os.StrictMode
import java.sql.Connection
import java.sql.DriverManager
import java.sql.SQLException

object DatabaseManager {
    private const val URL = "jdbc:mysql://your-database-url:3306/your-database-name"
    private const val USER = "your-username"
    private const val PASSWORD = "your-password"

    init {
        // 允許在主執行緒進行網路操作（僅供範例，實際應使用協程或其他方式）
        val policy = StrictMode.ThreadPolicy.Builder().permitAll().build()
        StrictMode.setThreadPolicy(policy)
    }

    fun getConnection(): Connection? {
        return try {
            DriverManager.getConnection(URL, USER, PASSWORD)
        } catch (e: SQLException) {
            e.printStackTrace()
            null
        }
    }
}
