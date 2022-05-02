package com.example.mysingleapplication;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface UserDao {

    @Insert
    void insertRecord (User user);


    @Query("SELECT * FROM User WHERE email = :email  and password = :password ")
    List<User> getUser(String email,String password);


    @Query("SELECT * FROM User WHERE email = :email  and password = :password ")
    User getValidate(String email,String password);



//    @Query("INSERT INTO User (task_name,task_time) VALUES (:task_name,:task_time) ")
//    List<User> insertNotes(String task_name,int task_time);


}

