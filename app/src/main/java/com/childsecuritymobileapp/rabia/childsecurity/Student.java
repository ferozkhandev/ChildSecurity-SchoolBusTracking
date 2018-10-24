package com.childsecuritymobileapp.rabia.childsecurity;

import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable;

public class Student extends UserData {
    private String childName, childClassName, childBatch;
    private final String defaultChildName = "";
    private final String defaultChildClassName = "";
    private final String defaultChildCLassBatch = "";

    Student()
    {
        setChildName(defaultChildName);
        setChildClassName(defaultChildClassName);
        setChildBatch(defaultChildCLassBatch);
    }
    Student(UserData userData)
    {
        setDisplayName(userData.getDisplayName());
        setEmail(userData.getEmail());
        setMobileNumber(userData.getMobileNumber());
        setPhotoUri(userData.getPhotoUri());
        setPassword(userData.getPassword());
        setChildName(defaultChildName);
        setChildClassName(defaultChildClassName);
        setChildBatch(defaultChildCLassBatch);
    }
    Student(Parcel in)
    {
        setDisplayName(in.readString());
        setEmail(in.readString());
        setMobileNumber(in.readString());
        setPhotoUri((Uri)in.readParcelable(Uri.class.getClassLoader()));
        setPassword(in.readString());
        setChildName(in.readString());
        setChildClassName(in.readString());
        setChildBatch(in.readString());
    }
    Student(String displayName, String email, String mobileNumber, Uri photoUri , String password, String childName, String childClassName, String childBatch)
    {
        super(displayName, email, mobileNumber, photoUri, password);
        if (childName != null)
        {
            setChildName(childName);
        }
        else
        {
            setChildName(defaultChildName);
        }
        if (childClassName != null)
        {
            setChildClassName(childClassName);
        }
        else
        {
            setChildClassName(defaultChildClassName);
        }
        if (childBatch != null)
        {
            setChildBatch(childBatch);
        }
        else
        {
            setChildBatch(defaultChildCLassBatch);
        }
    }

    //Setters
    public void setChildName(String childName) {
        this.childName = childName;
    }

    public void setChildClassName(String childClassName) {
        this.childClassName = childClassName;
    }

    public void setChildBatch(String childBatch) {
        this.childBatch = childBatch;
    }

    //Getters
    public String getChildName() {
        return childName;
    }

    public String getChildClassName() {
        return childClassName;
    }

    public String getChildBatch() {
        return childBatch;
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
        parcel.writeString(getChildName());
        parcel.writeString(getChildClassName());
        parcel.writeString(getChildBatch());
    }
    // This is to de-serialize the object
    public static final Parcelable.Creator<Student> CREATOR = new Parcelable.Creator<Student>(){
        public Student createFromParcel(Parcel in) {
            return new Student(in);
        }

        public Student[] newArray(int size) {
            return new Student[size];
        }
    };
}
