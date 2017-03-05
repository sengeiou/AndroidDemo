package com.boc.lfj.httpdemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import com.boc.lfj.httpdemo.transformations.picasso.CropCircleTransformation;
import com.bumptech.glide.Glide;
import com.squareup.picasso.Picasso;

public class LoadImageActivity extends AppCompatActivity {
    private ImageView imageView,imageView1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_load_image);
        imageView = (ImageView) findViewById(R.id.imageView);
        imageView1 = (ImageView) findViewById(R.id.imageView1);
        String url ="http://p3.so.qhmsg.com/t011fd64776e672353d.jpg";

        Glide.with(LoadImageActivity.this)
                .load(url)
//                .placeholder(android.R.drawable.btn_dialog)
                .bitmapTransform(new com.boc.lfj.httpdemo.transformations.glide.CropCircleTransformation(LoadImageActivity.this))
                .crossFade()
                .into(imageView);

        Picasso.with(LoadImageActivity.this)
                .load(url)
                .placeholder(android.R.drawable.btn_dialog)
//                .error()
                .transform(new com.boc.lfj.httpdemo.transformations.picasso.CropCircleTransformation())
                .into(imageView1);
    }
}
