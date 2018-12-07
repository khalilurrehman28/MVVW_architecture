package com.example.khalilurrehman.mvvwarc.database;

import androidx.sqlite.db.SupportSQLiteDatabase;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import android.content.Context;
import android.os.AsyncTask;
import androidx.annotation.NonNull;

import com.example.khalilurrehman.mvvwarc.DAO.notes_dao;
import com.example.khalilurrehman.mvvwarc.tables.notes;

@Database(entities = {notes.class},version = 1)
public abstract class notes_database extends RoomDatabase {

    private static notes_database notesDb;

    public abstract notes_dao notesDao();

    public static synchronized notes_database getNotesDb(Context context){
        if (notesDb==null){
            notesDb = Room.databaseBuilder(context.getApplicationContext(),notes_database.class,"notes_database").addCallback(roomCallback).fallbackToDestructiveMigration().build();
        }
        return  notesDb;
    }

    public static RoomDatabase.Callback roomCallback = new RoomDatabase.Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
           // new populateDb(notesDb);
        }
    };

    public static class populateDb extends AsyncTask<Void,Void,Void> {

        notes_dao dao;
        populateDb(notes_database db){
            this.dao = db.notesDao();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            dao.insertData(new notes("hello","text"));
            dao.insertData(new notes("hello1","text1"));
            dao.insertData(new notes("hell2","text3"));

            return null;

        }
    }


}
