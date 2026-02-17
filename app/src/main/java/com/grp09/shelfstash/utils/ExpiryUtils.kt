package com.grp09.shelfstash.utils

import com.grp09.shelfstash.model.FoodItem
import java.time.LocalDate
import java.time.temporal.ChronoUnit

/**
 * ExpiryUtils
 *
 * AI Developer Responsibility:
 * - Implements intelligent freshness classification logic.
 *
 * Data Engineer Responsibility:
 * - Ensures consistent time-based calculations for food lifecycle tracking.
 */
object ExpiryUtils {

    // Configurable near-expiry threshold (removes magic numbers)
    private const val DEFAULT_NEAR_EXPIRY_DAYS = 3

    /**
     * Calculate number of days remaining before expiry.
     *
     * Single source of truth for temporal computation.
     */
    fun daysRemaining(foodItem: FoodItem): Long {
        val today = LocalDate.now()
        return ChronoUnit.DAYS.between(today, foodItem.expiryDate)
    }

    /**
     * Check if item is already expired.
     */
    fun isExpired(foodItem: FoodItem): Boolean {
        return daysRemaining(foodItem) < 0
    }

    /**
     * Check if item is near expiry within configurable threshold.
     */
    fun isNearExpiry(
        foodItem: FoodItem,
        thresholdDays: Int = DEFAULT_NEAR_EXPIRY_DAYS
    ): Boolean {
        val remaining = daysRemaining(foodItem)
        return remaining in 0..thresholdDays
    }

    /**
     * Returns human-readable freshness category.
     *
     * Used by the presentation layer to display expiry intelligence.
     */
    fun getExpiryStatus(
        foodItem: FoodItem,
        thresholdDays: Int = DEFAULT_NEAR_EXPIRY_DAYS
    ): String {

        val remaining = daysRemaining(foodItem)

        return when {
            remaining < 0 -> "Expired"
            remaining == 0L -> "Expires Today"
            remaining <= thresholdDays -> "Near Expiry"
            else -> "Fresh"
        }
    }
}
