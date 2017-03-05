package com.boc.lfj.httpdemo.refresh;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.util.TypedValue;
import android.widget.Toast;

import com.boc.lfj.httpdemo.R;
import com.boc.lfj.httpdemo.common.ToastUtils;
import com.boc.lfj.httpdemo.recycleview.DividerGridItemDecoration;
import com.boc.lfj.httpdemo.recycleview.RecycleViewAdapter;

import java.util.ArrayList;
import java.util.List;

public class RefreshActivity extends AppCompatActivity {
    private SwipeRefreshLayout mSwipeRefreshWidget;
    private RecyclerView mRecyclerView;
    private SampleAdapter adapter;
    private List<String> mDatas = new ArrayList<String>();
    private LinearLayoutManager mLayoutManager;
    int lastVisibleItem;
    boolean isLoadingMore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_refresh);
        mLayoutManager = new LinearLayoutManager(this);

        mSwipeRefreshWidget = (SwipeRefreshLayout) findViewById(R.id.swipe_refresh_widget);
        mRecyclerView = (RecyclerView) findViewById(android.R.id.list);
        mSwipeRefreshWidget.post(new Runnable() {

            @Override
            public void run() {
                mSwipeRefreshWidget.setRefreshing(true);
            }
        });
        initData();
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.addItemDecoration(new DividerGridItemDecoration(this));
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        adapter = new SampleAdapter(mDatas);
        mRecyclerView.setAdapter(adapter);

        // 这句话是为了，第一次进入页面的时候显示加载进度条
        mSwipeRefreshWidget.setProgressViewOffset(false, 0, (int) TypedValue
                .applyDimension(TypedValue.COMPLEX_UNIT_DIP, 24, getResources()
                        .getDisplayMetrics()));
        mSwipeRefreshWidget.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mDatas.clear();
                initData();
            }
        });

        mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                lastVisibleItem = mLayoutManager.findLastVisibleItemPosition();

                int lastVisibleItem = mLayoutManager.findLastVisibleItemPosition();
                int totalItemCount = mLayoutManager.getItemCount();
                //lastVisibleItem >= totalItemCount - 4 表示剩下4个item自动加载，各位自由选择
                // dy>0 表示向下滑动
                if (lastVisibleItem >= totalItemCount - 4 && dy > 0) {
                    if (isLoadingMore) {
                        Log.d("refresh", "ignore manually update!");
                    } else {
                        initData();
                    }
                }
            }

            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
//                if (newState == RecyclerView.SCROLL_STATE_IDLE
//                        && lastVisibleItem + 1 == adapter.getItemCount()) {
//                    mSwipeRefreshWidget.setRefreshing(true);
//                    // 此处在现实项目中，请换成网络请求数据代码，sendRequest .....
//                    ToastUtils.showLongMsg(RefreshActivity.this,"刷新");
//                }
            }
        });
    }

    protected void initData() {
        if (adapter!=null) {
            adapter.setState(SampleAdapter.STATE_LOADING);
        }
        new Thread() {
            @Override
            public void run() {
                super.run();
                try {
                    isLoadingMore = true;

                    Thread.sleep(1000);
                    int index = mDatas.size();
                    for (int i = index; i < index + 20; i++) {
                        mDatas.add("第" + i + "个数据");
                    }
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            isLoadingMore = false;
                            adapter.setState(SampleAdapter.STATE_INVISIBLE);
                            mSwipeRefreshWidget.setRefreshing(false);
                            adapter.notifyDataSetChanged();
                        }
                    });
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }.start();

    }
}
