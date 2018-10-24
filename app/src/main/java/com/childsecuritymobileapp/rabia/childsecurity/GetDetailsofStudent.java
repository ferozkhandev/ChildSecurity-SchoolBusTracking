package com.childsecuritymobileapp.rabia.childsecurity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class GetDetailsofStudent extends AppCompatActivity {
    UserData userData, childData;

    //Views Variables
    private EditText childName, childClassName, childBatch;
    private Button childNext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_detailsof_student);

        //Views Declarations
        childName = findViewById(R.id.childName);
        childClassName = findViewById(R.id.childClassName);
        childBatch = findViewById(R.id.childBatch);
        childNext = findViewById(R.id.childNext);

    }

    @Override
    protected void onStart() {
        super.onStart();
        //Get Intent User
        userData = getIntent().getParcelableExtra("user");
        childData = new Student(userData);

        //Listen Button Click
        childNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event)
    {
        if ((keyCode == KeyEvent.KEYCODE_BACK))
        {
            finish();
        }
        return super.onKeyDown(keyCode, event);
    }

    //Validate Fields
    public boolean validate() {
        boolean valid = true;

        String cname = childName.getText().toString();
        String clName = childClassName.getText().toString();
        String cBatch = childBatch.getText().toString();

        if (cname.isEmpty() || cname.length() < 3) {
            childName.setError("at least 3 characters");
            valid = false;
        } else {
            childName.setError(null);
        }

        if (clName.isEmpty()) {
            childClassName.setError("Kindly fill this field");
            valid = false;
        } else {
            childClassName.setError(null);
        }

        if (cBatch.isEmpty()) {
            childBatch.setError("Kindly fill this field");
            valid = false;
        } else {
            childBatch.setError(null);
        }

        return valid;
    }
}
