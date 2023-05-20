package com.example.feature_profile.impl.domain.model

data class ProfileModel(
    val id: Int,
    val avatar: String,
    val status: StatusModel,
    val name: String,
)
