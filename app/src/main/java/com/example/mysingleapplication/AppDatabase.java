package com.example.mysingleapplication;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {User.class,Notes.class},version = 1)
public  abstract  class AppDatabase extends RoomDatabase {

    private  static  AppDatabase Instance;
    public abstract UserDao dao();
    public  abstract  NotesDao notesDao();
    public  static  synchronized AppDatabase getInstance(Context context)
    {
        if (Instance == null)
        {
            Instance = Room.databaseBuilder(context,AppDatabase.class,"room_db_notes").build();
        }
        return  Instance;
    }


}
