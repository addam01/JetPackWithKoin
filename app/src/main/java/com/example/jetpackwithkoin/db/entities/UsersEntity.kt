package com.example.jetpackwithkoin.db.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

/** Created by addam in 2019-10-30 **/

@Entity(tableName = "users")
data class UsersEntity (
    @PrimaryKey val username: String,
    var password: String
)