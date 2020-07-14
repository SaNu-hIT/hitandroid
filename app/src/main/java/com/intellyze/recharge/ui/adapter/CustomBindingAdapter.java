package com.intellyze.recharge.ui.adapter;

import android.widget.ImageView;

import androidx.databinding.BindingAdapter;

import com.intellyze.recharge.R;
import com.squareup.picasso.Picasso;

public class CustomBindingAdapter {
    @BindingAdapter({"bind:image_url"})
    public static void loadImage(ImageView imageView, String url)
    {

        Picasso.get()
                .load(url)
                .placeholder(R.drawable.ic_launcher_background)
                .error(R.drawable.ic_launcher_background)
                .into(imageView);


    }
}
