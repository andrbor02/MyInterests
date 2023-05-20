package com.example.core_utils.di_helpers.component_holder

abstract class DataComponentHolder<T : DiComponent> : ComponentHolder<T> {

    @Volatile
    private var component: T? = null

    override fun get(): T {
        return component ?: synchronized(this) {
            component ?: build().also(::set)
        }
    }

    override fun set(component: T) {
        this.component = component
    }

    protected abstract fun build(): T
}