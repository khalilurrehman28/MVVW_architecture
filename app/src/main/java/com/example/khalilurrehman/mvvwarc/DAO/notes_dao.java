package com.example.khalilurrehman.mvvwarc.DAO;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

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
