package com.boc.lfj.httpdemo;

import android.app.Notification;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.support.design.widget.CoordinatorLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.boc.lfj.httpdemo.asynctask.AsyncTaskActivity;
import com.boc.lfj.httpdemo.banner.BannerActivity;
import com.boc.lfj.httpdemo.bean.BaseResponseBean;
import com.boc.lfj.httpdemo.bean.message.QueryMessageCriteria;
import com.boc.lfj.httpdemo.bean.message.QueryMessageResponse;
import com.boc.lfj.httpdemo.common.SPUtils;
import com.boc.lfj.httpdemo.common.ToastUtils;
import com.boc.lfj.httpdemo.dispatchevent.DispatchEventActivity;
import com.boc.lfj.httpdemo.exoplayer.PlayerActivity;
import com.boc.lfj.httpdemo.map.MapActivity;
import com.boc.lfj.httpdemo.news.ScrollingActivity;
import com.boc.lfj.httpdemo.progress.ProgressActivity;
import com.boc.lfj.httpdemo.rainbar.RainBarActivity;
import com.boc.lfj.httpdemo.recycleview.RecycleViewActivity;
import com.boc.lfj.httpdemo.refresh.RefreshActivity;
import com.boc.lfj.httpdemo.repeatclick.RepeatyActivity;
import com.boc.lfj.httpdemo.retrofit.NetService;
import com.boc.lfj.httpdemo.retrofit.ServiceGenerator;
import com.boc.lfj.httpdemo.rx.BaseSubscriber;
import com.boc.lfj.httpdemo.rx.RxNetService;
import com.boc.lfj.httpdemo.rx.RxServiceGenerator;
import com.boc.lfj.httpdemo.service.MyIntentService;
import com.boc.lfj.httpdemo.service.ServiceActivity;
import com.boc.lfj.httpdemo.service.UndeadService;
import com.boc.lfj.httpdemo.special.ColorFulStatusBarActivity;
import com.boc.lfj.httpdemo.special.CoordinatorLayoutActivity;
import com.boc.lfj.httpdemo.special.FadeInOutActivity;
import com.boc.lfj.httpdemo.special.SearchViewActivity;
import com.boc.lfj.httpdemo.special.SpecialViewActivity;
import com.boc.lfj.httpdemo.swipdelete.SwipDeleteActivity;
import com.boc.lfj.httpdemo.xml.XmlParsePullActivity;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;


public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {
    private ListView mListView;
    private List<String> mList = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initData();
        mListView = (ListView) findViewById(R.id.listview);
        mListView.setOnItemClickListener(this);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(MainActivity.this, android.R.layout.simple_list_item_1, mList);
        mListView.setAdapter(adapter);
    }

    private void initData() {
        mList.add("Retrofit 固定请求头");
        mList.add("Retrofit 动态+固定请求头");
        mList.add("Glide Picasso 图片加载");
        mList.add("View 的事件分发");
        mList.add("Retrofit RxAndroid");
        mList.add("RecycleView");
        mList.add("RecycleView Refreshing");
        mList.add("Exoplayer");
        mList.add("Service");
        mList.add("Progressbar");
        mList.add("侧滑删除");
        mList.add("自定义RainBar");
        mList.add("AsyncTask");
        mList.add("XML解析");
        mList.add("监听连续点击事件");
        mList.add("各种定制View");
        mList.add("百度地图");
        mList.add("SearchView");
        mList.add("CoordinatorLayout");
        mList.add("Fade in out");
        mList.add("Banner");
        mList.add("5.0新控件");
        mList.add("沉浸式状态栏");
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        if (position == 0) {
            queryMessages();
        }
        if (position == 1) {
            //查询可踢出成员
            queryDeletable();
        }

        if (position == 2) {
            startActivity(new Intent(MainActivity.this, LoadImageActivity.class));
        }

        if (position == 3) {
            startActivity(new Intent(MainActivity.this, DispatchEventActivity.class));
        }

        if (position == 4) {
            queryDeletableRx();
        }

        if (position == 5) {
            startActivity(new Intent(MainActivity.this, RecycleViewActivity.class));
        }

        if (position == 6) {
            startActivity(new Intent(MainActivity.this, RefreshActivity.class));
        }

        if (position == 7) {
            startActivity(new Intent(MainActivity.this, PlayerActivity.class));
        }

        if (position == 8) {//service 相关
            Intent intent = new Intent(MainActivity.this,ServiceActivity.class);
            startActivity(intent);
        }
        if (position == 9) {
            startActivity(new Intent(MainActivity.this, ProgressActivity.class));
        }

        if (position == 10) {
            startActivity(new Intent(MainActivity.this, SwipDeleteActivity.class));
        }

        if (position == 11) {
            startActivity(new Intent(MainActivity.this, RainBarActivity.class));
        }
        if (position == 12) {
            startActivity(new Intent(MainActivity.this, AsyncTaskActivity.class));
        }
        if (position == 13) {
            startActivity(new Intent(MainActivity.this, XmlParsePullActivity.class));
        }
        if (position == 14) {
            startActivity(new Intent(MainActivity.this, RepeatyActivity.class));
        }
        if (position == 15) {
            startActivity(new Intent(MainActivity.this, SpecialViewActivity.class));
        }

        if (position == 16) {
            startActivity(new Intent(MainActivity.this, MapActivity.class));
        }

        if (position == 17) {
            startActivity(new Intent(MainActivity.this, SearchViewActivity.class));
        }
        if (position == 18) {
            startActivity(new Intent(MainActivity.this, CoordinatorLayoutActivity.class));
        }

        if (position == 19) {
            startActivity(new Intent(MainActivity.this, FadeInOutActivity.class));
        }
        if (position == 20) {
            startActivity(new Intent(MainActivity.this, BannerActivity.class));
        }
        if (position == 21) {
            startActivity(new Intent(MainActivity.this, ScrollingActivity.class));
        }
        if (position == 22) {
            startActivity(new Intent(MainActivity.this, ColorFulStatusBarActivity.class));
        }
    }


    private void queryMessages() {
        NetService service = ServiceGenerator.create(NetService.class);
        QueryMessageCriteria criteria = new QueryMessageCriteria();
        criteria.setUid(SPUtils.getUid());
        criteria.setPageNo("" + 1);
        Call<QueryMessageResponse> call = service.queryMessages(criteria);
        call.enqueue(new Callback<QueryMessageResponse>() {
            @Override
            public void onResponse(Call<QueryMessageResponse> call, Response<QueryMessageResponse> response) {
                ToastUtils.showLongMsg(MainActivity.this, "query messages success");
            }

            @Override
            public void onFailure(Call<QueryMessageResponse> call, Throwable throwable) {
                ToastUtils.showLongMsg(MainActivity.this, "query messages failure");
            }
        });
    }

    private void queryDeletable() {
        NetService service = ServiceGenerator.create(NetService.class);
        Call<BaseResponseBean> call = service.queryDeletable("32");
        call.enqueue(new Callback<BaseResponseBean>() {
            @Override
            public void onResponse(Call<BaseResponseBean> call, Response<BaseResponseBean> response) {
                ToastUtils.showLongMsg(MainActivity.this, "query delete success");
            }

            @Override
            public void onFailure(Call<BaseResponseBean> call, Throwable throwable) {
                ToastUtils.showLongMsg(MainActivity.this, "query messages failure");
            }
        });
    }

    private void queryDeletableRx() {
        RxNetService service = RxServiceGenerator.create(RxNetService.class);
        Subscription subscription = service.queryDeletable("32")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseSubscriber<BaseResponseBean>() {
                    @Override
                    public void next(BaseResponseBean o) {

                    }
                });
    }




}
