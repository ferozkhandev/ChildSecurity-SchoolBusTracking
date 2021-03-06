package com.childsecuritymobileapp.rabia.childsecurity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.facebook.login.LoginManager;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;

import org.angmarch.views.NiceSpinner;

import java.io.Serializable;
import java.util.Arrays;

public class Signup_Form extends AppCompatActivity {

    private Button btn_next;

    //View Variables
    private EditText email, mobileNumber, password, confirmPassword, username;
    private NiceSpinner spinner;
    private FirebaseAuth firebaseAuth;
    private FirebaseFirestore firestore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup__form);

        //Views Initialization
        username = findViewById(R.id.username);
        email = findViewById(R.id.email);
        mobileNumber = findViewById(R.id.mobileNumber);
        password = findViewById(R.id.password);
        confirmPassword = findViewById(R.id.cPassword);

        spinner = findViewById(R.id.userType);
        String[] users = getResources().getStringArray(R.array.usetype);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,android.R.layout.simple_spinner_item, users);
        spinner.setAdapter(adapter);

        btn_next = findViewById(R.id.btn_next);

        //Firebase Declarations
        firebaseAuth = FirebaseAuth.getInstance();
        firestore = FirebaseFirestore.getInstance();
    }

    @Override
    protected void onStart() {
        super.onStart();
        final UserData userData = (UserData) getIntent().getExtras().getParcelable("userData");
        username.setText(userData.getDisplayName());
        email.setText(userData.getEmail());
        mobileNumber.setText(userData.getMobileNumber());
        btn_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (validate())
                {
                    updatePassword();
                    String user = spinner.getText().toString();
                    if (user.equals("Parent"))
                    {
                        Intent intent = new Intent(Signup_Form.this, GetDetailsofStudent.class);
                        userData.setPassword(password.getText().toString());
                        userData.setMobileNumber(mobileNumber.getText().toString());
                        intent.putExtra("user",userData);
                        startActivity(intent);
                        finish();
                    }
                    else if (user.equals("School"))
                    {
                        Intent intent = new Intent(Signup_Form.this, GetDetailsOfDriver.class);
                        userData.setPassword(password.getText().toString());
                        userData.setMobileNumber(mobileNumber.getText().toString());
                        intent.putExtra("user",userData);
                        startActivity(intent);
                        finish();
                    }
                }
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

    private void updatePassword()
    {
        firebaseAuth.getCurrentUser().updatePassword(password.getText().toString());
    }

    //Validate Fields
    private boolean validate() {
        boolean valid = true;

        String names = username.getText().toString();
        String emails = email.getText().toString();
        String passwords = password.getText().toString();
        String confirmPasswords = confirmPassword.getText().toString();

        if (names.isEmpty() || names.length() < 3) {
            username.setError("at least 3 characters");
            valid = false;
        } else {
            username.setError(null);
        }

        if (emails.isEmpty() || !android.util.Patterns.EMAIL_ADDRESS.matcher(emails).matches()) {
            email.setError("enter a valid email address");
            valid = false;
        } else {
            email.setError(null);
        }

        if (passwords.isEmpty() || passwords.length() < 6) {
            password.setError("Password cannot be less than 6 characters.");
            valid = false;
        } else {
            password.setError(null);
        }
        if (!passwords.equals(confirmPasswords))
        {
            password.setError("Password not matched.");
            valid = false;
        }
        else
        {
            confirmPassword.setError(null);
        }

        return valid;
    }
}
