package com.example.feature_streams.impl.di

import com.example.core_utils.di_helpers.component_holder.DataComponentHolder
import com.example.feature_streams.api.StreamsComponent
import com.example.feature_streams.api.StreamsDependencies

object StreamsComponentHolder : DataComponentHolder<StreamsComponent>() {
    override fun build(): StreamsComponent =
        DaggerStreamsComponentImpl.factory().create(
            StreamsDependencies.Impl()
        )
}