package com.example.core_utils_android.extensions

import android.content.Context
import android.util.TypedValue
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.core.content.ContextCompat
import androidx.core.view.marginBottom
import androidx.core.view.marginEnd
import androidx.core.view.marginStart
import androidx.core.view.marginTop

fun Float.sp(context: Context) = TypedValue.applyDimension(
    TypedValue.COMPLEX_UNIT_SP,
    this,
    context.resources.displayMetrics
)

fun Context.showKeyboard(inputView: View) {
    inputView.requestFocus()
    val imm = ContextCompat.getSystemService(this, InputMethodManager::class.java)
    imm?.showSoftInput(inputView, 0)
}

fun Context.hideKeyboard(rootView: View) {
    val imm = ContextCompat.getSystemService(this, InputMethodManager::class.java)
    imm?.hideSoftInputFromWindow(rootView.windowToken, 0)
}

fun View.hide() {
    this.visibility = View.GONE
}

fun View.show() {
    this.visibility = View.VISIBLE
}

fun View.enable() {
    this.isEnabled = true
}

fun View.disable() {
    this.isEnabled = false
}

// Padding
//________________________________________________________________________________
/// Immutable
///___________________________________________
val View.paddingHorizontal: Int
    get() = paddingStart + paddingEnd

val View.paddingVertical: Int
    get() = paddingTop + paddingBottom
/// Mutable
///___________________________________________

var View.mutablePaddingStart: Int
    get() = paddingStart
    set(value) = setPadding(value, paddingTop, paddingEnd, paddingBottom)

var View.mutablePaddingTop: Int
    get() = paddingTop
    set(value) = setPadding(paddingStart, value, paddingEnd, paddingBottom)

var View.mutablePaddingEnd: Int
    get() = paddingEnd
    set(value) = setPadding(paddingStart, paddingTop, value, paddingBottom)

var View.mutablePaddingBottom: Int
    get() = paddingBottom
    set(value) = setPadding(paddingStart, paddingTop, paddingEnd, value)

var View.mutablePaddingVertical: Int
    get() = paddingTop + paddingBottom
    set(value) = setPadding(paddingStart, value, paddingEnd, value)

var View.mutablePaddingHorizontal: Int
    get() = paddingStart + paddingEnd
    set(value) = setPadding(value, paddingTop, value, paddingBottom)
//___________________________________________
//________________________________________________________________________________


// Margin
//________________________________________________________________________________
/// Immutable
///___________________________________________
val View.marginHorizontal: Int
    get() = marginStart + marginEnd

val View.marginVertical: Int
    get() = marginTop + marginBottom

/// Mutable
///___________________________________________
var ViewGroup.MarginLayoutParams.mutableMarginStart: Int
    get() = leftMargin
    set(value) = setMargins(value, topMargin, rightMargin, bottomMargin)

var ViewGroup.MarginLayoutParams.mutableMarginTop: Int
    get() = topMargin
    set(value) = setMargins(leftMargin, value, rightMargin, bottomMargin)

var ViewGroup.MarginLayoutParams.mutableMarginEnd: Int
    get() = rightMargin
    set(value) = setMargins(leftMargin, topMargin, value, bottomMargin)

var ViewGroup.MarginLayoutParams.mutableMarginBottom: Int
    get() = bottomMargin
    set(value) = setMargins(leftMargin, topMargin, rightMargin, value)


var ViewGroup.MarginLayoutParams.mutableMarginHorizontal: Int
    get() = leftMargin + rightMargin
    set(value) = setMargins(value, topMargin, value, bottomMargin)

var ViewGroup.MarginLayoutParams.mutableMarginVertical: Int
    get() = topMargin + bottomMargin
    set(value) = setMargins(leftMargin, value, rightMargin, value)
//________________________________________________________________________________
