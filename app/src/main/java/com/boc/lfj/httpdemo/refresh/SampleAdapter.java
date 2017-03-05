package com.boc.lfj.httpdemo.refresh;

import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TextView;

import com.boc.lfj.httpdemo.R;

import java.util.List;

public class SampleAdapter extends RecyclerView.Adapter<ViewHolder> {
    private List<String> list;

    private static final int TYPE_ITEM = 0;
    private static final int TYPE_FOOTER = 1;

    public static final int STATE_INVISIBLE = 0;
    public static final int STATE_LOADING = 1;
    public static final int STATE_NO_MORE = 2;
    public static final int STATE_NO_DATA = 3;

    protected int state = STATE_LOADING;
    protected FooterView footerView;

    public List<String> getList() {
        return list;
    }

    public SampleAdapter(List<String> list) {
        this.list = list;


    }

    // RecyclerView的count设置为数据总条数+ 1（footerView）
    @Override
    public int getItemCount() {
        return list.size() + 1;
    }

    @Override
    public int getItemViewType(int position) {
        // 最后一个item设置为footerView
        if (position + 1 == getItemCount()) {
            return TYPE_FOOTER;
        } else {
            return TYPE_ITEM;
        }
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        if (holder instanceof ItemViewHolder) {
            ((ItemViewHolder) holder).textView.setText(String.valueOf(list
                    .get(position)));
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == TYPE_ITEM) {
            View view = LayoutInflater.from(parent.getContext()).inflate(
                    R.layout.list_item_text, null);
            view.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT,
                    LayoutParams.WRAP_CONTENT));
            return new ItemViewHolder(view);
        }
        // type == TYPE_FOOTER 返回footerView
        else if (viewType == TYPE_FOOTER) {
//            View view = LayoutInflater.from(parent.getContext()).inflate(
//                    R.layout.item_foot, null);
            footerView = new FooterView(parent.getContext());
            footerView.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT,
                    LayoutParams.WRAP_CONTENT));
            footerView.setVisibility(View.GONE);
            return new FooterViewHolder(footerView);
        }

        return null;
    }

    public void setState(int state) {//设置不同的状态然后更新footView
        this.state = state;
        refreshFooterView();
    }

    private void refreshFooterView() {
        if (footerView == null) {
            return;
        }
        switch (state) {

            case STATE_INVISIBLE:
                footerView.setVisibility(View.GONE);
                break;
            case STATE_LOADING:
                footerView.setLoadingState();
                break;
            case STATE_NO_MORE:
                footerView.setNoMoreState();
                break;
            case STATE_NO_DATA:
                footerView.setNoDataState();
                break;
        }
    }

    class FooterViewHolder extends ViewHolder {

        public FooterViewHolder(View view) {
            super(view);
        }

    }

    class ItemViewHolder extends ViewHolder {
        TextView textView;

        public ItemViewHolder(View view) {
            super(view);
            textView = (TextView) view.findViewById(R.id.text);
        }
    }
}