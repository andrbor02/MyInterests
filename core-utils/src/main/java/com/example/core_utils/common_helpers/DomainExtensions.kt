package com.example.core_utils.common_helpers

import java.sql.Timestamp
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import java.util.TimeZone

val Any?.isNull: Boolean
    get() = this == null

val Any?.isNotNull: Boolean
    get() = this != null

fun <T> List<T>.replaceItem(index: Int, item: T): List<T> =
    this.toMutableList().apply {
        this[index] = item
    }

fun <T> List<T>.updateItem(item: T, transform: (T) -> T): List<T> =
    this.toMutableList().apply {
        val index = this.indexOf(item)
        if (index != -1) {
            this[index] = transform(item)
        }
    }

fun Int.getDateFromTimestamp(): String {
    val stamp = Timestamp(this.toLong() * 1000)
    val date = Date(stamp.time)
    val sdf = SimpleDateFormat("dd MM", Locale.getDefault())
    sdf.timeZone = TimeZone.getTimeZone("Europe/Moscow")
    val formatted = sdf.format(date)
    return formatted
}

fun Int.getTimeFromTimestamp(): String {
    val stamp = Timestamp(this.toLong() * 1000)
    val date = Date(stamp.time)
    val sdf = SimpleDateFormat("HH:mm", Locale.getDefault())
    sdf.timeZone = TimeZone.getTimeZone("Europe/Moscow")
    val formatted = sdf.format(date)
    return formatted
}

fun <T> MutableList<T>.replaceItem(oldItem: T, newItem: T) {
    this.apply {
        val index = this.indexOf(oldItem)
        if (index != -1) {
            this[index] = newItem
        }
    }
}

fun <T> MutableSet<T>.updateSetByReplacing(
    collection: Iterable<T>,
    updateCondition: (existingItem: T, newItem: T) -> Boolean,
) {
    collection.forEach { element ->
        val existingItem = find { updateCondition(it, element) }
        if (existingItem != null) {
            remove(existingItem)
        }
        add(element)
    }
}

fun <T> MutableSet<T>.replaceItem(oldItem: T, newItem: T) {
    this.apply {
        val index = this.indexOf(oldItem)
        if (index != -1) {
            this.remove(oldItem)
            this.add(newItem)
        }
    }
}