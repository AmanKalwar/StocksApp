package com.example.kaninistocks

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class StockBuyingActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_stock_buying)

        val stockBuy = findViewById<Button>(R.id.buyStockButton)

        stockBuy.setOnClickListener{
            val intent = Intent(this, buyStockActivity::class.java)
            startActivity(intent)

        }
    }
}