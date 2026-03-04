package edu.ucne.angel_almonte_ap2_p2.di

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import edu.ucne.angel_almonte_ap2_p2.data.remote.ExamenApi
import edu.ucne.angel_almonte_ap2_p2.data.repository.ExamenRepositoryImpl
import edu.ucne.angel_almonte_ap2_p2.domain.repository.ExamenRepository
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideMoshi(): Moshi {
        return Moshi
            .Builder()
            .add(KotlinJsonAdapterFactory())
            .build()
    }

    @Provides
    @Singleton
    fun provideApi(moshi: Moshi): ExamenApi {
        return Retrofit.Builder()
            .baseUrl("https:/Examen/")
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .build()
            .create(ExamenApi::class.java)
    }
//
//    @Provides
//    @Singleton
//    fun provideRepository(api: ExamenApi): ExamenRepository {
//        return ExamenRepositoryImpl(api)
    }
//}