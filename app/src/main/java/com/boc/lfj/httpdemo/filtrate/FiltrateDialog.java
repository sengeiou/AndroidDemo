package com.boc.lfj.httpdemo.filtrate;

import android.app.Dialog;
import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.text.TextPaint;
import android.util.Log;
import android.view.Display;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.boc.lfj.httpdemo.R;
import com.boc.lfj.httpdemo.powerrv.AdapterLoader;
import com.boc.lfj.httpdemo.powerrv.PowerAdapter;
import com.boc.lfj.httpdemo.powerrv.PowerRecyclerView;
import com.boc.lfj.httpdemo.powerrv.SelectPowerAdapter;
import com.boc.lfj.httpdemo.powerrv.SpacesItemDecoration;
import com.boc.lfj.httpdemo.powerrv.annotation.SelectMode;
import com.boc.lfj.httpdemo.powerrv.demo.SelectRecycleAdapter;
import com.boc.lfj.httpdemo.powerrv.demo.model.TestBean;
import com.boc.lfj.httpdemo.powerrv.model.ISelect;
import com.boc.lfj.httpdemo.view.ActionSheetDialog;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/3/22.
 */

public class FiltrateDialog {
    private Context context;
    private Dialog dialog;
    private TextView txt_title;
    private TextView txt_cancel;
    PowerRecyclerView mRecycleView;
    private SelectPowerAdapter<TestBean> adapter;
    private boolean showTitle = false;
    private Display display;
    private Toast toast;
    private List<TestBean> list;
    private SpacesItemDecoration decor;

    public FiltrateDialog(Context context){
        this.context = context;
        WindowManager windowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        display = windowManager.getDefaultDisplay();
    }

    public FiltrateDialog builder() {
        //获取dialog布局
        View view = LayoutInflater.from(context).inflate(R.layout.view_filtrate, null);
        toast = Toast.makeText(context, "", Toast.LENGTH_SHORT);

        //设置dialog最小宽度为屏幕宽度
        view.setMinimumWidth(display.getWidth());

        //获取自定义dialog布局中的控件
        mRecycleView = (PowerRecyclerView) view.findViewById(R.id.recycle_view);
        GridLayoutManager manager = new GridLayoutManager(context, 3);
        mRecycleView.setLayoutManager(manager);
        adapter = new SelectRecycleAdapter();
        adapter.setSelectedMode(ISelect.SINGLE_MODE);
        mRecycleView.setOnItemSelectListener(new AdapterLoader.OnItemSelectedListener() {
            @Override
            public void onItemSelected(View view, int position, boolean isSelected) {
                Log.e("TAG", "onItemSelected: " + position + "::" + isSelected);
                if (isSelected) {
                    toast.setText(position + "::" + true);
                    toast.show();
                }
            }

            @Override
            public void onNothingSelected() {
                Log.e("TAG", "onNothingSelected: ");
            }
        });
        mRecycleView.setPullRefreshEnable(false);
//        firstRecycleView.setSpanSizeCallBack(new PowerRecyclerView.SpanSizeCallBack() {
//            @Override
//            public int getSpanSize(int position) {
//                return 1;
//            }
//        });


//        sLayout_content = (ScrollView) view.findViewById(R.id.sLayout_content);
//        lLayout_content = (LinearLayout) view.findViewById(R.id.lLayout_content);
        txt_title = (TextView) view.findViewById(R.id.txt_title);
        txt_cancel = (TextView) view.findViewById(R.id.txt_cancel);
        txt_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
//        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
//        firstRecycleView.setLayoutParams(params);
        mRecycleView.setAdapter(adapter);
        decor = new SpacesItemDecoration(30, 3, true);
        mRecycleView.getRecycle().addItemDecoration(decor);
        //定义dialog布局和参数
        dialog = new Dialog(context, R.style.ActionSheetDialogStyle);
        dialog.setContentView(view);
        Window dialogWindow = dialog.getWindow();
        dialogWindow.setGravity(Gravity.START | Gravity.BOTTOM);
        WindowManager.LayoutParams lp = dialogWindow.getAttributes();
        lp.x = 0;
        lp.y = 0;
        dialogWindow.setAttributes(lp);
        return this;
    }

    public void setSelectMode(@SelectMode int mode){
        adapter.setSelectedMode(mode);
        adapter.updateSelectMode(true);
    }

    public void setData(List<TestBean> data){
        list = new ArrayList<>();
        list.addAll(data);
        adapter.setList(list);
    }

    public FiltrateDialog setTitle(String title) {
        showTitle = true;
        txt_title.setVisibility(View.VISIBLE);
        txt_title.setText(title);
        return this;
    }

    public FiltrateDialog setTxtCancelColor(int color) {
        txt_cancel.setTextColor(color);
        return this;
    }

    public FiltrateDialog setTxtCancelBold(boolean isBold) {
        TextPaint tp = txt_cancel.getPaint();
        tp.setFakeBoldText(isBold);
        return this;
    }

    public FiltrateDialog setCancelable(boolean cancel) {

        dialog.setCancelable(cancel);
        return this;
    }

    public FiltrateDialog setCanceledOnTouchOutside(boolean cancel) {
        dialog.setCanceledOnTouchOutside(cancel);
        return this;
    }

    public void show() {
        dialog.show();
    }
}
