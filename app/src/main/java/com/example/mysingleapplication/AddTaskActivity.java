package com.example.mysingleapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlarmManager;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.timepicker.MaterialTimePicker;
import com.google.android.material.timepicker.TimeFormat;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class AddTaskActivity extends AppCompatActivity {

        EditText taskName,taskTime;
        Button btn_add;
        List<Notes> arrayList;
        MaterialTimePicker  timePicker;
        private Calendar  calendar =  Calendar.getInstance();;
        AlarmManager alarmManager;
        TextView name;
       public  static  final  String  EMAIL="sEmail";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_task);


        taskName = findViewById(R.id.add_task);
        taskTime = findViewById(R.id.add_taskTime);
        btn_add = findViewById(R.id.add_btn);
        name=findViewById(R.id.name_textView);

        SharedPreferences pref = this.getSharedPreferences("pref",Context.MODE_PRIVATE);
        String sname = pref.getString(EMAIL, "");
        Log.d("RESPONSE_SP","name is :"+sname);
        name.setText(sname);

       // createNotification();

//        taskTime.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                showTimePicker();
//                setAlaram();
//
//
//
//
//
//            }
//        });

        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveNotes();

            }


        });

    }
    private void setAlaram() {
        alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);

        Intent intent = new Intent(this,NotificationReceiver.class);

        PendingIntent pendingIntent = PendingIntent.getBroadcast(this,0,intent,0);

        alarmManager.setInexactRepeating(AlarmManager.RTC_WAKEUP,calendar.getTimeInMillis(),AlarmManager.INTERVAL_DAY,pendingIntent);

        Toast.makeText(this,"Alaram set successfully",Toast.LENGTH_SHORT).show();
    }


    private void showTimePicker() {

        timePicker = new MaterialTimePicker.Builder()
                .setTimeFormat(TimeFormat.CLOCK_12H)
                .setHour(12)
                .setMinute(0)
                .setTitleText("Select Time ")
                .build();

        timePicker.show(getSupportFragmentManager(),"singleApp");

        timePicker.addOnPositiveButtonClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (timePicker.getHour() > 12)
                {
                    taskTime.setText(
                            String.format("%02d",(timePicker.getHour()-12))+" : " +String.format("0%2d",timePicker.getMinute())+" PM");
                }
                else {
                    taskTime.setText(timePicker.getHour()+" : " +timePicker.getMinute() + "AM");
                }



                calendar.set(Calendar.HOUR_OF_DAY,timePicker.getHour());
                calendar.set(Calendar.MINUTE,timePicker.getMinute());
                calendar.set(Calendar.SECOND,0);
                calendar.set(Calendar.MILLISECOND,0);
            }
        });
    }



    private void createNotification() {


        if (Build.VERSION.SDK_INT >=Build.VERSION_CODES.O)
        {
            CharSequence name = "My Channel";
            String description = "Channel for notesapp";
            int imp = NotificationManager.IMPORTANCE_HIGH;
            NotificationChannel channel = new NotificationChannel("singleApp",name,imp);
            channel.setDescription(description);


            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
    }

    private void saveNotes() {

        final  String sTask = taskName.getText().toString();
        final  String sTime = taskTime.getText().toString();

        if (sTask.isEmpty())
        {
            taskName.setError("Please Enter task");
            taskName.requestFocus();
        }
        LoadData();




        class SaveNotes extends AsyncTask<Void,Void,Void>
        {
            @Override
            protected Void doInBackground(Void... voids) {
                //to add data into database

                NotesDao Dao = AppDatabase.getInstance(getApplicationContext()).notesDao();
                Dao.insertNotes(new Notes(sTask,sTime));




                return  null;

            }

            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);
                finish();
                startActivity(new Intent(getApplicationContext(),HomeActivity.class));
                Toast.makeText(getApplicationContext(), "Saved", Toast.LENGTH_LONG).show();

            }
        }


        SaveNotes st = new SaveNotes();
        st.execute();
    }

    private void LoadData() {

    }


}