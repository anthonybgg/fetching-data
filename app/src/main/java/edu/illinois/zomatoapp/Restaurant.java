package edu.illinois.zomatoapp;

import com.google.gson.annotations.SerializedName;

/**
 * Created by anthonybongungu1 on 10/30/17.
 */


public class Restaurant {
    private String name;
    private String cuisines;
    private Location location;
    @SerializedName("price_range")
    private int priceRange;

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

}
