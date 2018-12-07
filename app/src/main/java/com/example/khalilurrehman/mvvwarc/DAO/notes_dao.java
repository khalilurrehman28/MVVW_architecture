package com.example.khalilurrehman.mvvwarc.DAO;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.khalilurrehman.mvvwarc.tables.notes;

import java.util.List;

@Dao
public interface notes_dao {

    @Insert
    void insertData(notes note);

    @Update
    void updateData(notes note);

    @Delete
    void deleteData(notes note);

    @Query("SELECT * FROM notes_table")
    LiveData<List<notes>> notesList();

}
