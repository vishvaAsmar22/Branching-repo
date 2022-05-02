package com.example.mysingleapplication;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "User")
public class User {

    @PrimaryKey(autoGenerate = true)
      Integer id;

    @ColumnInfo(name = "name")
    public   String name;

    @ColumnInfo(name = "email")
    public  String email;

    @ColumnInfo(name ="password")
    public  String password;




    public User(String name, String email, String password) {

        this.name = name;
        this.email = email;
        this.password = password;
    }









    public Integer getId() {
        return id;
    }



    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
