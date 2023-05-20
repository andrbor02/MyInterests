package com.example.feature_people.impl.di

import com.example.feature_people.api.PeopleComponent
import com.example.feature_people.api.PeopleDependencies
import dagger.Component

@Component(
    dependencies = [PeopleDependencies::class],
    modules = [PeopleModule::class]
)
internal interface PeopleComponentImpl : PeopleComponent {

    @Component.Factory
    interface Factory {
        fun create(dependencies: PeopleDependencies): PeopleComponent
    }
}