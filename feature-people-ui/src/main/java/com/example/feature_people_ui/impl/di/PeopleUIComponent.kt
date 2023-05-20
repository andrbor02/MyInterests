package com.example.feature_people_ui.impl.di


import com.example.core_utils.di_helpers.component_holder.DiComponent
import com.example.feature_people_ui.api.PeopleUiDependencies
import com.example.feature_people_ui.impl.presentation.ui.PeopleFragment
import dagger.Component

@Component(
    dependencies = [PeopleUiDependencies::class],
    modules = [PeopleUiModule::class]
)
internal interface PeopleUIComponent : DiComponent {

    fun inject(fragment: PeopleFragment)

    @Component.Factory
    interface Factory {
        fun create(dependencies: PeopleUiDependencies): PeopleUIComponent
    }
}