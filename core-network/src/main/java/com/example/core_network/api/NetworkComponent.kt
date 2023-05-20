package com.example.core_network.api

import com.example.core_network.impl.retrofit.NetworkClient
import com.example.core_utils.di_helpers.component_holder.DiComponent

interface NetworkComponent : DiComponent {
    fun networkClient(): NetworkClient
}