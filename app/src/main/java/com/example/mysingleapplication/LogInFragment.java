package com.example.mysingleapplication;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.List;


public class LogInFragment extends Fragment {

    EditText email,password;
    Button login_btn;
    UserDao userDao;
    String uName,uPassword;

    public  static  final  String  EMAIL="sEmail";




    public LogInFragment() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view =inflater.inflate(R.layout.fragment_log_in, container, false);


        email = view.findViewById(R.id.ET_email);
        password = view.findViewById(R.id.ET_password);
        login_btn = view.findViewById(R.id.fragment_login);



        login_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveData();

                if (email.getText().toString().isEmpty() || password.getText().toString().isEmpty())
                {
                    email.requestFocus();
                    password.requestFocus();
                    email.setError("Enter the valid email");
                    password.setError("Enter the  valid password");
                }
                else
                {
                    userDao = AppDatabase.getInstance(getContext()).dao();

                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                           User user = userDao.getValidate(email.getText().toString(),password.getText().toString());

                            if (user != null)
                            {

                                Intent intent = new Intent(getContext(),HomeActivity.class);
                                startActivity(intent);



                            }
                            else
                            {
                                //Toast.makeText(getContext(),"Invalid",Toast.LENGTH_SHORT).show();



                            }


                        }
                    }).start();

                }



            }
        });


        return view;
    }

    private void saveData() {
        String saveEmail = email.getText().toString();
        SharedPreferences preferences = this.getActivity().getSharedPreferences("pref", Context.MODE_PRIVATE);
        SharedPreferences.Editor edt = preferences.edit();
        edt.putString(EMAIL,saveEmail );
        Log.d("RESPONSE","name is :" +saveEmail);

        edt.commit();


    }
}