package com.example.jetpackwithkoin.db.dao

import androidx.room.*
import com.example.jetpackwithkoin.db.entities.UsersEntity
import io.reactivex.Single

/** Created by addam in 2019-10-30 **/

@Dao
interface UsersDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveUser(user: UsersEntity)

    @Query("SELECT * FROM users")
    fun getUsers() : Single<List<UsersEntity>>

    @Query("SELECT * FROM users WHERE username = :username LIMIT 1 ")
    fun getUsers(username: String) : Single<UsersEntity>

    @Delete
    fun deleteUser(user: UsersEntity)
}