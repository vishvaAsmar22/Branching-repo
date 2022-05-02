package com.example.mysingleapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button login,signup;
    Fragment fragment;
    FragmentManager fragmentManager;
    FragmentTransaction fragmentTransaction;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        login = findViewById(R.id.btn_login);
        signup = findViewById(R.id.btn_signup);


        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fragment = new LogInFragment();
                fragmentManager = getSupportFragmentManager();
                fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.add(R.id.frameLayout, fragment);
                fragmentTransaction.commit();

            }
        });

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fragment = new SignUpFragment();
                fragmentManager = getSupportFragmentManager();
                fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.replace(R.id.frameLayout,fragment);
                fragmentTransaction.commit();



            }
        });
    }
}