package com.example.khalilurrehman.mvvwarc;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.View;
import android.widget.Toast;

import com.example.khalilurrehman.mvvwarc.Adapter.getNotesAdapter;
import com.example.khalilurrehman.mvvwarc.BaseActivity.BaseActivity;
import com.example.khalilurrehman.mvvwarc.Helper.RecyclerTouchListener;
import com.example.khalilurrehman.mvvwarc.ViewModels.viewmodel;
import com.example.khalilurrehman.mvvwarc.tables.notes;

import java.util.List;

public class Home extends BaseActivity {

    public static final int RequestCode = 101;
    private static final int UpdateCode = 102;
    private viewmodel vm;
    private RecyclerView recyclerView;
    private getNotesAdapter notesAdapter;
    FloatingActionButton floatingActionButton;

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
            }
        });

        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               startActivityForResult(new Intent(Home.this,Addupdate.class),RequestCode);
            }
        });

        //to enable swiping behaviour
        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0,ItemTouchHelper.LEFT|ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder viewHolder1) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
                vm.delete(notesAdapter.getItemAtPosition(viewHolder.getAdapterPosition()));
                Toast.makeText(Home.this, "Note deleted", Toast.LENGTH_SHORT).show();
            }
        }).attachToRecyclerView(recyclerView);

        recyclerView.addOnItemTouchListener(new RecyclerTouchListener(this, recyclerView, new RecyclerTouchListener.ClickListener() {
            @Override
            public void onClick(View view, int position) {
                notes nt = notesAdapter.getItemAtPosition(position);
                Intent i = new Intent(Home.this,Addupdate.class);
                i.putExtra("title",nt.getTitle());
                i.putExtra("text",nt.getText());
                i.putExtra("id",nt.getId());
                startActivityForResult(i,UpdateCode);
            }

            @Override
            public void onLongClick(View view, final int position) {
            }
        }));

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode){
            case RequestCode:
                if (resultCode !=RESULT_OK){
                    return;
                }
                if (data !=null){
                    notes nt = new notes(data.getStringExtra("note_title"),data.getStringExtra("note_text"));
                    vm.insert(nt);
                }

                break;
            case UpdateCode:
                if (resultCode !=RESULT_OK){
                    return;
                }
                if (data !=null){
                    notes nt = new notes(data.getStringExtra("note_title"),data.getStringExtra("note_text"));
                    nt.setId(data.getIntExtra("id",0));
                    vm.update(nt);
                }

                break;

        }

    }
}
