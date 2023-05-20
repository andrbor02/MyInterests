package com.example.feature_people.impl.data.utils

import com.example.feature_people.impl.data.datasource.local.model.StatusLocal
import com.example.feature_people.impl.data.datasource.local.model.UserLocal
import javax.inject.Inject

internal interface UserLocalToDomainMapper {
    operator fun invoke(userLocal: UserLocal): UserModel
}

internal class UserLocalToDomainMapperImpl @Inject constructor() : UserLocalToDomainMapper {
    override fun invoke(userLocal: UserLocal): UserModel {
        val status = when (userLocal.status) {
            StatusLocal.ACTIVE -> StatusModel.ACTIVE
            StatusLocal.IDLE -> StatusModel.IDLE
            StatusLocal.OFFLINE -> StatusModel.OFFLINE
        }

        return UserModel(
            id = userLocal.id,
            avatar = userLocal.avatar,
            status = status,
            name = userLocal.name,
            email = userLocal.email,
        )
    }
}