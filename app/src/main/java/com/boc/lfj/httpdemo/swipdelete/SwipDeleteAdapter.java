package com.boc.lfj.httpdemo.swipdelete;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.boc.lfj.httpdemo.R;
import com.boc.lfj.httpdemo.common.ToastUtils;

import java.util.List;

import static com.boc.lfj.httpdemo.R.id.btnDelete;
import static com.boc.lfj.httpdemo.R.id.btnTop;
import static com.boc.lfj.httpdemo.R.id.btnUnRead;

/**
 * Created by Administrator on 2017/3/1.
 */

public class SwipDeleteAdapter extends BaseAdapter {
    private Context mContext;

    public void setmList(List<String> mList) {
        this.mList = mList;
    }

    private List<String> mList;

    public SwipDeleteAdapter(Context context) {
        this.mContext = context;
    }


    @Override
    public int getCount() {
        return mList != null ? mList.size() : 0;
    }

    @Override
    public String getItem(int position) {
        return mList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if (convertView == null) {
            holder = new ViewHolder();
            convertView = LayoutInflater.from(mContext).inflate(R.layout.list_item_swipe, null);
            holder.contentView = (LinearLayout) convertView.findViewById(R.id.swipe_content);
            holder.title = (TextView) convertView.findViewById(R.id.title);
            holder.btnDelete = (Button) convertView.findViewById(btnDelete);
            holder.btnUnRead = (Button) convertView.findViewById(btnUnRead);
            holder.btnTop = (Button) convertView.findViewById(btnTop);
            convertView.setTag(convertView);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        //这句话关掉IOS阻塞式交互效果 并依次打开左滑右滑
        ((SwipeMenuView) convertView).setIos(false).setLeftSwipe(position % 2 == 0 ? true : false);

        holder.title.setText(mList.get(position) + (position % 2 == 0 ? "我只能右滑动" : "我只能左滑动"));

        //隐藏控件
        holder.btnUnRead.setVisibility(position % 3 == 0 ? View.GONE : View.VISIBLE);

        holder.btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != mOnSwipeListener) {
                    //如果删除时，不使用mAdapter.notifyItemRemoved(pos)，则删除没有动画效果，
                    //且如果想让侧滑菜单同时关闭，需要同时调用 ((CstSwipeDelMenu) holder.itemView).quickClose();
                    //((CstSwipeDelMenu) holder.itemView).quickClose();
                    mOnSwipeListener.onDel(position);
                }
            }
        });
        //注意事项，设置item点击，不能对整个holder.itemView设置咯，只能对第一个子View，即原来的content设置，这算是局限性吧。
        holder.contentView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ToastUtils.showLongMsg(mContext, mList.get(position));
                Log.d("TAG", "onClick() called with: v = [" + v + "]");
            }
        });
        //置顶：
        holder.btnTop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != mOnSwipeListener) {
                    mOnSwipeListener.onTop(position);
                }

            }
        });
        return convertView;
    }

    /**
     * 和Activity通信的接口
     */
    public interface onSwipeListener {
        void onDel(int pos);

        void onTop(int pos);
    }

    private onSwipeListener mOnSwipeListener;

    public void setOnDelListener(onSwipeListener mOnDelListener) {
        this.mOnSwipeListener = mOnDelListener;
    }

    public final class ViewHolder {
        LinearLayout contentView;
        TextView title;
        Button btnDelete;
        Button btnUnRead;
        Button btnTop;
    }
}
