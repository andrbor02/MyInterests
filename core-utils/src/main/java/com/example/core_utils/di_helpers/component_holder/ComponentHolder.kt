package com.example.core_utils.di_helpers.component_holder

interface ComponentHolder<T : DiComponent> {

    fun get(): T

    fun set(component: T)
}