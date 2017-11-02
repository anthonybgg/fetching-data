package edu.illinois.zomatoapp;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Handler;
import android.provider.SearchRecentSuggestions;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
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
import java.util.ArrayList;

public class RestaurantsAsyncTask extends AsyncTask<String, Integer, ArrayList<Restaurant>> {

    private static final String TAG = RestaurantsAsyncTask.class.getSimpleName();
    @SuppressLint("StaticFieldLeak")
    private Context context;
    @SuppressLint("StaticFieldLeak")
    private RecyclerView restaurantLayout;
    RestaurantsAsyncTask(Context context, RecyclerView restaurantLayout) {
        this.context = context;
        this.restaurantLayout = restaurantLayout;
    }

    @Override
    protected ArrayList<Restaurant> doInBackground(String... strings) {
        ArrayList<Restaurant> restaurants = new ArrayList<>();
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
            for (int i = 0; i < searchingRestaurants.getRestaurants().length; i++) {
                restaurants.add(searchingRestaurants.getRestaurants()[i].getRestaurant());
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

        final RestaurantAdapter restaurantAdapter = new RestaurantAdapter(restaurants);
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
            }, 3000);


//            View restaurantsList = LayoutInflater.from(context).inflate(R.layout.restaurants_layout,
//                    listLayout, false);
//            final TextView nameOfRestaurant = (TextView) restaurantsList.findViewById(R.id.nameOfRestaurant);
//            nameOfRestaurant.setText(restaurant.getName());
//            final TextView location = (TextView) restaurantsList.findViewById(R.id.location);
//            location.setText(restaurant.getLocation().getAddress());
//            final TextView city = (TextView) restaurantsList.findViewById(R.id.city);
//            city.setText(restaurant.getLocation().getCity());
//            final TextView cuisineType = (TextView) restaurantsList.findViewById(R.id.cuisineType);
//            cuisineType.setText(restaurant.getCuisines());
//            listLayout.addView(restaurantsList);
        }
    }
}
