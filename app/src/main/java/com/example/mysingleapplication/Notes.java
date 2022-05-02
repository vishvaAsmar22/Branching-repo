package com.example.mysingleapplication;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity(tableName = "Notes")
public class Notes implements Serializable {

    @PrimaryKey(autoGenerate =true)
    Integer id;

    @ColumnInfo(name = "task_name")
    public  String task_name;

    @ColumnInfo(name ="task_time")
    public  String task_time;


    public Notes(String task_name, String task_time) {
        this.task_name = task_name;
        this.task_time = task_time;
    }




    public Integer getId() {
        return id;
    }



    public String getTask_name() {
        return task_name;
    }

    public void setTask_name(String task_name) {
        this.task_name = task_name;
    }

    public String getTask_time() {
        return task_time;
    }

    public void setTask_time(String task_time) {
        this.task_time = task_time;
    }
}
