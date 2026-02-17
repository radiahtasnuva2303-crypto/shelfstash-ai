package com.grp09.shelfstash

import com.grp09.shelfstash.model.FoodItem
import com.grp09.shelfstash.utils.ExpiryUtils
import java.time.LocalDate
import android.widget.TextView

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.util.Log


class HomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        val milk = FoodItem(
            id = "1",
            name = "Milk",
            category = "Dairy",
            quantity = 1,
            expiryDate = LocalDate.now().plusDays(2)
        )

        val status = ExpiryUtils.getExpiryStatus(milk)

        val expiryText = findViewById<TextView>(R.id.expiryStatusText)
        expiryText.text = "Expiry Status: $status"
    }

}