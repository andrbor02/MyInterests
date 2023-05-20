package com.example.core_network.impl.di


import com.example.core_network.impl.retrofit.NetworkClient
import com.example.core_network.impl.retrofit.call_adapter.NetworkResponseAdapterFactory
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
        @BaseUrl
        baseUrl: String,
        callAdapterFactory: CallAdapter.Factory,
        okHttpClient: OkHttpClient,
        converterFactory: Converter.Factory,
    ): Retrofit {
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .addCallAdapterFactory(callAdapterFactory)
            .client(okHttpClient)
            .addConverterFactory(converterFactory)
            .build()
    }

    @Provides
    @Singleton
    @BaseUrl
    fun provideBaseUrl(
    ): String {
        return "https://andrbor.zulipchat.com/api/v1/"
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
        @Singleton
        fun provideLoggingInterceptor(): Interceptor {
            return HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            }
        }

        @IntoSet
        @Provides
        @Singleton
        fun provideAuthInterceptor(): Interceptor {
            return AuthInterceptor()
        }
    }

    @Module
    interface Bindings {
        @Binds
        @Singleton
        fun provideNetworkClient(retrofitClientImpl: RetrofitClientImpl): NetworkClient
    }
}
