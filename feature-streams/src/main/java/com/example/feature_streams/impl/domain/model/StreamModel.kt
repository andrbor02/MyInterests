package com.example.feature_streams.impl.domain.model

data class StreamModel(
    val id: Int,
    val name: String,
    val topics: List<TopicModel>,
)
