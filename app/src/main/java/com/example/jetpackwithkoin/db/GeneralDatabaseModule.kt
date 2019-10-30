package com.example.jetpackwithkoin.db

import android.app.Application
import androidx.room.Room

/** Created by addam in 2019-10-30 **/
class GeneralDatabaseModule {

    fun provideDB(application: Application): GeneralDatabase{
        return Room.databaseBuilder(application, GeneralDatabase::class.java, "sampleDB").build()
    }
    fun provideUserDao(db:GeneralDatabase) = db.usersDao()
}