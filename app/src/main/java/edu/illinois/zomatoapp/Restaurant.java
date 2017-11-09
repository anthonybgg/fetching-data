package edu.illinois.zomatoapp;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class Restaurant implements Parcelable {
    private String name;
    private String cuisines;
    private Location location;
    private String thumb;
    private String city;
    @SerializedName("price_range")
    private int priceRange;
    @SerializedName("average_cost_for_two")
    private int avgCostForTwo;
    private String url;

    public String getUrl() {
        return url;
    }
    
    public String getThumb() {
        return thumb;
    }

    public String getName() {
        return name;
    }

    public String getCuisines() {
        return cuisines;
    }

    public Location getLocation() {
        return location;
    }

    public int getPriceRange() {
        return priceRange;
    }

    public int getAvgCostForTwo() {
        return avgCostForTwo;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.name);
        dest.writeString(this.cuisines);
        dest.writeParcelable(this.location, flags);
        dest.writeString(this.thumb);
        dest.writeString(this.city);
        dest.writeInt(this.priceRange);
        dest.writeInt(this.avgCostForTwo);
        dest.writeString(this.url);
    }

    public Restaurant() {
    }

    protected Restaurant(Parcel in) {
        this.name = in.readString();
        this.cuisines = in.readString();
        this.location = in.readParcelable(Location.class.getClassLoader());
        this.thumb = in.readString();
        this.city = in.readString();
        this.priceRange = in.readInt();
        this.avgCostForTwo = in.readInt();
        this.url = in.readString();
    }

    public static final Parcelable.Creator<Restaurant> CREATOR = new Parcelable.Creator<Restaurant>() {
        @Override
        public Restaurant createFromParcel(Parcel source) {
            return new Restaurant(source);
        }

        @Override
        public Restaurant[] newArray(int size) {
            return new Restaurant[size];
        }
    };
}
