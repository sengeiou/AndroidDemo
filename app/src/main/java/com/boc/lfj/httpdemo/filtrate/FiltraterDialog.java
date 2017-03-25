package com.boc.lfj.httpdemo.filtrate;

import android.app.Dialog;
import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextPaint;
import android.util.Log;
import android.view.Display;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;

import com.boc.lfj.httpdemo.R;
import com.boc.lfj.httpdemo.powerrv.AdapterLoader;
import com.boc.lfj.httpdemo.powerrv.SelectPowerAdapter;
import com.boc.lfj.httpdemo.powerrv.SpacesItemDecoration;
import com.boc.lfj.httpdemo.powerrv.annotation.SelectMode;
import com.boc.lfj.httpdemo.powerrv.demo.SelectRecycleAdapter;
import com.boc.lfj.httpdemo.powerrv.demo.model.TestBean;
import com.boc.lfj.httpdemo.powerrv.model.ISelect;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/3/22.
 */

public class FiltraterDialog {
    private Context context;
    private Dialog dialog;
    private TextView txt_title;
    private TextView txt_cancel;
    private TextView commitBtn;
    RecyclerView firstRecycleView;
    RecyclerView secRecycleView;
    private SelectPowerAdapter<TestBean> firstAdapter;
    private SelectPowerAdapter<TestBean> secAdapter;
    private boolean showTitle = false;
    private Display display;
    private Toast toast;
    private List<TestBean> mFirstList;
    private List<TestBean> mSecList;
    private SpacesItemDecoration decor;

    public FiltraterDialog(Context context){
        this.context = context;
        WindowManager windowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        display = windowManager.getDefaultDisplay();
    }

    public FiltraterDialog builder() {
        //获取dialog布局
        View view = LayoutInflater.from(context).inflate(R.layout.view_filtrater, null);
        toast = Toast.makeText(context, "", Toast.LENGTH_SHORT);
        commitBtn = (TextView) view.findViewById(R.id.btn_commit);
        //设置dialog最小宽度为屏幕宽度
        view.setMinimumWidth(display.getWidth());
        GridLayoutManager firstManager = new GridLayoutManager(context, 3);
        GridLayoutManager secManager = new GridLayoutManager(context, 3);
        decor = new SpacesItemDecoration(30, 3, true);

        //获取自定义dialog布局中的控件
        secRecycleView= (RecyclerView) view.findViewById(R.id.recycle_sec);
        secRecycleView.setLayoutManager(secManager);
        secAdapter = new SelectRecycleAdapter();
        secAdapter.setSelectedMode(ISelect.SINGLE_MODE);
        secRecycleView.setAdapter(secAdapter);
        secRecycleView.addItemDecoration(decor);

        firstRecycleView = (RecyclerView) view.findViewById(R.id.recycle_first);
        firstRecycleView.setLayoutManager(firstManager);
        firstAdapter = new SelectRecycleAdapter();
        firstAdapter.setSelectedMode(ISelect.SINGLE_MODE);
        firstRecycleView.setAdapter(firstAdapter);
        firstRecycleView.addItemDecoration(decor);


        txt_title = (TextView) view.findViewById(R.id.txt_title);
        txt_cancel = (TextView) view.findViewById(R.id.txt_cancel);
        txt_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        //定义dialog布局和参数
        dialog = new Dialog(context, R.style.ActionSheetDialogStyle);
        dialog.setContentView(view);
        firstAdapter.setOnItemSelectListener(new AdapterLoader.OnItemSelectedListener() {
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

            }
        });
        secAdapter.setOnItemSelectListener(new AdapterLoader.OnItemSelectedListener() {
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

            }
        });
        Window dialogWindow = dialog.getWindow();
        dialogWindow.setGravity(Gravity.START | Gravity.BOTTOM);
        WindowManager.LayoutParams lp = dialogWindow.getAttributes();
        lp.x = 0;
        lp.y = 0;
        dialogWindow.setAttributes(lp);
        commitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (clickListener!=null){
                    List<TestBean> firstSelectList = new ArrayList<TestBean>();
                    for (int i = 0; i< mFirstList.size(); i++){
                        if (mFirstList.get(i).isSelected()){
                            firstSelectList.add(mFirstList.get(i));
                        }
                    }
                    List<TestBean> secSelectList = new ArrayList<TestBean>();
                    for (int i = 0; i< mSecList.size(); i++){
                        if (mSecList.get(i).isSelected()){
                            secSelectList.add(mSecList.get(i));
                        }
                    }

                    clickListener.onClick(firstSelectList,secSelectList);
                }
            }
        });
        return this;
    }

    public void setFirstMode(@SelectMode int mode){
        firstAdapter.setSelectedMode(mode);
        firstAdapter.updateSelectMode(true);
    }

    public void setSecMode(@SelectMode int mode){
        secAdapter.setSelectedMode(mode);
        secAdapter.updateSelectMode(true);
    }

    public void setData(List<TestBean> firstList,List<TestBean> secList){
        this.mFirstList = new ArrayList<>();
        this.mFirstList.addAll(firstList);
        firstAdapter.setList(firstList);

        this.mSecList = new ArrayList<>();
        this.mSecList.addAll(secList);
        secAdapter.setList(mSecList);
    }

    public FiltraterDialog setTitle(String title) {
        showTitle = true;
        txt_title.setVisibility(View.VISIBLE);
        txt_title.setText(title);
        return this;
    }

    public FiltraterDialog setTxtCancelColor(int color) {
        txt_cancel.setTextColor(color);
        return this;
    }

    public FiltraterDialog setTxtCancelBold(boolean isBold) {
        TextPaint tp = txt_cancel.getPaint();
        tp.setFakeBoldText(isBold);
        return this;
    }

    public FiltraterDialog setCancelable(boolean cancel) {

        dialog.setCancelable(cancel);
        return this;
    }

    public FiltraterDialog setCanceledOnTouchOutside(boolean cancel) {
        dialog.setCanceledOnTouchOutside(cancel);
        return this;
    }

    public void show() {
        dialog.show();
    }

    public void setClickListener(CommitClickListener clickListener) {
        this.clickListener = clickListener;
    }

    CommitClickListener clickListener;

    public interface CommitClickListener {
        void onClick(List<TestBean> firstList,List<TestBean> secList);
    }
}
