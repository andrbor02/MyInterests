package com.example.feature_people.impl.di

import com.example.core_utils.di_helpers.component_holder.FeatureComponentHolder
import com.example.feature_people.api.PeopleComponent
import com.example.feature_people.api.PeopleDependencies

object PeopleComponentHolder : FeatureComponentHolder<PeopleComponent>() {
    override fun build(): PeopleComponent =
        DaggerPeopleComponentImpl.factory().create(
            PeopleDependencies.Impl()
        )
}