package com.example.feature_profile.impl.data.utils

import com.example.feature_profile.impl.data.datasource.remote.model.user.Profile
import com.example.feature_profile.impl.data.datasource.remote.model.user_presence.PlatformWithPresence
import com.example.feature_profile.impl.domain.model.ProfileModel
import com.example.feature_profile.impl.domain.model.StatusModel
import javax.inject.Inject

internal interface UserWithPresenceNetworkMerger {
    operator fun invoke(
        user: Profile,
        presence: PlatformWithPresence,
    ): ProfileModel
}

internal class UserWithPresenceNetworkMergerImpl @Inject constructor() :
    UserWithPresenceNetworkMerger {
    override operator fun invoke(
        user: Profile,
        presence: PlatformWithPresence,
    ): ProfileModel {
        val status = when (presence[AGGREGATED]?.status) {
            ACTIVE_STATUS -> StatusModel.ACTIVE
            IDLE_STATUS -> StatusModel.IDLE
            OFFLINE_STATUS -> StatusModel.OFFLINE
            else -> StatusModel.OFFLINE
        }

        return ProfileModel(
            id = user.id,
            avatar = user.avatar ?: "",
            status = status,
            name = user.fullName,
        )
    }

    companion object {
        private const val AGGREGATED = "aggregated"

        private const val ACTIVE_STATUS = "active"
        private const val IDLE_STATUS = "idle"
        private const val OFFLINE_STATUS = "offline"
    }
}