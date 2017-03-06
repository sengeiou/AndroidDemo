package com.boc.lfj.httpdemo.repeatclick;

import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.boc.lfj.httpdemo.R;
import com.boc.lfj.httpdemo.common.ToastUtils;
import com.boc.lfj.httpdemo.view.ActionSheetDialog;

public class RepeatyActivity extends AppCompatActivity {
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_repeaty);
        button = (Button) findViewById(R.id.button);
        final long[] mHits = new long[5];
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //复制数组，整体向前移动一位
                System.arraycopy(mHits, 1, mHits, 0, mHits.length - 1);
                mHits[mHits.length - 1] = SystemClock.uptimeMillis();
                if (mHits[0] > (SystemClock.uptimeMillis()-2000)) {
                    ToastUtils.showShortMsg(RepeatyActivity.this, "重复点击五次");
                    showDialog();
                }
            }
        });
    }

    public void showDialog() {
        ActionSheetDialog dialog = new ActionSheetDialog(this)
                .builder().setCancelable(false)
                .setCanceledOnTouchOutside(false);
        dialog.addSheetItem("生产环境", ActionSheetDialog.SheetItemColor.Black, new ActionSheetDialog.OnSheelItemClickListener() {
            @Override
            public void onClick(int which) {
                ToastUtils.showLongMsg(RepeatyActivity.this, "生产环境");
            }
        });
        dialog.addSheetItem("测试环境", ActionSheetDialog.SheetItemColor.Black, new ActionSheetDialog.OnSheelItemClickListener() {
            @Override
            public void onClick(int which) {
                ToastUtils.showLongMsg(RepeatyActivity.this, "测试环境");
            }
        });
        dialog.setTxtCancelColor(getResources().getColor(R.color.gray));
        dialog.setTxtCancelBold(false);
        dialog.show();
    }
}
