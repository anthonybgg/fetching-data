package edu.illinois.zomatoapp;

import android.os.Parcel;
import android.os.Parcelable;

public class Location implements Parcelable {
    private String address;
    private String city;
    private int city_id;
    private String zipcode;
    private String latitude;
    private String longitude;

    public String getAddress() {
        return address;
    }

    public String getCity() {
        return city;
    }

    public int getCity_id() {
        return city_id;
    }

    public String getZipcode() {
        return zipcode;
    }

    public String getLatitude() {
        return latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.address);
        dest.writeString(this.city);
        dest.writeInt(this.city_id);
        dest.writeString(this.zipcode);
        dest.writeString(this.latitude);
        dest.writeString(this.longitude);
    }

    public Location() {
    }

    protected Location(Parcel in) {
        this.address = in.readString();
        this.city = in.readString();
        this.city_id = in.readInt();
        this.zipcode = in.readString();
        this.latitude = in.readString();
        this.longitude = in.readString();
    }

    public static final Parcelable.Creator<Location> CREATOR = new Parcelable.Creator<Location>() {
        @Override
        public Location createFromParcel(Parcel source) {
            return new Location(source);
        }

        @Override
        public Location[] newArray(int size) {
            return new Location[size];
        }
    };
}
