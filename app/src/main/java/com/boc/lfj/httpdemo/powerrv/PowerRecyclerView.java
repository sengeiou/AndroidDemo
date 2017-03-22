/*
 * Copyright (c) 2016.  Joe
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.boc.lfj.httpdemo.powerrv;

import android.content.Context;
import android.support.annotation.ColorInt;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.boc.lfj.httpdemo.R;


/**
 * Created by Joe on 2016-03-11.
 * Email lovejjfg@gmail.com
 */
public class PowerRecyclerView extends FrameLayout implements SwipeRefreshLayout.OnRefreshListener {
    private SwipeRefreshLayout mRefreshLayout;
    private RecyclerView mRecyclerView;

    public PowerRecyclerView(Context context) {
        this(context, null);
    }

    public PowerRecyclerView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public PowerRecyclerView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }


    private void init(Context context) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.layout_recycler, this, false);
        mRecyclerView = (RecyclerView) inflate.findViewById(R.id.power_recycle_view);
        mRefreshLayout = (SwipeRefreshLayout) inflate.findViewById(R.id.container);
        mRefreshLayout.setOnRefreshListener(this);
        mRecyclerView.setItemAnimator(new DefaultAnimator());
        mRecyclerView.addOnScrollListener(new LoadMoreScrollListener(mRecyclerView));
        addView(inflate, new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
    }

    /**
     * Set the colors used in the progress animation. The first
     * color will also be the color of the bar that grows in response to a user
     * swipe gesture.
     */

    public void setColorSchemeColors(@ColorInt int... colors) {
        mRefreshLayout.setColorSchemeColors(colors);
    }

    public void setLayoutManager(final RecyclerView.LayoutManager manager, boolean showScrollBar) {
        if (manager instanceof LinearLayoutManager) {
            int orientation = ((LinearLayoutManager) manager).getOrientation();
            if (LinearLayoutManager.HORIZONTAL == orientation) {
                mRecyclerView.setHorizontalScrollBarEnabled(showScrollBar);
                setPullRefreshEnable(false);
            } else {
                mRecyclerView.setVerticalScrollBarEnabled(showScrollBar);
            }
        }
        if (manager instanceof GridLayoutManager) {
            ((GridLayoutManager) manager).setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
                @Override
                public int getSpanSize(int position) {
                    return initSpanSize(position, (GridLayoutManager) manager);
                }
            });
        }
        mRecyclerView.setLayoutManager(manager);
    }

    private int initSpanSize(int position, GridLayoutManager manager) {
        int itemViewType = adapter.getItemViewType(position);
        if (AdapterLoader.TYPE_BOTTOM == itemViewType) {
            return manager.getSpanCount();
        }
        return (spanSizeCallBack != null ? spanSizeCallBack.getSpanSize(position) : 0) == 0 ? 1 : spanSizeCallBack.getSpanSize(position);
    }

    /**
     * show horizontalScrollBar default state is false,show verticalScrollBar default state is true.
     *
     * @param manager LayoutManager
     */
    public void setLayoutManager(final RecyclerView.LayoutManager manager) {
        if (manager instanceof LinearLayoutManager) {
            int orientation = ((LinearLayoutManager) manager).getOrientation();
            if (LinearLayoutManager.HORIZONTAL == orientation) {
                mRecyclerView.setHorizontalScrollBarEnabled(false);
                setPullRefreshEnable(false);
            } else {
                mRecyclerView.setVerticalScrollBarEnabled(true);
            }
        }
        if (manager instanceof GridLayoutManager) {
            ((GridLayoutManager) manager).setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
                @Override
                public int getSpanSize(int position) {
                    return initSpanSize(position, (GridLayoutManager) manager);
                }
            });
        }
        mRecyclerView.setLayoutManager(manager);
    }

    @SuppressWarnings("unused")
    public void setItemAnimator(RecyclerView.ItemAnimator animator) {
        mRecyclerView.setItemAnimator(animator);

    }

    /**
     * Enable to pullRefresh
     *
     * @param enable whether can pull refresh or not...
     */
    @SuppressWarnings("unused")
    public void setPullRefreshEnable(boolean enable) {
        mRefreshLayout.setEnabled(enable);
    }


    private PowerAdapter adapter;

    public void setAdapter(PowerAdapter adapter) {
        this.adapter = adapter;
        mRecyclerView.setAdapter(adapter);
        adapter.attachToRecyclerView(this);
    }

    public void setRefresh(final boolean refresh) {
        mRecyclerView.post(new Runnable() {
            @Override
            public void run() {
                mRefreshLayout.setRefreshing(refresh);
            }
        });
    }

    @Override
    public void onRefresh() {
        if (null != listener) {
            listener.onRefresh();
        }
    }

    private OnRefreshLoadMoreListener listener;

    public void setOnRefreshListener(OnRefreshLoadMoreListener listener) {
        this.listener = listener;
        if (adapter != null) {
            adapter.setLoadMoreListener(listener);
        }
    }

    public RecyclerView getRecycle() {
        return mRecyclerView;
    }

    public interface OnLoadMoreListener {
        void onLoadMore();
    }

    public interface OnRefreshLoadMoreListener extends OnLoadMoreListener {
        void onRefresh();
    }


    public void addOnScrollListener(RecyclerView.OnScrollListener listener) {
        mRecyclerView.addOnScrollListener(listener);
    }

    /**
     * you should call this method when you want to specified SpanSize.
     */
    public void setSpanSizeCallBack(@NonNull SpanSizeCallBack spanSizeCallBack) {
        this.spanSizeCallBack = spanSizeCallBack;
    }

    @Nullable
    private SpanSizeCallBack spanSizeCallBack;

    public interface SpanSizeCallBack {
        int getSpanSize(int position);
    }

    public AdapterLoader.OnItemSelectedListener getSelectedListener() {
        return selectedListener;
    }


    public AdapterLoader.OnItemClickListener getClickListener() {
        return clickListener;
    }


    public AdapterLoader.OnItemLongClickListener getLongClickListener() {
        return longClickListener;
    }
    public OnLoadMoreListener getLoadMoreClickListener() {
        return listener;
    }


    public void setOnItemSelectListener(AdapterLoader.OnItemSelectedListener listener) {
        this.selectedListener = listener;
        if (adapter instanceof SelectPowerAdapter) {
            ((SelectPowerAdapter) adapter).setOnItemSelectListener(selectedListener);
        }
    }

    private AdapterLoader.OnItemSelectedListener selectedListener;


    public void setOnItemClickListener(AdapterLoader.OnItemClickListener listener) {
        this.clickListener = listener;
        if (adapter != null) {
            adapter.setOnItemClickListener(clickListener);
        }
    }


    public void setOnItemLongClickListener(AdapterLoader.OnItemLongClickListener listener) {
        this.longClickListener = listener;
        if (adapter != null) {
            adapter.setOnItemLongClickListener(longClickListener);
        }
    }

    private AdapterLoader.OnItemClickListener clickListener;


    private AdapterLoader.OnItemLongClickListener longClickListener;


}
