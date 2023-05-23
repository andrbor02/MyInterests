package com.example.feature_authorization.impl.di

import com.example.core_network.impl.retrofit.call_adapter.NetworkResponseAdapterFactory
import com.example.feature_authorization.impl.data.datasource.remote.AuthorizationRemoteDataSource
import com.example.feature_authorization.impl.data.datasource.remote.impl.AuthorizationRemoteDatasourceImpl
import com.example.feature_authorization.impl.data.repository_impl.AuthorizationRepositoryImpl
import com.example.feature_authorization.impl.data.utils.RelevantOrganizationChecker
import com.example.feature_authorization.impl.data.utils.RelevantOrganizationCheckerImpl
import com.example.feature_authorization.impl.domain.repository.AuthorizationRepository
import com.example.feature_authorization.impl.domain.usecase.CheckOrganizationUseCase
import com.example.feature_authorization.impl.domain.usecase.FetchApiKeyUseCase
import com.example.feature_authorization.impl.domain.usecase.impl.CheckOrganizationUseCaseImpl
import com.example.feature_authorization.impl.domain.usecase.impl.FetchApiKeyUseCaseImpl
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoSet
import kotlinx.serialization.json.Json
import okhttp3.Interceptor
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.CallAdapter
import retrofit2.Converter

@Module(
    includes = [
        AuthorizationModule.Bindings::class,
        AuthorizationModule.NetworkClient::class,
    ]
)
internal class AuthorizationModule {

    @Module
    interface Bindings {

        @Binds
        fun bindCheckOrganizationUseCase(checkOrganizationUseCaseImpl: CheckOrganizationUseCaseImpl): CheckOrganizationUseCase

        @Binds
        fun bindFetchApiKeyUseCase(fetchApiKeyUseCaseImpl: FetchApiKeyUseCaseImpl): FetchApiKeyUseCase

        @Binds
        fun bindPeopleRepository(peopleRepositoryImpl: AuthorizationRepositoryImpl): AuthorizationRepository

        @Binds
        fun bindUsersRemoteDataSource(authorizationRemoteDatasourceImpl: AuthorizationRemoteDatasourceImpl): AuthorizationRemoteDataSource

        @Binds
        fun bindRelevantOrganizationChecker(relevantOrganizationCheckerImpl: RelevantOrganizationCheckerImpl): RelevantOrganizationChecker
    }

    @Module
    class NetworkClient {

//        @Provides
//        fun provideAuthorizationApi(
//            retrofit: Retrofit,
//        ): AuthorizationApi {
//            return retrofit.create(AuthorizationApi::class.java)
//        }

//        @Provides
//        fun provideNetworkClient(
//            baseUrlHolder: BaseUrlHolder,
//            callAdapterFactory: CallAdapter.Factory,
//            okHttpClient: OkHttpClient,
//            converterFactory: Converter.Factory,
//        ): Retrofit {
//            return Retrofit.Builder()
//                .baseUrl(baseUrlHolder.g)
//                .client(okHttpClient)
//                .addConverterFactory(converterFactory)
//                .build()
//        }

        @Provides
        fun provideConverterFactory(
        ): Converter.Factory {
            val json = Json { ignoreUnknownKeys = true }
            val contentType = "application/json".toMediaType()

            return json.asConverterFactory(contentType)
        }

        @Provides
        fun provideCallAdapterFactory(
            callAdapterFactory: NetworkResponseAdapterFactory,
        ): CallAdapter.Factory {
            return callAdapterFactory
        }


        @Provides
        fun provideOkHttpClient(
            interceptors: Set<@JvmSuppressWildcards Interceptor>,
        ): OkHttpClient {
            return OkHttpClient.Builder()
                .apply {
                    interceptors.forEach { interceptor ->
                        addInterceptor(interceptor)
                    }
                }
                .build()
        }

        @IntoSet
        @Provides
        fun provideLoggingInterceptor(): Interceptor {
            return HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            }
        }
    }
}