package com.example.mysingleapplication;

import android.database.Cursor;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface  NotesDao {

    @Insert
    void insertNotes(Notes notes);


    @Query("SELECT * FROM Notes")
    List<Notes> getNotes();

    @Query("SELECT * FROM Notes")
    Cursor getAccessNotes();

    @Update
    void updateNotes(Notes notes);

    @Delete
    void deleteNotes(Notes notes);




}
