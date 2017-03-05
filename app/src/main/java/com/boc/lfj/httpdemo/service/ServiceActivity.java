package com.boc.lfj.httpdemo.service;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Handler;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.boc.lfj.httpdemo.MainActivity;
import com.boc.lfj.httpdemo.R;

import java.util.ArrayList;
import java.util.List;

public class ServiceActivity extends AppCompatActivity implements AdapterView.OnItemClickListener{
    private ListView mListView;
    private List<String> mList = new ArrayList<String>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service);
        initData();
        mListView = (ListView) findViewById(R.id.listview);
        mListView.setOnItemClickListener(this);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(ServiceActivity.this, android.R.layout.simple_list_item_1, mList);
        mListView.setAdapter(adapter);
    }

    private void initData() {
        mList.add("Start Service");
        mList.add("Bind Service");
        mList.add("IntentService");
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        if (position == 0) {
            final Intent intent = new Intent(ServiceActivity.this, UndeadService.class);
            startService(intent);

            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    stopService(intent);
                }
            }, 5000);
        }

        if (position == 1) {
            Intent intent = new Intent(ServiceActivity.this, UndeadService.class);
            UndeadServiceConnection connection = new UndeadServiceConnection();
            bindService(intent, connection,BIND_AUTO_CREATE);
        }

        if (position == 2) {
            MyIntentService.startActionFoo(ServiceActivity.this,"123","222");
            MyIntentService.startActionFoo(ServiceActivity.this,"234","333");
        }
    }

    public class UndeadServiceConnection implements ServiceConnection {

        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            UndeadService.MyBinder mBinder = (UndeadService.MyBinder) service;
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    }
}
