package com.boc.lfj.httpdemo.powerrv.demo.simple;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;


import com.boc.lfj.httpdemo.R;
import com.boc.lfj.httpdemo.powerrv.PowerAdapter;
import com.boc.lfj.httpdemo.powerrv.PowerRecyclerView;
import com.boc.lfj.httpdemo.powerrv.SpacesItemDecoration;
import com.boc.lfj.httpdemo.powerrv.TouchHelperCallback;

import java.util.ArrayList;
import java.util.List;

public class RefreshActivity extends AppCompatActivity implements PowerRecyclerView.OnRefreshLoadMoreListener{
    PowerRecyclerView mRecycleView;
    private PowerAdapter mAdapter;
    List<BaseBean> mList=new ArrayList<>();
    private SpacesItemDecoration decor;
    private Runnable refreshAction;
    private Runnable loadMoreAction;
    private boolean isRun;
    private static final int DEFAULT_TIME = 1000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_refresh2);
        mRecycleView = (PowerRecyclerView) findViewById(R.id.recycle_view);
        mAdapter = new SimpleAdapter();
        mAdapter.setList(mList);
        GridLayoutManager manager = new GridLayoutManager(this, 3);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        mRecycleView.setLayoutManager(layoutManager);
        mRecycleView.setSpanSizeCallBack(new PowerRecyclerView.SpanSizeCallBack() {
            @Override
            public int getSpanSize(int position) {
                return 1;
            }
        });

        decor = new SpacesItemDecoration(30, 3, true);
//        mRecycleView.getRecycle().addItemDecoration(decor);
        mRecycleView.getRecycle().addItemDecoration(new DividerGridItemDecoration(this));
        mRecycleView.setAdapter(mAdapter);
        mRecycleView.setOnRefreshListener(this);
        //初始化一个TouchHelperCallback
        TouchHelperCallback callback = new TouchHelperCallback();
        //添加一个回调
        callback.setItemDragSwipeCallBack(mAdapter);
        //初始化一个ItemTouchHelper
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(callback);
        //关联相关的RecycleView
        itemTouchHelper.attachToRecyclerView(mRecycleView.getRecycle());

        mAdapter.setLoadMoreListener(this);
        refreshAction = new Runnable() {
            @Override
            public void run() {
                mList=new ArrayList<>();
                for (int i = 0; i < 40; i++) {
                    mList.add(new BaseBean("这是" + i));
                }
                mAdapter.setList(mList);
                mRecycleView.setRefresh(false);
            }
        };
        loadMoreAction = new Runnable() {
            @Override
            public void run() {

                int i = ((int) (Math.random() * 10)) % 3;
                if (i == 0 || i == 1) {
                    ArrayList<BaseBean> arrayList = new ArrayList<>();
                    arrayList.add(new BaseBean("ccc1"));
                    arrayList.add(new BaseBean("ccc2"));
                    arrayList.add(new BaseBean("ccc3"));
                    arrayList.add(new BaseBean("ccc4"));
                    arrayList.add(new BaseBean("ccc5"));
                    mAdapter.appendList(arrayList);
                    Log.e("TAG", "run: 正常加载更多！！");
                } else {
                    mAdapter.loadMoreError();
                    Log.e("TAG", "run: 加载失败！！！");
                }
                isRun = false;
            }
        };
        mRecycleView.setRefresh(true);
        mAdapter.enableLoadMore(true);
        mAdapter.setTotalCount(10000);
        mRecycleView.postDelayed(refreshAction, DEFAULT_TIME);
    }

    @Override
    public void onLoadMore() {
        if (isRun) {
            Log.e("TAG", "onLoadMore:正在执行，直接返回。。。 ");
            return;
        }
        Log.e("TAG", "onLoadMore: ");
        isRun = true;
        mRecycleView.postDelayed(loadMoreAction, DEFAULT_TIME);
    }

    @Override
    public void onRefresh() {
        mRecycleView.postDelayed(refreshAction, DEFAULT_TIME);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.id_action_gridview:
                mAdapter = new SimpleAdapter();
                mAdapter.setList(mList);
                mRecycleView.setAdapter(mAdapter);
                mAdapter.enableLoadMore(true);
                mAdapter.setTotalCount(10000);
                mRecycleView.setLayoutManager(new GridLayoutManager(this, 3));
                break;
            case R.id.id_action_listview:
                mAdapter = new SimpleAdapter();
                mAdapter.setList(mList);
                mRecycleView.setAdapter(mAdapter);
                mAdapter.enableLoadMore(true);
                mAdapter.setTotalCount(10000);
                mRecycleView.setLayoutManager(new LinearLayoutManager(this));
                break;
            case R.id.id_action_horizontalGridView:
                mAdapter = new SimpleAdapter();
                mAdapter.setList(mList);
                mRecycleView.setAdapter(mAdapter);
                mAdapter.enableLoadMore(true);
                mAdapter.setTotalCount(10000);
                mRecycleView.setLayoutManager(new StaggeredGridLayoutManager(4,
                        StaggeredGridLayoutManager.HORIZONTAL));
                break;

            case R.id.id_action_staggeredgridview:
                mAdapter = new StaggeredAdapter();
                mAdapter.setList(mList);
                mRecycleView.setAdapter(mAdapter);
                mAdapter.enableLoadMore(true);
                mAdapter.setTotalCount(10000);
                mRecycleView.setLayoutManager(new StaggeredGridLayoutManager(2,
                        StaggeredGridLayoutManager.VERTICAL));
//                Intent intent = new Intent(this, StaggeredGridLayoutActivity.class);
//                startActivity(intent);
                break;
        }
        return true;
    }
}
