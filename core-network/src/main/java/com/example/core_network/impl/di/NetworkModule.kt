package com.example.core_network.impl.di


import com.example.core_network.impl.authorization.AuthorizationChecker
import com.example.core_network.impl.authorization.AuthorizationCheckerImpl
import com.example.core_network.impl.retrofit.BaseUrlHolder
import com.example.core_network.impl.retrofit.CredentialsHolder
import com.example.core_network.impl.retrofit.NetworkClient
import com.example.core_network.impl.retrofit.call_adapter.NetworkResponseAdapterFactory
import com.example.core_network.impl.retrofit.impl.BaseUrlHolderImpl
import com.example.core_network.impl.retrofit.impl.CredentialsHolderImpl
import com.example.core_network.impl.retrofit.impl.RetrofitClientImpl
import com.example.core_network.impl.retrofit.interceptors.AuthInterceptor
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
import retrofit2.Retrofit
import javax.inject.Singleton

@Module(
    includes = [
        NetworkModule.Bindings::class,
        NetworkModule.OkHttpClientModule::class]
)
internal class NetworkModule {

    @Provides
    @Singleton
    fun provideNetworkClient(
        baseUrlHolder: BaseUrlHolder,
        callAdapterFactory: CallAdapter.Factory,
        okHttpClient: OkHttpClient,
        converterFactory: Converter.Factory,
    ): Retrofit {
        return Retrofit.Builder()
            .baseUrl(baseUrlHolder.getUrl())
            .addCallAdapterFactory(callAdapterFactory)
            .client(okHttpClient)
            .addConverterFactory(converterFactory)
            .build()
    }

    @Provides
    @Singleton
    fun provideCallAdapterFactory(
        callAdapterFactory: NetworkResponseAdapterFactory,
    ): CallAdapter.Factory {
        return callAdapterFactory
    }


    @Provides
    @Singleton
    fun provideConverterFactory(
    ): Converter.Factory {
        val json = Json { ignoreUnknownKeys = true }
        val contentType = "application/json".toMediaType()

        return json.asConverterFactory(contentType)
    }

    @Module
    class OkHttpClientModule {
        @Provides
        @Singleton
        fun provideOkHttpClient(
            interceptors: Set<@JvmSuppressWildcards Interceptor>,
            credentialsHolder: CredentialsHolder,
        ): OkHttpClient {
            val isAuthorized = credentialsHolder.getApiKey().isNotBlank()

            return OkHttpClient.Builder()
                .apply {
                    interceptors.forEach { interceptor ->
                        if (interceptor is AuthInterceptor && isAuthorized) {
                            println("dobavil")
                            addInterceptor(interceptor)
                        }
                        addInterceptor(interceptor)
                    }
                }
                .build()
        }

        @IntoSet
        @Provides
        @Singleton
        fun provideLoggingInterceptor(): Interceptor {
            return HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            }
        }

        @IntoSet
        @Provides
        @Singleton
        fun provideAuthInterceptor(authInterceptor: AuthInterceptor): Interceptor {
            return authInterceptor
        }
    }

    @Module
    interface Bindings {
        @Binds
        @Singleton
        fun bindNetworkClient(retrofitClientImpl: RetrofitClientImpl): NetworkClient

        @Binds
        @Singleton
        fun bindBaseUrlHolder(baseUrlHolderImpl: BaseUrlHolderImpl): BaseUrlHolder

        @Binds
        @Singleton
        fun bindCredentialsHolder(credentialsHolderImpl: CredentialsHolderImpl): CredentialsHolder

        @Binds
        @Singleton
        fun bindAuthorizationChecker(authorizationCheckerImpl: AuthorizationCheckerImpl): AuthorizationChecker
    }
}
