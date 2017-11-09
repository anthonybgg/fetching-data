package edu.illinois.zomatoapp;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.net.URLEncoder;
import java.util.ArrayList;

import java.util.List;

/**
 * Created by anthonybongungu1 on 11/1/17.
 */

public class RestaurantAdapter extends RecyclerView.Adapter<RestaurantAdapter.ViewHolder> {
    public static final String IMG_URL = "img-ur";
    private List<Restaurant> restaurants = new ArrayList<>();

    /**
     * Constructor that passes list of restaurants.
     * @param restaurants in the list.
     */
    RestaurantAdapter(List<Restaurant> restaurants) {
        this.restaurants = restaurants;
    }

    /**
     * This function adds a restaurant into the list of restaurant.
     * @param restaurant is a restaurant.
     */
    void addRestaurant(Restaurant restaurant) {
        restaurants.add(restaurant);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View restaurantItem = LayoutInflater.from(parent.getContext()).
                inflate(R.layout.restaurants_with_image, parent, false);
        // Create a new view holder as we need.
        return new ViewHolder(restaurantItem);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        // Get the position in the list to fill the ViewHolder.
        final Restaurant restaurant = restaurants.get(position);
        // Fill the viewHolder with the information.
        holder.nameOfRestaurant.setText(restaurant.getName());
        final String address = restaurant.getLocation().getAddress();
        holder.location.setText(address);

        try {
            final String encodedLocation = URLEncoder.encode(address, "UTF-8");
            holder.location.setEnabled(true);
            holder.location.setOnClickListener(new View.OnClickListener() {
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
            holder.location.setEnabled(false);
        }

        holder.cuisineType.setText(restaurant.getCuisines());
        holder.city.setText(restaurant.getLocation().getCity());
        if (!restaurant.getThumb().equals("")) {
            Picasso.with(holder.imageView.getContext()).load(restaurant.getThumb()).into(holder.imageView);
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    final Context context = view.getContext();
                    Intent infoIntent = new Intent(context, InfoActivity.class);
                    infoIntent.putExtra(IMG_URL, restaurant.getThumb());
                    context.startActivity(infoIntent);
                }
            });
        } else {
            holder.itemView.setOnClickListener(null);
        }


    }

    @Override
    public int getItemViewType(int position) {
        Restaurant restaurant =  restaurants.get(position);
        return (restaurant.getThumb().equals("")) ?
                R.layout.restaurants_layout : R.layout.restaurants_with_image;
    }

    @Override
    public int getItemCount() {
        // How big the scroll bar will be
        return restaurants.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        // Pointer to the various things to plug data in
        View itemView;
        ImageView imageView;
        TextView nameOfRestaurant;
        Button location;
        TextView city;
        TextView cuisineType;

        ViewHolder(View itemView) {
            super(itemView);
            this.itemView = itemView;
            this.imageView = (ImageView) itemView.findViewById(R.id.restaurantIcon);
            this.city = (TextView) itemView.findViewById(R.id.city);
            this.nameOfRestaurant = (TextView) itemView.findViewById(R.id.nameOfRestaurant);
            this.cuisineType = (TextView) itemView.findViewById(R.id.cuisineType);
            this.location = (Button) itemView.findViewById(R.id.location);
        }
    }
}
