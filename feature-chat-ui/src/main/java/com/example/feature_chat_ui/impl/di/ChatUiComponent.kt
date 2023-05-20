package com.example.feature_chat_ui.impl.di

import com.example.core_utils.di_helpers.component_holder.DiComponent
import com.example.feature_chat_ui.api.ChatUiDependencies
import com.example.feature_chat_ui.impl.presentation.ui.ChatFragment
import dagger.Component

@Component(
    dependencies = [ChatUiDependencies::class],
    modules = [ChatUiModule::class]
)
interface ChatUiComponent : DiComponent {

    fun inject(fragment: ChatFragment)

    @Component.Factory
    interface Factory {
        fun create(dependencies: ChatUiDependencies): ChatUiComponent
    }
}