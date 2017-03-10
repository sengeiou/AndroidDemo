package com.boc.lfj.httpdemo.special;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.boc.lfj.httpdemo.R;

import java.util.ArrayList;
import java.util.List;


public class CoordinatorLayoutActivity extends AppCompatActivity {
    private ListView mListView;
    private List<String> mList = new ArrayList<String>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coordinator_layout);
//        findViewById(R.id.btn).setOnTouchListener(new View.OnTouchListener() {
//            @Override
//            public boolean onTouch(View v, MotionEvent event) {
//                if (event.getAction()==MotionEvent.ACTION_MOVE){
//                    v.setX(event.getRawX()-v.getWidth()/2);
//                    v.setY(event.getRawY()-v.getHeight()/2);
//                }
//                return true;
//            }
//        });

        initData();
        mListView = (ListView) findViewById(R.id.listview);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(CoordinatorLayoutActivity.this,android.R.layout.simple_list_item_1,mList);
        mListView.setAdapter(adapter);
    }

    private void initData() {
        for (int i=0;i<20;i++){
            mList.add("CoordinatorLayout"+i);
        }
    }
}
