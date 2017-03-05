package com.boc.lfj.httpdemo.refresh;

import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.boc.lfj.httpdemo.R;

/**
 * Created by Administrator on 2017/2/23.
 */

public class FooterView extends LinearLayout {
    protected String loadingText ="加载中",noMoreText="已加载全部",noDataText="暂无内容";
    private View mLayout;
    private  TextView textView;
    private  ProgressBar progressBar;
    public FooterView(Context context) {
        super(context);
        init(context);
    }

    private void init(Context context) {
        mLayout = LayoutInflater.from(context).inflate(R.layout.item_foot,null);
        addView(mLayout);
        progressBar = (ProgressBar) mLayout.findViewById(R.id.progressBar);
        textView = (TextView) mLayout.findViewById(R.id.textView);
    }

    public void setText(CharSequence s){
        textView.setText(s);
    }

    public void setNoDataState(){
        this.setVisibility(VISIBLE);
        progressBar.setVisibility(GONE);
        textView.setVisibility(VISIBLE);
        textView.setText(noDataText);
    }

    public void setNoMoreState(){
        this.setVisibility(VISIBLE);
        progressBar.setVisibility(GONE);
        textView.setVisibility(VISIBLE);
        textView.setText(noMoreText);
    }

    public void setLoadingState(){
        this.setVisibility(VISIBLE);
        progressBar.setVisibility(VISIBLE);
        textView.setVisibility(VISIBLE);
        textView.setText(loadingText);
    }
}
