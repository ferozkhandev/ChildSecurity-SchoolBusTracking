package com.childsecuritymobileapp.rabia.childsecurity;

import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable;

public class Driver extends UserData {
    private String driverName, driverPhone, driverCNIC, vehicleNumber;
    private final String defaultDriverName = "";
    private final String defaultDriverPhone = "";
    private final String defaultDriverCNIC = "";
    private final String defaultVehicleNumber = "";
    Driver()
    {
        setDriverName(defaultDriverName);
        setDriverPhone(defaultDriverPhone);
        setDriverCNIC(defaultDriverCNIC);
    }
    Driver(UserData userData)
    {
        setDisplayName(userData.getDisplayName());
        setEmail(userData.getEmail());
        setMobileNumber(userData.getMobileNumber());
        setPhotoUri(userData.getPhotoUri());
        setPassword(userData.getPassword());
        setDriverName(defaultDriverName);
        setDriverPhone(defaultDriverPhone);
        setDriverCNIC(defaultDriverCNIC);
    }
    Driver(Parcel in)
    {
        setDisplayName(in.readString());
        setEmail(in.readString());
        setMobileNumber(in.readString());
        setPhotoUri((Uri)in.readParcelable(Uri.class.getClassLoader()));
        setPassword(in.readString());
        setDriverName(in.readString());
        setDriverPhone(in.readString());
        setDriverCNIC(in.readString());
        setVehicleNumber(in.readString());
    }
    Driver(String displayName, String email, String mobileNumber, Uri photoUri , String password, String driverName, String driverPhone, String driverCNIC, String vehicleNumber)
    {
        super(displayName, email, mobileNumber, photoUri, password);
        if (driverName != null)
        {
            setDriverName(driverName);
        }
        else
        {
            setDriverName(defaultDriverName);
        }
        if (driverPhone != null)
        {
            setDriverPhone(driverPhone);
        }
        else
        {
            setDriverPhone(defaultDriverPhone);
        }
        if (driverCNIC != null)
        {
            setDriverCNIC(defaultDriverCNIC);
        }
        else
        {
            setDriverCNIC(defaultDriverCNIC);
        }
        if (vehicleNumber != null)
        {
            setVehicleNumber(vehicleNumber);
        }
        else
        {
            setVehicleNumber(defaultVehicleNumber);
        }
    }

    //Setters
    public void setDriverName(String driverName) {
        this.driverName = driverName;
    }

    public void setDriverPhone(String driverPhone) {
        this.driverPhone = driverPhone;
    }

    public void setDriverCNIC(String driverCNIC) {
        this.driverCNIC = driverCNIC;
    }

    public void setVehicleNumber(String vehicleNumber) {
        this.vehicleNumber = vehicleNumber;
    }

    //Getters
    public String getDriverName() {
        return driverName;
    }

    public String getDriverPhone() {
        return driverPhone;
    }

    public String getDriverCNIC() {
        return driverCNIC;
    }

    public String getVehicleNumber() {
        return vehicleNumber;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(getDisplayName());
        parcel.writeString(getEmail());
        parcel.writeString(getMobileNumber());
        parcel.writeParcelable(getPhotoUri(),i);
        parcel.writeString(getPassword());
        parcel.writeString(getDriverName());
        parcel.writeString(getDriverPhone());
        parcel.writeString(getDriverCNIC());
        parcel.writeString(getVehicleNumber());
    }
    // This is to de-serialize the object
    public static final Parcelable.Creator<Driver> CREATOR = new Parcelable.Creator<Driver>(){
        public Driver createFromParcel(Parcel in) {
            return new Driver(in);
        }

        public Driver[] newArray(int size) {
            return new Driver[size];
        }
    };
}
