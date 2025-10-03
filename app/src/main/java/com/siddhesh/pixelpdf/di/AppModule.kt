package com.siddhesh.pixelpdf.di

import android.content.Context
import androidx.room.Room
import com.siddhesh.pixelpdf.db.AppDatabase
import com.siddhesh.pixelpdf.db.BookDao
import com.siddhesh.pixelpdf.db.AnnotationDao
import com.siddhesh.pixelpdf.db.ReadingProgressDao
import com.siddhesh.pixelpdf.db.data.BookRepository
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
    fun provideAppDatabase(@ApplicationContext context: Context): AppDatabase {
        return Room.databaseBuilder(
            context.applicationContext,
            AppDatabase::class.java,
            "pixelpdf.db"
        ).build()
    }

    @Provides
    fun provideBookDao(appDatabase: AppDatabase): BookDao {
        return appDatabase.bookDao()
    }

    @Provides
    fun provideAnnotationDao(appDatabase: AppDatabase): AnnotationDao {
        return appDatabase.annotationDao()
    }

    @Provides
    fun provideReadingProgressDao(appDatabase: AppDatabase): ReadingProgressDao {
        return appDatabase.readingProgressDao()
    }

    @Provides
    fun provideBookRepository(bookDao: BookDao): BookRepository {
        return BookRepository(bookDao)
    }
}
