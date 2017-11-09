package edu.illinois.zomatoapp;

import com.google.gson.annotations.SerializedName;

public class Restaurant {
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
}
