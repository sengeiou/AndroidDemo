package com.boc.lfj.httpdemo.asynctask;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.boc.lfj.httpdemo.R;
import com.boc.lfj.httpdemo.common.ToastUtils;

import static com.kjhxtc.asn1.ua.DSTU4145NamedCurves.params;
import static com.kjhxtc.asn1.x500.style.RFC4519Style.o;

public class AsyncTaskActivity extends AppCompatActivity {
    private ProgressBar progressBar;
    private TextView tv;
    ProgressBarTask task;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_async_task);
        progressBar = (ProgressBar) findViewById(R.id.progressBar2);
        tv = (TextView) findViewById(R.id.textView);
        findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                task = new ProgressBarTask(progressBar);
                task.execute();
            }
        });

    }

    @Override
    protected void onDestroy() {
        if (task != null) {
            task.cancel(true);
            task = null;
        }
        super.onDestroy();
    }

    /**
     * 第一个参数 doInBackground入参
     * 第二个参数 onProgressUpdate入参
     * 第三个参数 doInBackground返回值类型和onPostExecute
     */
    public class ProgressBarTask extends AsyncTask<Void, Integer, Integer> {
        int max;
        int stepProgress;
        int currentprogress;

        ProgressBar mProgressBar;

        public ProgressBarTask(ProgressBar progressBar) {
            this.mProgressBar = progressBar;
        }

        @Override
        protected void onPreExecute() {
            max = mProgressBar.getMax();
            mProgressBar.setProgress(0);
            stepProgress = max / 10 / 10;
        }

        @Override
        protected Integer doInBackground(Void... params) {
            while (currentprogress != 100) {
                currentprogress += stepProgress;
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                publishProgress(currentprogress);
            }
            return currentprogress;
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            mProgressBar.setProgress(values[0]);
            tv.setText(values[0] + "%");
            super.onProgressUpdate(values);
        }

        @Override
        protected void onPostExecute(Integer integer) {//doInBackground完成后调用
            ToastUtils.showLongMsg(AsyncTaskActivity.this, "onPostExecute" + integer);
            super.onPostExecute(integer);
        }
    }
}
