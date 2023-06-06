package com.alierdemalkoc.contacts.di

import android.content.Context
import androidx.room.Room
import com.alierdemalkoc.contacts.config.AppDatabase
import com.alierdemalkoc.contacts.dao.ContactDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideContactDatabase(@ApplicationContext context: Context): AppDatabase =
        Room.databaseBuilder(context, AppDatabase::class.java, "contact_database")
            .fallbackToDestructiveMigration().build()

    @Provides
    @Singleton
    fun provideContactDao(contactDatabase: AppDatabase): ContactDao = contactDatabase.contactDao()
}