package com.grp09.shelfstash.model

import java.time.LocalDate

// Data class representing a food item in the pantry
data class FoodItem(

    // Unique ID (can later come from database)
    val id: String,

    // Name of the food item (e.g., Milk, Rice)
    val name: String,

    // Category (Dairy, Grain, Meat, etc.)
    val category: String,

    // Quantity available
    val quantity: Int,

    // Expiry date of the item
    val expiryDate: LocalDate
)
