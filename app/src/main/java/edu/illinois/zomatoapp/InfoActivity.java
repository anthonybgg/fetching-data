package edu.illinois.zomatoapp;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.net.URLEncoder;

public class InfoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);
        final Intent intent = getIntent();
        Restaurant restaurant = intent.getParcelableExtra("restaurant");

        // Set the different views to their proper View.
        final ImageView imageView = (ImageView) findViewById(R.id.imageView);
        final TextView restaurantName = (TextView) findViewById(R.id.restaurantName_info);
        final TextView city = (TextView) findViewById(R.id.city_info);
        final TextView avgCost = (TextView) findViewById(R.id.avgCost);
        final TextView priceRange = (TextView) findViewById(R.id.priceRange_info);
        final TextView cuisineType = (TextView) findViewById(R.id.typeCuisine_info);

        // Get the URL image and put it into the ImageView
        final String imgURL = restaurant.getThumb();
        Picasso.with(this).load(imgURL).into(imageView);

        // Set the text for each view.
        restaurantName.setText(restaurant.getName());
        city.setText(restaurant.getLocation().getCity());
        cuisineType.setText(restaurant.getCuisines());

        String avgCostForTwo = getAvgCostForTwo(restaurant);
        avgCost.setText(avgCostForTwo);

        String dollar = getPriceRangeToDollars(restaurant);
        priceRange.setText(dollar);

        setAddressClickable(restaurant);
        setWebsiteLinkClickable(restaurant);
    }

    /**
     * This function will convert the price range into dollar signs.
     * @param restaurant is the restaurant with all of its attribute.
     * @return a string of dollars signs.
     */
    private String getPriceRangeToDollars(Restaurant restaurant) {
        StringBuilder dollarSign = new StringBuilder();
        int price = restaurant.getPriceRange();
        for (int i = 0; i < price; i++) {
            dollarSign.append("$");
        }
        return dollarSign.toString();
    }

    /**
     * This function will get the Average Cost For Two.
     * @param restaurant is the restaurant with all of its attribute.
     * @return a string of the price for two.
     */
    private String getAvgCostForTwo(Restaurant restaurant) {
        String avgCost = String.valueOf(restaurant.getAvgCostForTwo());
        String finalAvgCost = "Average Cost For Two: ";
        return finalAvgCost + avgCost + "$";
    }

    /**
     * This function will open the web browser if the button is clicked.
     * It will be set to enable if the restaurant provides an URL.
     * @param restaurant is the restaurant with all of its attribute.
     */
    private void setWebsiteLinkClickable(Restaurant restaurant) {
        Button link = (Button) findViewById(R.id.zomatoLink);
        final String url = restaurant.getUrl();
        try {
            link.setEnabled(true);
            link.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    final Context context = view.getContext();
                    Uri uriLink = Uri.parse(url);
                    Intent browserIntent = new Intent(Intent.ACTION_VIEW, uriLink);
                    if (browserIntent.resolveActivity(context.getPackageManager()) != null) {
                        context.startActivity(browserIntent);
                    }
                }
            });
        } catch (Exception e) {
            link.setEnabled(false);
        }
    }

    /**
     * The function will set Address Button clickable if the address is correct.
     * @param restaurant is the restaurant with all of its attribute.
     */
    private void setAddressClickable(Restaurant restaurant) {
        final Button addressButton = (Button) findViewById(R.id.address_info);
        final String address = restaurant.getLocation().getAddress();
        try {
            final String encodedLocation = URLEncoder.encode(address, "UTF-8");
            addressButton.setEnabled(true);
            addressButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    final Context context = view.getContext();
                    Uri locationUri = Uri.parse("geo:0,0?q=" + encodedLocation);
                    Intent intent = new Intent(Intent.ACTION_VIEW, locationUri);
                    if (intent.resolveActivity(context.getPackageManager()) != null) {
                        context.startActivity(intent);
                    }
                }
            });
        } catch (Exception e)  {
            addressButton.setEnabled(false);
        }
    }
}
