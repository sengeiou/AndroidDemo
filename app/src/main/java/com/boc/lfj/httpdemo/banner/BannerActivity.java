package com.boc.lfj.httpdemo.banner;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.boc.lfj.httpdemo.R;
import com.boc.lfj.httpdemo.banner.listener.OnBannerListener;
import com.boc.lfj.httpdemo.banner.loader.GlideImageLoader;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BannerActivity extends AppCompatActivity implements OnBannerListener {
    public static List<?> images=new ArrayList<>();
    public static List<String> titles=new ArrayList<>();
    Banner banner;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_banner);
        banner = (Banner) findViewById(R.id.banner);
        initData();
        //简单使用
        banner.setImages(images)
                .setImageLoader(new GlideImageLoader())
                .setOnBannerListener(this)
                .start();
    }

    private void initData() {
        String[] urls = getResources().getStringArray(R.array.url);
        String[] tips = getResources().getStringArray(R.array.title);
        List list = Arrays.asList(urls);
        images = new ArrayList(list);
        List list1 = Arrays.asList(tips);
        titles= new ArrayList(list1);
    }

    @Override
    public void OnBannerClick(int position) {

    }

    //如果你需要考虑更好的体验，可以这么操作
    @Override
    protected void onStart() {
        super.onStart();
        //开始轮播
        banner.startAutoPlay();
    }

    @Override
    protected void onStop() {
        super.onStop();
        //结束轮播
        banner.stopAutoPlay();
    }

}
