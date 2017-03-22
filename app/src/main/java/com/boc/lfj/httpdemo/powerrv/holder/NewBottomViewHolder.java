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

package com.boc.lfj.httpdemo.powerrv.holder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.boc.lfj.httpdemo.R;
import com.boc.lfj.httpdemo.powerrv.AdapterLoader;
import com.boc.lfj.httpdemo.powerrv.PowerAdapter;
import com.boc.lfj.httpdemo.powerrv.PowerRecyclerView;


/**
 * Created by Joe on 2016-03-11.
 * Email lovejjfg@gmail.com
 */
public class NewBottomViewHolder extends RecyclerView.ViewHolder {
    private final LinearLayout container;
    private final ProgressBar pb;
    private final TextView content;

    public NewBottomViewHolder(View itemView) {

        super(itemView);
        container = (LinearLayout) itemView.findViewById(R.id.footer_container);
        pb = (ProgressBar) itemView.findViewById(R.id.progressbar);
        content = (TextView) itemView.findViewById(R.id.content);
    }

    public void bindDateView(PowerAdapter adapter) {
        final PowerRecyclerView.OnLoadMoreListener loadMoreListener = adapter.getLoadMoreListener();
        switch (adapter.loadState) {
            case AdapterLoader.STATE_LASTED:
                pb.setVisibility(View.GONE);
                container.setVisibility(View.VISIBLE);
                container.setOnClickListener(null);
                content.setText(R.string.text_no_more);

                break;
            case AdapterLoader.STATE_LOADING:
                content.setVisibility(View.VISIBLE);
                content.setText(R.string.text_load_more);
                container.setOnClickListener(null);
                pb.setVisibility(View.VISIBLE);
                if (loadMoreListener != null) {
                    loadMoreListener.onLoadMore();
                }
                break;
            case AdapterLoader.STATE_ERROR:
                container.setVisibility(View.VISIBLE);
                pb.setVisibility(View.GONE);
                content.setText(R.string.text_load_error);
                container.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (loadMoreListener != null) {
                            loadMoreListener.onLoadMore();
                        }
                        content.setText(R.string.text_load_more);
                        pb.setVisibility(View.VISIBLE);
                    }
                });
                break;
            default:
                break;
        }
    }

}
