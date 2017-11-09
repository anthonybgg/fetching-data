package edu.illinois.zomatoapp;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Handler;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.google.gson.Gson;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.Charset;
import java.util.ArrayList;

public class RestaurantsAsyncTask extends AsyncTask<String, Integer, ArrayList<Restaurant>> {

    private Context context;
    private RecyclerView restaurantLayout;

    /**
     * Constructor for the AsyncTask
     * @param context is the environment.
     * @param restaurantLayout is the RecyclerView Layout.
     */
    RestaurantsAsyncTask(Context context, RecyclerView restaurantLayout) {
        this.context = context;
        this.restaurantLayout = restaurantLayout;
    }

    @Override
    protected ArrayList<Restaurant> doInBackground(String... strings) {
        ArrayList<Restaurant> restaurants = new ArrayList<>();
        try {
            for (String stringURL : strings) {
                URL url = new URL(stringURL);
                URLConnection connection = url.openConnection();
                // Get the API
                connection.setRequestProperty("user-key", RestaurantAPI.API_Key);
                connection.connect();

                InputStream inputStream = connection.getInputStream();
                InputStreamReader inputStreamReader = new InputStreamReader(inputStream, Charset.forName("UTF-8"));

                Gson gson = new Gson();
                SearchingRestaurants searchingRestaurants;
                searchingRestaurants = gson.fromJson(inputStreamReader, SearchingRestaurants.class);
                for (int i = 0; i < searchingRestaurants.getRestaurants().length; i++) {
                    // Add the restaurant at that specific index.
                    restaurants.add(searchingRestaurants.getRestaurants()[i].getRestaurant());
                }
            }
            return restaurants;
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPostExecute(ArrayList<Restaurant> restaurants) {
        if (restaurants == null) {
            return;
        }
        // Make a new Restaurant Adapter and pass in restaurants.
        final RestaurantAdapter restaurantAdapter = new RestaurantAdapter(restaurants);
        // Fill out the recycler view
        restaurantLayout.setAdapter(restaurantAdapter);
        restaurantLayout.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false));

        for (final Restaurant restaurant: restaurants) {
            final Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    restaurantAdapter.addRestaurant(restaurant);
                    restaurantAdapter.notifyDataSetChanged();
                }
            }, 2000);

        }
    }
}
