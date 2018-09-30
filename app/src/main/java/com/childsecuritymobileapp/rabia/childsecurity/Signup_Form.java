package com.childsecuritymobileapp.rabia.childsecurity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import java.util.Arrays;

public class Signup_Form extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup__form);

        final Spinner userType = findViewById(R.id.userType);
        String[] users = getResources().getStringArray(R.array.usetype);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, users);
        userType.setAdapter(adapter);
    }
}
