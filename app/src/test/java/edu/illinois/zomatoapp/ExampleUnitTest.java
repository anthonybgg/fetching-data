package edu.illinois.zomatoapp;

import com.google.gson.Gson;

import org.junit.Test;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.Charset;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    private static final String urlString = "https://developers.zomato.com/api/v2.1/search?entity_id=685&cuisines=73";
    @Test
    public void addition_isCorrect() throws Exception {
        assertEquals(4, 2 + 2);
    }
    @Test
    public void getName() throws Exception {

        URL url = new URL(urlString);
        URLConnection connection = url.openConnection();
        connection.setRequestProperty("user-key", RestaurantAPI.API_Key);
        connection.connect();
        InputStream inputStream = connection.getInputStream();
        InputStreamReader inputStreamReader = new InputStreamReader(inputStream, Charset.forName("UTF-8"));
        Gson gson = new Gson();
        SearchingRestaurants searchingRestaurants = gson.fromJson(inputStreamReader, SearchingRestaurants.class);
        for (RestaurantsCollection restaurantsCollection : searchingRestaurants.getRestaurants()) {
            System.out.println(restaurantsCollection.getRestaurant().getName());
            System.out.println("    " + restaurantsCollection.getRestaurant().getLocation().getAddress());
        }
    }
}