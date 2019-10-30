package com.example.jetpackwithkoin.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.jetpackwithkoin.db.dao.UsersDao
import com.example.jetpackwithkoin.db.entities.UsersEntity

/** Created by addam in 2019-10-30 **/

@Database(entities = [UsersEntity::class], version = 1)
abstract class GeneralDatabase: RoomDatabase() {

    abstract fun usersDao(): UsersDao
}