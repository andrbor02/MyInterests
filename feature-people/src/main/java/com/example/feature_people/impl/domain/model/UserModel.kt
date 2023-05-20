package com.example.feature_people.impl.domain.model

data class UserModel(
    val id: Int,
    val avatar: String,
    val status: StatusModel,
    val name: String,
    val email: String,
)
