package com.example.khalilurrehman.mvvwarc.ViewModels;

import android.app.Application;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.khalilurrehman.mvvwarc.repository.rep;
import com.example.khalilurrehman.mvvwarc.tables.notes;

import java.util.List;

public class viewmodel extends AndroidViewModel {

    private LiveData<List<notes>> listLiveData;
    private rep repo;

    public viewmodel(Application application) {
        super(application);
        this.repo = new rep(application);
        this.listLiveData = repo.getListLiveData();
    }

    public void insert(notes notes){
        repo.insert(notes);
    }

    public void update(notes notes){
        repo.update(notes);
    }

    public void delete(notes notes){
        repo.delete(notes);
    }

    public LiveData<List<notes>> getListLiveData() {
        return listLiveData;
    }


}
