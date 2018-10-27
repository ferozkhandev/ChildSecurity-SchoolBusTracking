package com.childsecuritymobileapp.rabia.childsecurity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Handler;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.google.firebase.auth.FirebaseAuth;
import com.shashank.sony.fancydialoglib.Animation;
import com.shashank.sony.fancydialoglib.FancyAlertDialog;
import com.shashank.sony.fancydialoglib.FancyAlertDialogListener;

public class SplashScreen extends AppCompatActivity {
    private int splashInterval = 2000;
    private FirebaseAuth firebaseAuth;
    private InternetCheck internetCheck;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        firebaseAuth = FirebaseAuth.getInstance();

        internetCheck = new InternetCheck(getApplicationContext());
        process();
        //new InternetCheck(internet -> { /* do something with boolean response */});
    }

    @Override
    protected void onStart() {
        super.onStart();
        final Handler handler = new Handler();
        handler.post(new Runnable() {
            @Override
            public void run() {
                if (internetCheck.netCheck())
                {
                    process();
                }
                else
                {
                    handler.postDelayed(this,500);
                }
            }
        });
    }

    private void process()
    {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (firebaseAuth.getCurrentUser() != null && internetCheck.netCheck())
                {
                    Intent i = new Intent(SplashScreen.this, Home.class);
                    startActivity(i);
                    this.finish();
                }
                else if (internetCheck.netCheck())
                {
                    Intent i = new Intent(SplashScreen.this, LoginActivity.class);
                    startActivity(i);
                    this.finish();
                }
                else
                {
                    showAlertDialog(SplashScreen.this, "Internet Connection",
                            "You don't have internet connection", false);
                }
            }
            private void finish() {
            }}, splashInterval);
    }

    public void showAlertDialog(Context context, String title, String message, Boolean status)
    {
        new FancyAlertDialog.Builder(this)
                .setTitle(title)
                .setBackgroundColor(Color.parseColor("#D22E2E"))  //Don't pass R.color.colorvalue
                .setMessage(message)
                .setNegativeBtnText("Settings")
                .setPositiveBtnBackground(Color.parseColor("#FF4081"))  //Don't pass R.color.colorvalue
                .setPositiveBtnText("Exit")
                .setNegativeBtnBackground(Color.parseColor("#FFA9A7A8"))  //Don't pass R.color.colorvalue
                .setAnimation(Animation.POP)
                .isCancellable(true)
                //.setIcon(R.drawable.ic_star_border_black_24dp, Icon.Visible)
                .OnPositiveClicked(new FancyAlertDialogListener() {
                    @Override
                    public void OnClick() {
                        System.exit(0);
                        //Toast.makeText(getApplicationContext(),"Please check your internet connection", Toast.LENGTH_SHORT).show();
                    }
                })
                .OnNegativeClicked(new FancyAlertDialogListener() {
                    @Override
                    public void OnClick() {
                        startActivity(new Intent(Settings.ACTION_WIFI_SETTINGS));
                        try {
                            Thread.sleep(2000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        finally {
                            if(internetCheck.netCheck())
                            {
                                process();
                            }
                            else
                            {
                                showAlertDialog(SplashScreen.this, "Internet Connection",
                                        "You don't have internet connection", false);
                            }
                        }
                    }
                })
                .build();
    }
}
