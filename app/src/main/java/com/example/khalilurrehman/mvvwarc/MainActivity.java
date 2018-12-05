package com.example.khalilurrehman.mvvwarc;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;
import android.widget.Toast;

import com.example.khalilurrehman.mvvwarc.Adapter.getNotesAdapter;
import com.example.khalilurrehman.mvvwarc.ViewModels.viewmodel;
import com.example.khalilurrehman.mvvwarc.tables.notes;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private viewmodel vm;
    private RecyclerView recyclerView;
    private getNotesAdapter notesAdapter;
   // private List<notes> notesList = new ArrayList<>();
    FloatingActionButton floatingActionButton;

    int i = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        notesAdapter = new getNotesAdapter(this);
        floatingActionButton = findViewById(R.id.fab);

        recyclerView = findViewById(R.id.recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(notesAdapter);

        vm = ViewModelProviders.of(this).get(viewmodel.class);

        vm.getListLiveData().observe(this, new Observer<List<notes>>() {
            @Override
            public void onChanged(@Nullable List<notes> notes) {
                notesAdapter.setList(notes);
                Toast.makeText(MainActivity.this, "Hello", Toast.LENGTH_SHORT).show();
                //notesAdapter.notifyDataSetChanged();
            }
        });

        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                notes nt = new notes("hello "+i,"text "+i);
                vm.insert(nt);
                i++;
            }
        });
    }
}
