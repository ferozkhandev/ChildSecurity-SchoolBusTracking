package com.childsecuritymobileapp.rabia.childsecurity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.gms.common.util.NumberUtils;
import com.google.zxing.common.StringUtils;

public class GetDetailsOfDriver extends AppCompatActivity {
    UserData userData, driverData;

    //Views Variables
    private EditText driverName, driverPhone, driverCNIC, driverVehicle;
    private Button driverNext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_details_of_driver);

        //Views Declarations
        driverName = findViewById(R.id.driverName);
        driverPhone = findViewById(R.id.driverPhone);
        driverCNIC = findViewById(R.id.driverCNIC);
        driverVehicle = findViewById(R.id.driverVehicle);
        driverNext = findViewById(R.id.driverNext);

    }

    @Override
    protected void onStart() {
        super.onStart();
        //Get Intent User
        userData =  getIntent().getParcelableExtra("user");
        driverData = new Driver(userData);

        //Lister Button Click
        driverNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (validate())
                {
                    Intent intent = new Intent(GetDetailsOfDriver.this, Home.class);
                    startActivity(intent);
                    finish();
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

    //Validate Fields
    public boolean validate() {
        boolean valid = true;

        String dname = driverName.getText().toString();
        String dphone = driverPhone.getText().toString();
        String dCNIC = driverCNIC.getText().toString();
        String dVehicle = driverVehicle.getText().toString();

        if (dname.isEmpty() || dname.length() < 3) {
            driverName.setError("at least 3 characters");
            valid = false;
        } else {
            driverName.setError(null);
        }

        if (dphone.isEmpty() || !Patterns.PHONE.matcher(dphone).matches()) {
            driverPhone.setError("Enter a valid Phone Number");
            valid = false;
        } else {
            driverPhone.setError(null);
        }

        if (dCNIC.isEmpty() || dCNIC.length() != 13) {
            driverCNIC.setError("Kindly Enter a valid CNIC.");
            valid = false;
        } else {
            driverCNIC.setError(null);
        }
        if (!dVehicle.isEmpty())
        {
            driverVehicle.setError("Vechile number must not be empty");
            valid = false;
        }
        else
        {
            driverVehicle.setError(null);
        }

        return valid;
    }
}
