package com.example.khalilurrehman.mvvwarc.repository;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.RoomDatabase;
import android.os.AsyncTask;
import android.support.annotation.NonNull;

import com.example.khalilurrehman.mvvwarc.DAO.notes_dao;
import com.example.khalilurrehman.mvvwarc.database.notes_database;
import com.example.khalilurrehman.mvvwarc.tables.notes;

import java.util.List;

public class rep {

    notes_dao dao;
    LiveData<List<notes>> listLiveData;

    public LiveData<List<notes>> getListLiveData() {
        return listLiveData;
    }

    public rep(Application application){
        notes_database database = notes_database.getNotesDb(application);
        this.dao = database.notesDao();
        this.listLiveData = dao.notesList();
    }

    public void insert(notes note){
        new insertDataInDB(dao).execute(note);
    }

    public void update(notes note){
        new updateDataInDB(dao).execute(note);
    }

    public void delete(notes note){
        new deleteDataInDB(dao).execute(note);
    }


    public static class insertDataInDB extends AsyncTask<notes,Void,Void>{

        notes_dao dao;
        insertDataInDB(notes_dao notes_dao){
            this.dao = notes_dao;
        }

        @Override
        protected Void doInBackground(notes... notes) {
            dao.insertData(notes[0]);

            return null;

        }
    }
    public static class updateDataInDB extends AsyncTask<notes,Void,Void>{

        notes_dao dao;
        updateDataInDB(notes_dao notes_dao){
            this.dao = notes_dao;
        }

        @Override
        protected Void doInBackground(notes... notes) {
            dao.updateData(notes[0]);

            return null;

        }
    }
    public static class deleteDataInDB extends AsyncTask<notes,Void,Void>{

        notes_dao dao;
        deleteDataInDB(notes_dao notes_dao){
            this.dao = notes_dao;
        }

        @Override
        protected Void doInBackground(notes... notes) {
            dao.deleteData(notes[0]);

            return null;

        }
    }



}
