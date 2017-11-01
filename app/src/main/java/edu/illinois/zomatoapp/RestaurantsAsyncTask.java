package edu.illinois.zomatoapp;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.AsyncTask;
import android.provider.SearchRecentSuggestions;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.gson.Gson;

import org.w3c.dom.Text;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.Charset;

public class RestaurantsAsyncTask extends AsyncTask<String, Integer, SearchingRestaurants> {

    private static final String TAG = RestaurantsAsyncTask.class.getSimpleName();
    @SuppressLint("StaticFieldLeak")
    private Context context;
    @SuppressLint("StaticFieldLeak")
    private LinearLayout listLayout;
    RestaurantsAsyncTask(Context context, LinearLayout listLayout) {
        this.context = context;
        this.listLayout = listLayout;
    }

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
        } catch (MalformedURLException e) {
            e.printStackTrace();
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
            View restaurantsList = LayoutInflater.from(context).inflate(R.layout.restaurants_layout,
                    listLayout, false);
            final TextView nameOfRestaurant = (TextView) restaurantsList.findViewById(R.id.nameOfRestaurant);
            nameOfRestaurant.setText(restaurantsCollection.getRestaurant().getName());
            final TextView location = (TextView) restaurantsList.findViewById(R.id.location);
            location.setText(restaurantsCollection.getRestaurant().getLocation().getAddress());
//            final TextView priceRange = (TextView) restaurantsList.findViewById(R.id.priceRange);
//            priceRange.setText(restaurantsCollection.getRestaurant().getPriceRange());
            listLayout.addView(restaurantsList);
        }
    }
}
