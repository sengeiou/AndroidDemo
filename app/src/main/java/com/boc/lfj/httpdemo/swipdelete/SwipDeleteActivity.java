package com.boc.lfj.httpdemo.swipdelete;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.Toast;

import com.boc.lfj.httpdemo.R;

import java.util.ArrayList;
import java.util.List;

public class SwipDeleteActivity extends AppCompatActivity {
    private SwipDeleteAdapter mDataAdapter = null;
    private List<String> mList=new ArrayList<>();
    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_swip_delete);
        initData();
        listView = (ListView) findViewById(R.id.listview);
        mDataAdapter = new SwipDeleteAdapter(this);
        mDataAdapter.setmList(mList);
        listView.setAdapter(mDataAdapter);
        mDataAdapter.setOnDelListener(new SwipDeleteAdapter.onSwipeListener() {
            @Override
            public void onDel(int pos) {
                Toast.makeText(SwipDeleteActivity.this, "删除:" + pos, Toast.LENGTH_SHORT).show();

                //RecyclerView关于notifyItemRemoved的那点小事 参考：http://blog.csdn.net/jdsjlzx/article/details/52131528
//                mDataAdapter.getDataList().remove(pos);
//                mDataAdapter.notifyItemRemoved(pos);//推荐用这个
//
//                if(pos != (mDataAdapter.getDataList().size())){ // 如果移除的是最后一个，忽略
//                    mDataAdapter.notifyItemRangeChanged(pos, mDataAdapter.getDataList().size() - pos);
//                }
                //且如果想让侧滑菜单同时关闭，需要同时调用 ((CstSwipeDelMenu) holder.itemView).quickClose();
            }

            @Override
            public void onTop(int pos) {//置顶功能有bug，后续解决
//                TLog.error("onTop pos = " + pos);
//                ItemModel itemModel = mDataAdapter.getDataList().get(pos);
//
//                mDataAdapter.getDataList().remove(pos);
//                mDataAdapter.notifyItemRemoved(pos);
//                mDataAdapter.getDataList().add(0, itemModel);
//                mDataAdapter.notifyItemInserted(0);
//
//
//                if(pos != (mDataAdapter.getDataList().size())){ // 如果移除的是最后一个，忽略
//                    mDataAdapter.notifyItemRangeChanged(0, mDataAdapter.getDataList().size() - 1,"jdsjlzx");
//                }
//
//                mRecyclerView.scrollToPosition(0);

            }
        });
    }

    private void initData() {
        for (int i = 0; i < 20; i++) {
            mList.add("data"+i);
        }
    }
}
