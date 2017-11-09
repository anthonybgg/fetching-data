package edu.illinois.zomatoapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class InfoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);
        final Intent intent = getIntent();
        Restaurant restaurant = intent.getParcelableExtra("restaurant");
        final String imgURL = restaurant.getThumb();

        final ImageView imageView = (ImageView) findViewById(R.id.imageView);
        final TextView restaurantName = (TextView) findViewById(R.id.restaurantName_info);
        final TextView address = (TextView) findViewById(R.id.address_info);
        final TextView city = (TextView) findViewById(R.id.city_info);
        final TextView avgCost = (TextView) findViewById(R.id.avgCost);
        final TextView priceRange = (TextView) findViewById(R.id.priceRange_info);
        final TextView cuisineType = (TextView) findViewById(R.id.typeCuisine_info);
        Picasso.with(this).load(imgURL).into(imageView);

        restaurantName.setText(restaurant.getName());
        address.setText(restaurant.getLocation().getAddress());
        city.setText(restaurant.getLocation().getCity());
        cuisineType.setText(restaurant.getCuisines());

        String avgCostForTwo = getAvgCostForTwo(restaurant);
        avgCost.setText(avgCostForTwo);

        String dollar = getPriceRangeToDollars(restaurant);
        priceRange.setText(dollar);
    }

    private String getPriceRangeToDollars(Restaurant restaurant) {
        StringBuilder dollarSign = new StringBuilder();
        int price = restaurant.getPriceRange();
        for (int i = 0; i < price; i++) {
            dollarSign.append("$");
        }
        return dollarSign.toString();
    }

    private String getAvgCostForTwo(Restaurant restaurant) {
        String avgCost = String.valueOf(restaurant.getAvgCostForTwo());
        String finalAvgCost = "Average Cost For Two: ";
        return finalAvgCost + avgCost;
    }
}
