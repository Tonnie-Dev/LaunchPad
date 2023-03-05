package com.uxstate.launchpad.di

import android.content.Context
import androidx.room.Room
import com.uxstate.launchpad.data.local.database.LaunchDatabase
import com.uxstate.launchpad.data.local.database.transaction.LaunchDatabaseTransactionProvider
import com.uxstate.launchpad.data.local.database.utils.DatabaseTransactionProvider
import com.uxstate.launchpad.domain.useCases.*
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface DatabaseModule {

    @Binds
    @Singleton
    fun bindLaunchDbTransactionProvider(provider: LaunchDatabaseTransactionProvider):
        DatabaseTransactionProvider

    companion object {
        @Provides
        @Singleton
        fun provideDatabase(@ApplicationContext context: Context): LaunchDatabase =
            Room.databaseBuilder(
                context,
                LaunchDatabase::class.java,
                LaunchDatabase.DATABASE_NAME
            ).build()
    }
}
