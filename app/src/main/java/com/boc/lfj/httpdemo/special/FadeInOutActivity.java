package com.boc.lfj.httpdemo.special;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AbsListView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.boc.lfj.httpdemo.R;
import com.boc.lfj.httpdemo.common.log.Logger;


import java.util.ArrayList;
import java.util.List;


public class FadeInOutActivity extends AppCompatActivity {
    private ListView listView;
    private LinearLayout layout;
    private List<String> mList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fade_in_out);
        initData();
        listView = (ListView) findViewById(R.id.listview);
        View view = LayoutInflater.from(FadeInOutActivity.this).inflate(R.layout.item_header,null);
        layout = (LinearLayout) findViewById(R.id.layout);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(FadeInOutActivity.this, android.R.layout.simple_list_item_1, mList);
        listView.addHeaderView(view);
        listView.setAdapter(adapter);
        initListener();
    }

    private void initData() {
        for (int i = 0; i < 20; i++) {
            mList.add("Fade in out" + i);
        }
    }

    private void initListener() {


        listView.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {

            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
                Logger.d("firstVisibleItem=" + firstVisibleItem + "visibleItemCount=" + visibleItemCount + "totalItemCount=" + totalItemCount);
                Logger.d(getScroolY() + "top=" + gettop());
                if (firstVisibleItem > 0) {
                    layout.getBackground().mutate().setAlpha(255);
                } else {
                    layout.getBackground().mutate().setAlpha((int) (getAlphaFloat(Math.abs(getScroolY())) * 255));
                }
                Logger.d(layout.getAlpha()+"");
            }
        });

    }

    /**
     * 获取渐变透明值
     *
     * @param dis
     * @return
     */
    public float getAlphaFloat(int dis) {

        int step = 450;
        if (dis == 0) {
            return 0.0f;
        }

        if (dis < step) {
            return (float) (dis * (1.0 / step));
        } else {
            return 1.0f;
        }

    }

    /**
     * 获取上滑的距离
     *
     * @return distance
     */
    public int getScroolY() {
        View c = listView.getChildAt(0);
        if (null == c) {
            return 0;
        }
        int firstVisiblePosition = listView.getFirstVisiblePosition();
        int top = c.getTop();

        /**
         * 声明一下，这里测试得到的top值始终是listview条目中显示的第一条距离顶部的距离，
         * 而这个在坐标中的表示是一个负数，所以需要对其取一个绝对值
         */
        return firstVisiblePosition * c.getHeight() + Math.abs(top);

    }

    /**
     * 获取首条距离顶部的高度
     *
     * @return distance top
     */
    private int gettop() {
        View c = listView.getChildAt(0);
        if (null == c) {
            return 0;
        }
        return c.getTop();
    }
}
