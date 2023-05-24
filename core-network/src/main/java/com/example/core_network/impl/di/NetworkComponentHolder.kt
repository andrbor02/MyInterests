package com.example.core_network.impl.di

import com.example.core_network.api.NetworkComponent
import com.example.core_network.api.NetworkDependencies
import com.example.core_utils.di_helpers.component_holder.DataComponentHolder

object NetworkComponentHolder : DataComponentHolder<NetworkComponent>() {
    override fun build(): NetworkComponent =
        DaggerNetworkComponentImpl.factory().create(
            NetworkDependencies.Impl()
        )
}