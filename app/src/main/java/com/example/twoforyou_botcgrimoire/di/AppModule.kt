package com.example.twoforyou_botcgrimoire.di

import com.example.twoforyou_botcgrimoire.data.repository.GrimoireRepositoryImpl
import com.example.twoforyou_botcgrimoire.data.repository.SetupRepositoryImpl
import com.example.twoforyou_botcgrimoire.domain.database.remote.FirebaseCharacterDatabase
import com.example.twoforyou_botcgrimoire.domain.repository.GrimoireRepository
import com.example.twoforyou_botcgrimoire.domain.repository.SetupRepository
import com.google.firebase.Firebase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Provides
    @Singleton
    fun providesSetupRepository(): SetupRepository {
        return SetupRepositoryImpl(providesFirebaseCharacterDatabase())
    }

    @Provides
    @Singleton
    fun providesFirebaseCharacterDatabase(): FirebaseCharacterDatabase {
        return FirebaseCharacterDatabase()
    }

    @Provides
    @Singleton
    fun providesGrimoireRepository(): GrimoireRepository {
        return GrimoireRepositoryImpl(providesFirebaseCharacterDatabase())
    }

}