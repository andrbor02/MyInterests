package com.example.feature_chat.impl.data.datasource.utils.reactions

import com.example.feature_chat.impl.data.datasource.remote.model.reactions.ReactionsResponse
import javax.inject.Inject

internal interface SuccessfulOperationChecker {
    operator fun invoke(reactionsResponse: ReactionsResponse): Boolean
}

internal class SuccessfulOperationCheckerImpl @Inject constructor() :
    SuccessfulOperationChecker {
    override operator fun invoke(reactionsResponse: ReactionsResponse): Boolean {
        return reactionsResponse.result == SUCCESS
    }

    companion object {
        private const val SUCCESS = "success"
    }
}