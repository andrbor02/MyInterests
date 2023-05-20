package com.example.feature_people_ui.impl.di

import com.example.core_utils.di_helpers.component_holder.FeatureComponentHolder
import com.example.feature_people_ui.api.PeopleUiDependencies

internal object PeopleUiComponentHolder : FeatureComponentHolder<PeopleUIComponent>() {
    override fun build(): PeopleUIComponent {
        return DaggerPeopleUIComponent.factory().create(
            PeopleUiDependencies.Impl()
        )
    }
}