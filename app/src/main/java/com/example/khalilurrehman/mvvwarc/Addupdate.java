package com.example.khalilurrehman.mvvwarc;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;

import com.example.khalilurrehman.mvvwarc.BaseActivity.BaseActivity;

public class Addupdate extends BaseActivity {

    EditText title,text;
    int id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.addupdate);

        //getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_launcher_foreground);
        title = findViewById(R.id.add_title);
        text = findViewById(R.id.add_text);

        Intent i =  getIntent();
        if (!i.hasExtra("text"))
            setTitle("Add Note");
        else {
            setTitle("Edit Note");
            title.setText(getIntent().getStringExtra("title"));
            text.setText(getIntent().getStringExtra("text"));
            id = getIntent().getIntExtra("id",0);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.mainmenu, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.save:
                Intent i = new Intent();
                i.putExtra("note_text",text.getText().toString());
                i.putExtra("note_title",title.getText().toString());
                if (id!=0){
                    i.putExtra("id",id);
                }
                setResult(Activity.RESULT_OK,i);
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
