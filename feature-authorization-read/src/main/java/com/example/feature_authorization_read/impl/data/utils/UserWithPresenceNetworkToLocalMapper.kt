package com.example.feature_people.impl.data.utils

import com.example.feature_people.impl.data.datasource.local.model.StatusLocal
import com.example.feature_people.impl.data.datasource.local.model.UserLocal
import com.example.feature_people.impl.data.datasource.remote.model.users.UserFromPeopleListRemote
import com.example.feature_people.impl.data.datasource.remote.model.users_presence.UserWithPlatform
import javax.inject.Inject

internal interface UserWithPresenceNetworkToLocalMapper {
    operator fun invoke(
        users: List<UserFromPeopleListRemote>,
        usersPresences: UserWithPlatform,
    ): List<UserLocal>
}

internal class UserWithPresenceNetworkToLocalMapperImpl @Inject constructor() :
    UserWithPresenceNetworkToLocalMapper {
    override operator fun invoke(
        users: List<UserFromPeopleListRemote>,
        usersPresences: UserWithPlatform,
    ): List<UserLocal> {
        val usersWithPresence = mutableListOf<UserLocal>()

        users.forEach { user ->
            val userPresence = usersPresences[user.email] ?: return@forEach
            val userPresenceAggregated = userPresence[AGGREGATED] ?: return@forEach

            val currentTimestampSeconds = System.currentTimeMillis() / 1000
            val wasRecently =
                (currentTimestampSeconds - userPresenceAggregated.timestamp) < RECENT_SECONDS

            val status = if (userPresenceAggregated.status == ACTIVE_STATUS && wasRecently) {
                StatusLocal.ACTIVE
            } else if (userPresenceAggregated.status == IDLE_STATUS && wasRecently) {
                StatusLocal.IDLE
            } else {
                StatusLocal.OFFLINE
            }

            usersWithPresence.add(
                UserLocal(
                    id = user.id,
                    avatar = user.avatar ?: "",
                    status = status,
                    name = user.fullName,
                    timestamp = userPresenceAggregated.timestamp,
                    email = user.email,
                )
            )
        }
        return usersWithPresence
    }

    companion object {
        private const val AGGREGATED = "aggregated"

        private const val ACTIVE_STATUS = "active"
        private const val IDLE_STATUS = "idle"

        private const val RECENT_SECONDS = 180
    }
}