package edu.illinois.zomatoapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

public class InfoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);
        final Intent intent = getIntent();
        final String imgURL = intent.getStringExtra(RestaurantAdapter.IMG_URL);
        final ImageView imageView = (ImageView) findViewById(R.id.imageView);
        Picasso.with(this).load(imgURL).into(imageView);
    }
}
