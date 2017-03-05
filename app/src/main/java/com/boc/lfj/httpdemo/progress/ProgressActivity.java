package com.boc.lfj.httpdemo.progress;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ProgressBar;

import com.boc.lfj.httpdemo.R;

public class ProgressActivity extends AppCompatActivity {
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_progress);
        progressBar = (ProgressBar) findViewById(R.id.progressBar2);
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                int max = progressBar.getMax();
                int stepProgress = max / 10 / 10;
                while (max != progressBar.getProgress()) {
                    int currentprogress = progressBar.getProgress();
                    progressBar.setProgress(currentprogress + stepProgress);
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        thread.start();
    }
}
