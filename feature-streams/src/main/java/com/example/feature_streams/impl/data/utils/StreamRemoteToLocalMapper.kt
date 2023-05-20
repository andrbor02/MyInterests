package com.example.feature_streams.impl.data.utils

import com.example.feature_streams.impl.data.datasource.local.model.StreamLocal
import com.example.feature_streams.impl.data.datasource.remote.model.streams.all.StreamRemote
import javax.inject.Inject

internal interface StreamRemoteToLocalMapper {
    operator fun invoke(streamRemote: StreamRemote, isSubscribed: Boolean): StreamLocal
}

internal class StreamRemoteToLocalMapperImpl @Inject constructor() : StreamRemoteToLocalMapper {
    override fun invoke(streamRemote: StreamRemote, isSubscribed: Boolean): StreamLocal {
        return StreamLocal(
            id = streamRemote.id,
            name = streamRemote.name,
            description = streamRemote.description,
            isSubscribed = isSubscribed,
            topics = "",
        )
    }
}