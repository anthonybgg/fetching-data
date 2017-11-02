package edu.illinois.zomatoapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;

public class MainActivity extends AppCompatActivity {
    public static final int NUMBER_OF_URL = 10;
    public static final String urlString =
            "https://developers.zomato.com/api/v2.1/search?entity_id=94741&entity_type=zone";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        String[] urls = new String[NUMBER_OF_URL];

        for (int i = 0; i < urls.length; i++) {
            urls[i] = urlString + "&start=" + 10 * i + "&count=10";
        }

        final RecyclerView recyclerView = (RecyclerView) findViewById(R.id.restaurantRecyclerView);
        RestaurantsAsyncTask restaurantsAsyncTask = new RestaurantsAsyncTask(this, recyclerView);
        restaurantsAsyncTask.execute(urls);
    }
}
