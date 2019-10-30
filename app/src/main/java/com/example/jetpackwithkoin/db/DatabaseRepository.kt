package com.example.jetpackwithkoin.db

import com.example.jetpackwithkoin.db.dao.UsersDao
import com.example.jetpackwithkoin.db.entities.UsersEntity
import io.reactivex.Single

/** Created by addam in 2019-10-30 **/
class DatabaseRepository (val usersDao: UsersDao){

    fun getUserProfile(username: String): Single<UsersEntity> = usersDao.getUsers(username)

    fun saveUser(usersEntity: UsersEntity) = usersDao.saveUser(usersEntity)


}