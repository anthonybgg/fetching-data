package edu.illinois.zomatoapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {
    public static final String urlString = "https://developers.zomato.com/api/v2.1/search";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        String url = urlString;
        RestaurantsAsyncTask restaurantsAsyncTask = new RestaurantsAsyncTask();
        restaurantsAsyncTask.execute(url);
    }
}
