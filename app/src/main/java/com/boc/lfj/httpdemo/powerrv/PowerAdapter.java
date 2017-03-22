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

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.boc.lfj.httpdemo.R;
import com.boc.lfj.httpdemo.powerrv.annotation.LoadState;
import com.boc.lfj.httpdemo.powerrv.holder.NewBottomViewHolder;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by Joe on 2016-03-11
 * Email: lovejjfg@gmail.com
 */
public abstract class PowerAdapter<T> extends RecyclerView.Adapter implements AdapterLoader<T>, TouchHelperCallback.ItemDragSwipeCallBack {

    private View loadMore;
    public int loadState;
    public boolean enableLoadMore;
    @Nullable
    private OnItemLongClickListener longClickListener;
    @Nullable
    OnItemClickListener clickListener;
    @Nullable
    OnItemSelectedListener selectedListener;

    public PowerRecyclerView.OnLoadMoreListener getLoadMoreListener() {
        return loadMoreListener;
    }

    public <G extends PowerRecyclerView.OnLoadMoreListener> void setLoadMoreListener(G loadMoreListener) {
        this.loadMoreListener = loadMoreListener;
    }

    private PowerRecyclerView.OnLoadMoreListener loadMoreListener;

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
        enableLoadMore = totalCount > list.size();
        notifyDataSetChanged();
    }

    private int totalCount;

    public PowerAdapter() {
        list = new ArrayList<>();
    }

    public List<T> getList() {
        return list;
    }

    @Override
    public final void setList(List<T> data) {
        if (data == null) {
            return;
        }
        list.clear();
        appendList(data);
        enableLoadMore = totalCount > data.size();

    }

    @Override
    public final void appendList(List<T> data) {
        int positionStart = list.size();
        list.addAll(data);
        int itemCount = list.size() - positionStart;

        if (positionStart == 0) {
            notifyDataSetChanged();
        } else {
            notifyItemRangeInserted(positionStart + 1, itemCount);
        }
    }

    @Override
    public T removeItem(int position) {
        if (position < 0 || position > list.size()) {
            return null;
        }
        T bean = list.remove(position);
        notifyItemRemoved(position);
        return bean;
    }

    @Override
    public void insertItem(int position, T bean) {
        if (position < 0) {
            position = 0;
        }
        if (position > list.size()) {
            position = list.size();
        }
        list.add(position, bean);
        notifyItemInserted(position);
    }

    public final List<T> list;

    @Override
    public final RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (TYPE_BOTTOM == viewType) {
            if (loadMore != null) {
                RecyclerView.ViewHolder holder = onBottomViewHolderCreate(loadMore);
                if (holder == null) {
                    throw new RuntimeException("You must impl onBottomViewHolderCreate() and return your own holder ");
                }
                return holder;
            } else {
                return new NewBottomViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_footer_new, parent, false));
            }
        } else {
            return onViewHolderCreate(parent, viewType);
        }
    }

    @Override
    public RecyclerView.ViewHolder onBottomViewHolderCreate(View loadMore) {
        return null;
    }

    @Override
    public void onBottomViewHolderBind(RecyclerView.ViewHolder holder, @LoadState int loadState) {
        //todoNothing
    }

    @Override
    public abstract RecyclerView.ViewHolder onViewHolderCreate(ViewGroup parent, int viewType);

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, int position) {
        if (getItemViewType(position) == TYPE_BOTTOM) {
            ViewGroup.LayoutParams params = holder.itemView.getLayoutParams();
            if (params instanceof StaggeredGridLayoutManager.LayoutParams) {
                ((StaggeredGridLayoutManager.LayoutParams) params).setFullSpan(true);
            }
            loadState = loadState == STATE_ERROR ? STATE_ERROR : isHasMore() ? STATE_LOADING : STATE_LASTED;
            if (loadMore != null) {
                try {
                    onBottomViewHolderBind(holder, loadState);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else {
                try {
                    ((NewBottomViewHolder) holder).bindDateView(this);
                } catch (Exception e) {
//                    e.printStackTrace();
                }
            }
        } else {
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position1 = holder.getAdapterPosition();
                    if (position1 == -1 || position1 >= list.size()) {
                        return;
                    }
                    performClick(v, position1);
                }
            });

            holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    int position12 = holder.getAdapterPosition();
                    return !(position12 == -1 || position12 >= list.size()) && performLongClick(v, holder.getAdapterPosition());
                }
            });
            if (position == -1 || position >= list.size()) {
                return;
            }
            onViewHolderBind(holder, position);
        }
    }

    public void performClick(View itemView, int position) {
        if (clickListener != null) {
            clickListener.onItemClick(itemView, position);
        }
    }

    @Override
    public boolean performLongClick(View itemView, int position) {
        return longClickListener != null && longClickListener.onItemLongClick(itemView, position);
    }

    @Override
    public abstract void onViewHolderBind(RecyclerView.ViewHolder holder, int position);

    @Override
    public final void isLoadingMore() {
        if (loadState == STATE_LOADING) {
            return;
        }
        loadState = STATE_LOADING;
        notifyItemRangeChanged(getItemRealCount(), 1);
    }

    @Override
    public int getItemCount() {
        return list.isEmpty() ? 0 : enableLoadMore ? list.size() + 1 : list.size();
    }

    @Override
    public int getItemRealCount() {
        return list.size();
    }

    @Override
    public final void setLoadMoreView(@NonNull View view) {
        loadMore = view;
    }

    @Override
    public final int getItemViewType(int position) {
        if (!list.isEmpty() && position < list.size()) {
            return getItemViewTypes(position);
        } else {
            return TYPE_BOTTOM;
        }
    }

    @Override
    public int getItemViewTypes(int position) {
        return 0;
    }


    @Override
    public boolean isHasMore() {
        return getItemCount() < totalCount;
    }

    public final void loadMoreError() {
        loadState = STATE_ERROR;
        notifyItemRangeChanged(getItemRealCount(), 1);
    }

    @Override
    public void enableLoadMore(boolean loadMore) {
        if (enableLoadMore != loadMore) {
            enableLoadMore = loadMore;
            notifyDataSetChanged();
        }
    }


    public void setOnItemClickListener(OnItemClickListener listener) {
        this.clickListener = listener;
    }


    public void setOnItemLongClickListener(OnItemLongClickListener listener) {
        this.longClickListener = listener;
    }

    @Override
    public void onItemDismiss(int position) {
        list.remove(position);
        notifyItemRemoved(position);
    }

    @Override
    public boolean onItemMove(int fromPosition, int toPosition) {
        if (fromPosition == list.size() || toPosition == list.size()) {
            return false;
        }
        if (fromPosition < toPosition) {
            for (int i = fromPosition; i < toPosition; i++) {
                Collections.swap(list, i, i + 1);
            }
        } else {
            for (int i = fromPosition; i > toPosition; i--) {
                Collections.swap(list, i, i - 1);
            }
        }
        notifyItemMoved(fromPosition, toPosition);
        return true;
    }

    void attachToRecyclerView(PowerRecyclerView recycleView) {
        longClickListener = recycleView.getLongClickListener();
        clickListener = recycleView.getClickListener();
        selectedListener = recycleView.getSelectedListener();
        loadMoreListener = recycleView.getLoadMoreClickListener();
    }

    @NonNull
    @Override
    public int[] getMovementFlags(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
        return new int[]{0, 0};
    }
}
