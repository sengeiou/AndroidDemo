package com.boc.lfj.httpdemo.powerrv.demo.simple;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.boc.lfj.httpdemo.R;
import com.boc.lfj.httpdemo.powerrv.PowerAdapter;
import com.transitionseverywhere.ChangeText;
import com.transitionseverywhere.TransitionManager;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by Administrator on 2017/3/21.
 */

public class StaggeredAdapter extends PowerAdapter<BaseBean> {
    private static List<Integer> mHeights;

    public StaggeredAdapter() {
        super();
        mHeights = new ArrayList<Integer>();
        for (int i = 0; i < 50; i++) {
            mHeights.add((int) (100 + Math.random() * 300));
        }
    }

    @Override
    public RecyclerView.ViewHolder onViewHolderCreate(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_staggered_home, parent, false);
        return new MyViewHolder(view);
    }
    @NonNull
    @Override
    public int[] getMovementFlags(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
        return new int[]{ItemTouchHelper.UP | ItemTouchHelper.DOWN | ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT, ItemTouchHelper.ACTION_STATE_IDLE};
    }
    @Override
    public void onViewHolderBind(RecyclerView.ViewHolder holder, int position) {

        final BaseBean testBean = list.get(position);
        ((MyViewHolder) holder).bindDateView(testBean);
    }

    private static class MyViewHolder extends RecyclerView.ViewHolder {

        private final TextView mTv;

        MyViewHolder(View itemView) {
            super(itemView);
            mTv = (TextView) itemView.findViewById(R.id.id_num);
        }

        void bindDateView(BaseBean s) {
            TransitionManager.beginDelayedTransition((ViewGroup) itemView, new ChangeText().setChangeBehavior(ChangeText.CHANGE_BEHAVIOR_KEEP));
            ViewGroup.LayoutParams lp = mTv.getLayoutParams();
            Random random = new Random();
            lp.height = mHeights.get(random.nextInt(40));

            mTv.setLayoutParams(lp);
            mTv.setText(s.getName());
        }
    }

}
