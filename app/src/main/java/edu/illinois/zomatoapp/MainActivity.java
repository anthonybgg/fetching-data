package edu.illinois.zomatoapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity {
    public static final String urlString = "https://developers.zomato.com/api/v2.1/search?entity_id=685";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final LinearLayout listLayout = (LinearLayout) findViewById(R.id.list_layout);
        String url = urlString;
        RestaurantsAsyncTask restaurantsAsyncTask = new RestaurantsAsyncTask(this, listLayout);
        restaurantsAsyncTask.execute(url);
    }
}
