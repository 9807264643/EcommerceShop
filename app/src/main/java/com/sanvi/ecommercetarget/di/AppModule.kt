package com.sanvi.ecommercetarget.di

import com.google.firebase.firestore.FirebaseFirestore
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module // define how to provide certain dependency
@InstallIn(SingletonComponent::class) // dependency are available in app wide
object AppModule {

    // provide firebase firestore instance
    @Provides
    @Singleton
    fun provideFirebaseFireStore() : FirebaseFirestore {
        return FirebaseFirestore.getInstance()

    }


}