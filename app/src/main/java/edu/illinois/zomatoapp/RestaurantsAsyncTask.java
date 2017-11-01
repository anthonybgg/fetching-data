package edu.illinois.zomatoapp;

import android.os.AsyncTask;
import android.provider.SearchRecentSuggestions;
import android.util.Log;

import com.google.gson.Gson;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.Charset;

public class RestaurantsAsyncTask extends AsyncTask<String, Integer, SearchingRestaurants> {

    public static final String TAG = RestaurantsAsyncTask.class.getSimpleName();

    @Override
    protected SearchingRestaurants doInBackground(String... strings) {
        try {
            URL url = new URL(strings[0]);
            URLConnection connection = url.openConnection();
            connection.setRequestProperty("user-key", RestaurantAPI.API_Key);
            connection.connect();

            InputStream inputStream = connection.getInputStream();
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream, Charset.forName("UTF-8"));

            Gson gson = new Gson();
            SearchingRestaurants searchingRestaurants;
            searchingRestaurants = gson.fromJson(inputStreamReader, SearchingRestaurants.class);
            return searchingRestaurants;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPostExecute(SearchingRestaurants searchingRestaurants) {
        if (searchingRestaurants == null) {
            return;
        }
        for (RestaurantsCollection restaurantsCollection: searchingRestaurants.getRestaurants()) {
            Log.d(TAG, "name: " + restaurantsCollection.getRestaurant().getName());
            Log.d(TAG, "location: " + restaurantsCollection.getRestaurant().getLocation().getAddress());
            Log.d(TAG, "price range: " + restaurantsCollection.getRestaurant().getPriceRange());
            Log.d(TAG, "cuisine: " + restaurantsCollection.getRestaurant().getCuisines());
        }
    }
}
