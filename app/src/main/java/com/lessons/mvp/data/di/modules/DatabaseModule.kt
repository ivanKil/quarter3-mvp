package com.lessons.mvp.data.di.modules

import android.content.Context
import androidx.room.Room
import com.lessons.mvp.data.db.Database
import dagger.Module
import dagger.Provides

@Module
class DatabaseModule {

    @Provides
    fun provideDatabase(context: Context): Database =
        Room.databaseBuilder(context, Database::class.java, "database.db")
            .build()


}