package com.example.core_utils.di_helpers.component_holder

abstract class LazyComponentHolder<T : DiComponent> : ComponentHolder<T> {

    @Volatile
    private var component: T? = null

    override fun get(): T {
        return component ?: error("Component not provided")
    }

    override fun set(component: T) {
        this.component = component
    }
}