package edu.illinois.zomatoapp;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

/**
 * Created by anthonybongungu1 on 11/1/17.
 */

public class RestaurantAdapter extends RecyclerView.Adapter<RestaurantAdapter.ViewHolder> {

    private List<Restaurant> restaurants = new ArrayList<>();
    RestaurantAdapter(List<Restaurant> restaurants) {
        this.restaurants = restaurants;
    }
    void addRestaurant(Restaurant restaurant) {
        restaurants.add(restaurant);
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View restaurantItem = LayoutInflater.from(parent.getContext()).
                inflate(R.layout.restaurants_with_image, parent, false);
        return new ViewHolder(restaurantItem);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Restaurant restaurant = restaurants.get(position);
        holder.nameOfRestaurant.setText(restaurant.getName());
        holder.location.setText(restaurant.getLocation().getAddress());
        holder.cuisineType.setText(restaurant.getCuisines());
        holder.city.setText(restaurant.getLocation().getCity());
        if (restaurant.getThumb() != "") {
            Picasso.with(holder.imageView.getContext()).load(restaurant.getThumb()).into(holder.imageView);
        }
    }

    @Override
    public int getItemViewType(int position) {
        Restaurant restaurant =  restaurants.get(position);
        return (restaurant.getThumb() != null) ?
                R.layout.restaurants_with_image : R.layout.restaurants_layout;
    }

    @Override
    public int getItemCount() {
        return restaurants.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public View itemView;
        public ImageView imageView;
        public TextView nameOfRestaurant;
        public TextView location;
        public TextView city;
        public TextView cuisineType;

        public ViewHolder(View itemView) {
            super(itemView);
            this.itemView = itemView;
            this.imageView = (ImageView) itemView.findViewById(R.id.restaurantIcon);
            this.city = (TextView) itemView.findViewById(R.id.city);
            this.nameOfRestaurant = (TextView) itemView.findViewById(R.id.nameOfRestaurant);
            this.cuisineType = (TextView) itemView.findViewById(R.id.cuisineType);
            this.location = (TextView) itemView.findViewById(R.id.location);
        }
    }
}
