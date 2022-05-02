package com.example.mysingleapplication;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class UpdateDeleteActivity extends AppCompatActivity {

    EditText task,time;
    Button update,delete;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_delete);

        task = findViewById(R.id.editTextTask);
        time = findViewById(R.id.editTextTime);
        update = findViewById(R.id.button_update);
        delete = findViewById(R.id.button_delete);

        final Notes notes = (Notes) getIntent().getSerializableExtra("notes");

        loadNote(notes);












        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Toast.makeText(getApplicationContext(), "Clicked", Toast.LENGTH_LONG).show();

                updateNotes(notes);







            }
        });

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //deleteNotes(notes);

                AlertDialog.Builder builder = new AlertDialog.Builder(UpdateDeleteActivity.this);
                builder.setTitle("Are you sure?");
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                        deleteNotes(notes);

                    }
                });

                builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {


                    }
                });

                AlertDialog ad = builder.create();
                ad.show();


            }
        });



    }

    private void deleteNotes(Notes notes) {

        class deleteNote extends AsyncTask<Void,Void,Void>
        {
            @Override
            protected Void doInBackground(Void... voids) {

                AppDatabase.getInstance(getApplicationContext()).notesDao().deleteNotes(notes);

                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);



                finish();
                startActivity(new Intent(UpdateDeleteActivity.this,HomeActivity.class));
            }
        }

        deleteNote dt = new deleteNote();
        dt.execute();
    }

    private void updateNotes(Notes notes) {


        final String sTask = task.getText().toString().trim();
        final String sTime = time.getText().toString().trim();

        if (sTask.isEmpty())
        {
            task.requestFocus();
        }
        if (sTime.isEmpty())
        {
            time.requestFocus();
        }

        class UpdateNotes extends AsyncTask<Void,Void,Void>
        {

            @Override
            protected Void doInBackground(Void... voids) {



                notes.setTask_name(sTask);
                notes.setTask_time(sTime);

                AppDatabase.getInstance(getApplicationContext()).notesDao().updateNotes(notes);
                return  null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);

                Toast.makeText(getApplicationContext(),"Updated",Toast.LENGTH_SHORT).show();
                startActivity(new Intent(UpdateDeleteActivity.this,HomeActivity.class));
            }
        }


        UpdateNotes un = new UpdateNotes();
        un.execute();




    }

    private void loadNote(Notes notes) {

        task.setText(notes.getTask_name());
        time.setText(notes.getTask_time());
    }




}