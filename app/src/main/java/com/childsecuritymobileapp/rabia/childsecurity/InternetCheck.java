package com.childsecuritymobileapp.rabia.childsecurity;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

public class InternetCheck
{
    private Context context;
    public InternetCheck(Context context)
    {
        this.context = context;
    }
    public boolean netCheck()
    {
        try
        {
            ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
            return activeNetworkInfo != null && activeNetworkInfo.isConnected();
        }
        catch (NullPointerException ex)
        {
            return false;
        }
    }
}

