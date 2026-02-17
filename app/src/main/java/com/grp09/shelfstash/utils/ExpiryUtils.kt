package com.grp09.shelfstash.utils

import com.grp09.shelfstash.model.FoodItem
import java.time.LocalDate
import java.time.temporal.ChronoUnit

// Utility object responsible for expiry-related calculations
object ExpiryUtils {

    // Calculate number of days remaining before expiry
    fun daysRemaining(foodItem: FoodItem): Long {
        val today = LocalDate.now()
        return ChronoUnit.DAYS.between(today, foodItem.expiryDate)
    }

    // Check if item is already expired
    fun isExpired(foodItem: FoodItem): Boolean {
        return daysRemaining(foodItem) < 0
    }

    // Check if item is near expiry (within threshold days)
    fun isNearExpiry(foodItem: FoodItem, thresholdDays: Int = 3): Boolean {
        val remaining = daysRemaining(foodItem)
        return remaining in 0..thresholdDays
    }

    // Get readable expiry status
    fun getExpiryStatus(foodItem: FoodItem): String {
        val remaining = daysRemaining(foodItem)

        return when {
            remaining < 0 -> "Expired"
            remaining == 0L -> "Expires Today"
            remaining <= 3 -> "Near Expiry"
            else -> "Fresh"
        }
    }
}
