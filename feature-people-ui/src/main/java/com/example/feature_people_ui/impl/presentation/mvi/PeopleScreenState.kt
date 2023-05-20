package com.example.feature_people_ui.impl.presentation.mvi

import com.example.feature_people.impl.domain.model.UserModel

sealed class PeopleScreenState {
    object Init : PeopleScreenState()
    object Loading : PeopleScreenState()
    data class ListError(val errorMessage: String) : PeopleScreenState()
    data class SearchError(val errorMessage: String) : PeopleScreenState()
    data class Success(val data: List<UserModel>) : PeopleScreenState()
}