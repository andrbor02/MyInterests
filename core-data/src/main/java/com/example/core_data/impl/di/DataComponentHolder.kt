package com.example.core_data.impl.di

import com.example.core_data.api.DataComponent
import com.example.core_utils.di_helpers.component_holder.DataComponentHolder

object DataComponentHolder : DataComponentHolder<DataComponent>() {
    override fun build(): DataComponent {
        return DaggerDataComponentImpl.factory().create()
    }
}