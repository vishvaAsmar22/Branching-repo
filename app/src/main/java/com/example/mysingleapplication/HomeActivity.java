package com.example.mysingleapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class HomeActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    NotesDao dao;

    FloatingActionButton fb;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        recyclerView = findViewById(R.id.recyclerview);
        fb = findViewById(R.id.fb);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));



        fb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomeActivity.this,AddTaskActivity.class);
                startActivity(intent);
            }
        });


        getNotes();
    }

    private void getNotes() {



        class getNote extends AsyncTask<Void,Void, List<Notes>>
        {

            @Override
            protected List<Notes> doInBackground(Void... voids) {
               dao = AppDatabase.getInstance(getApplicationContext()).notesDao();

               List<Notes> notesList =dao.getNotes();

                NotesAdapter adapter = new NotesAdapter(HomeActivity.this,notesList);
                recyclerView.setAdapter(adapter);


                return null;
            }





        }
        getNote gt = new getNote();
        gt.execute();

    }
}