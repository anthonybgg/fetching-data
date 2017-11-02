package edu.illinois.zomatoapp;

import com.google.gson.annotations.SerializedName;

public class Restaurant {
    private String name;
    private String cuisines;
    private Location location;
    private String thumb;
    private String city;

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
}
