package com.childsecuritymobileapp.rabia.childsecurity;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class GetDetailsofStudent extends AppCompatActivity {
    private UserData userData;
    private Student childData;
    private InternetCheck internetCheck;
    private FirebaseFirestore firestore;

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

        //Get Intent User
        userData = getIntent().getParcelableExtra("user");
        childData = new Student(userData);

        //Firebase Declarations
        firestore = FirebaseFirestore.getInstance();

        //Internet Check
        internetCheck = new InternetCheck(getApplicationContext());
    }

    @Override
    protected void onStart() {
        super.onStart();

        //Listen Button Click
        childNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(collectData() && internetCheck.netCheck())
                {
                    saveData();
                }
                else if (validate())
                {
                    Toast.makeText(getApplicationContext(), "Check Your Internet Connectivity.",Toast.LENGTH_LONG).show();
                }
                else
                {
                    Toast.makeText(getApplicationContext(), "Input Data is not valid.",Toast.LENGTH_LONG).show();
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

    //Send Data To Firebase Firestore
    private void saveData()
    {
        Map<String,Object> map = new HashMap();
        map.put("displayName",childData.getDisplayName());
        map.put("email",childData.getEmail());
        map.put("mobileNumber", childData.getMobileNumber());
        map.put("photoUri", childData.getPhotoUri().toString());
        map.put("childName",childData.getChildName());
        map.put("childClassName", childData.getChildClassName());
        map.put("childBatch", childData.getChildBatch());
        map.put("singup","already");
        firestore.collection("Students").document(FirebaseAuth.getInstance().getCurrentUser().getUid()).set(map).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                Intent intent = new Intent(GetDetailsofStudent.this, Home.class);
                startActivity(intent);
                finish();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Log.i("failed","Error In Saving Details: " + e.getMessage());
            }
        });
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
    private boolean collectData()
    {
        if (validate())
        {
            childData.setChildName(childName.getText().toString());
            childData.setChildClassName(childClassName.getText().toString());
            childData.setChildBatch(childBatch.getText().toString());
            return true;
        }
        else
        {
            return false;
        }
    }
}
