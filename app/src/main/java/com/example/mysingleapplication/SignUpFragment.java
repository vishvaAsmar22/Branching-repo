package com.example.mysingleapplication;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import java.util.List;


public class SignUpFragment extends Fragment {

    EditText Name,Email,Password;
    Button signup;
    UserDao userDao;
    User user;
    SharedPreference sp;
    Context context;
    List<User> users;
    public static final String MY_PREFERENCE = "myPreference";
    public static final String EMAIL = "sEmail";
    public static final String PASSWORD = "sPassword";





    public SignUpFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_sign_up, container, false);

        Name = view.findViewById(R.id.ET_name);
        Email = view.findViewById(R.id.ET_email);
        Password = view.findViewById(R.id.ET_password);
        signup = view.findViewById(R.id.fragment_signup);

       // SharedPreferences sp = context.getSharedPreferences(MY_PREFERENCE, Context.MODE_PRIVATE);


        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                userDao = AppDatabase.getInstance(getContext()).dao();
                    new Thread(new Runnable() {
                        @Override
                        public void run() {



                            userDao.insertRecord(new User(Name.getText().toString(), Email.getText().toString(), Password.getText().toString()));

//                                Name.setText("");
//                                Email.setText("");
//                                Password.setText("");
                        }
                    }).start();
            }
        });
        return  view;



    }
}