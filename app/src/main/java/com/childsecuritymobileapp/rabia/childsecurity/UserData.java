package com.childsecuritymobileapp.rabia.childsecurity;

import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable;

public class UserData implements Parcelable
{
    private String displayName, email, mobileNumber, password;
    private Uri photoUri;
    private final String defaultDisplayName = "App user";
    private final String defaultEmail = "";
    private final String defaultMobileNumber = "";
    private final String defaultPassword = "";
    private String photoUrl = "https://cdn.iconscout.com/public/images/icon/premium/png-512/baby-safety-security-protect-insurance-3cdff6482fe3cce3-512x512.png";
    private final Uri defaultPhotoUri = Uri.parse(photoUrl);

    UserData()
    {
        setDisplayName(defaultDisplayName);
        setEmail(defaultEmail);
        setMobileNumber(defaultMobileNumber);
        setPhotoUri(defaultPhotoUri);
        setPassword(defaultPassword);
    }
    UserData(Parcel in)
    {
        setDisplayName(in.readString());
        setEmail(in.readString());
        setMobileNumber(in.readString());
        setPhotoUri((Uri)in.readParcelable(Uri.class.getClassLoader()));
        setPassword(in.readString());
    }
    UserData(String displayName, String email, String mobileNumber, Uri photoUri, String password)
    {
        if(displayName != null)
        {
            setDisplayName(displayName);
        }
        else
        {
            setDisplayName(defaultDisplayName);
        }
        if (email != null)
        {
            setEmail(email);
        }
        else
        {
            setEmail(defaultEmail);
        }
        if (mobileNumber != null)
        {
            setMobileNumber(mobileNumber);
        }
        else
        {
            setMobileNumber(defaultMobileNumber);
        }
        //!Uri.EMPTY.equals(photoUri)
        if (photoUri != null)
        {
            setPhotoUri(photoUri);
        }
        else
        {
            setPhotoUri(defaultPhotoUri);
        }
        if (password != null)
        {
            setPassword(password);
        }
        else
        {
            setPassword(defaultPassword);
        }
    }


    //Setters
    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public void setPhotoUri(Uri photoUri) {
        this.photoUri = photoUri;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    //Getters
    public String getDisplayName() {
        return displayName;
    }

    public Uri getPhotoUri() {
        return photoUri;
    }

    public String getEmail() {
        return email;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public String getPassword() {
        return password;
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
    }
    // This is to de-serialize the object
    public static final Parcelable.Creator<UserData> CREATOR = new Parcelable.Creator<UserData>(){
        public UserData createFromParcel(Parcel in) {
            return new UserData(in);
        }

        public UserData[] newArray(int size) {
            return new UserData[size];
        }
    };
}
