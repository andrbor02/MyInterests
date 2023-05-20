package com.example.core_context_provider.api

import android.content.Context
import com.example.core_utils.di_helpers.component_holder.DiComponent

interface ContextProviderComponent : DiComponent {

    fun applicationContext(): Context
}