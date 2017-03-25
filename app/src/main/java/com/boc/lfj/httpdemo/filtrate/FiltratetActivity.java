package com.boc.lfj.httpdemo.filtrate;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.boc.lfj.httpdemo.R;
import com.boc.lfj.httpdemo.common.log.Logger;
import com.boc.lfj.httpdemo.powerrv.demo.model.TestBean;
import com.boc.lfj.httpdemo.powerrv.model.ISelect;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class FiltratetActivity extends AppCompatActivity {
    @Bind(R.id.btn_filtrate)
    Button filtrateBtn;
    List<TestBean> mFirstList;
    List<TestBean> mSecList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filtratet);
        ButterKnife.bind(this);
        mFirstList = new ArrayList<>();
        mSecList = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            mFirstList.add(new TestBean("第一" + i));
            mSecList.add(new TestBean("第二" + i));
        }
    }

    @OnClick({R.id.btn_filtrate})
    void click(View v) {
        switch (v.getId()) {
            case R.id.btn_filtrate:
                FiltraterDialog dialog = new FiltraterDialog(FiltratetActivity.this).builder().setCancelable(true);
                dialog.setData(mFirstList,mSecList);
                dialog.setFirstMode(ISelect.MULTIPLE_MODE);
                dialog.setSecMode(ISelect.MULTIPLE_MODE);
                dialog.setClickListener(new FiltraterDialog.CommitClickListener() {
                    @Override
                    public void onClick(List<TestBean> firstList,List<TestBean> secList) {
                        Logger.d("选择");
                    }
                });
                dialog.show();
                break;
        }
    }

}
