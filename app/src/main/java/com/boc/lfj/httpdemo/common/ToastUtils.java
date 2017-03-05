package com.boc.lfj.httpdemo.common;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.boc.lfj.httpdemo.R;


/**
 * ToastUtils 提示工具
 * 非Activity且非Fragment类用到的弹出吐司的公用方法，中银易商的吐司都统一显示在页面上方
 */
public class ToastUtils {
    private static Handler handler = new Handler(Looper.getMainLooper());
    private static Toast toast = null;
    private static Object synObj = new Object();
    private static LayoutInflater inflater;
    private static View mytoast_view;
    private static TextView text;

    public static void showShortMsg(Context context, String message) {
        showMsg(context, message, Toast.LENGTH_LONG);
    }

    public static void showShortMsg(Context context, int message) {
        showMsg(context, context.getString(message), Toast.LENGTH_SHORT);
    }

    public static void showLongMsg(Context context, String message) {
        showMsg(context, message, Toast.LENGTH_LONG);
    }

    public static void showLongMsg(Context context, int message) {
        showMsg(context, context.getString(message), Toast.LENGTH_LONG);
    }

    /**
     * @param act activity
     * @param msg 提示内容
     * @param len toast 显示的时间长度
     */
    public static void showMsg(final Context act, final String msg, int len) {
        inflater = LayoutInflater.from(act);
        // toast 显示位置的偏移量
        // getView(act);
        int x = 0;
        int y = (int) act.getResources().getDimension(R.dimen.toast_offset_y);
        int gravity = Gravity.TOP;
        showMsg(act, gravity, msg, len, x, y);
    }

    /**
     * @param act     当前activity/context
     * @param gravity 控件位置
     * @param x       toast显示在屏幕x轴的坐标
     * @param y       toast显示在屏幕y轴的坐标
     */
    public static void showMsg(final Context act, int gravity,
                               final String msg, int x, int y) {
        inflater = LayoutInflater.from(act);
        // getView(act);
        // toast 显示位置的偏移量
        x = 0;
        y = (int) act.getResources().getDimension(R.dimen.toast_offset_y);
        int len = Toast.LENGTH_SHORT;
        showMsg(act, gravity, msg, len, x, y);
    }

    /**
     * @param act     activity
     * @param gravity 控件位置
     * @param msg     提示内容
     * @param len     toast 显示的时间长度
     */
    public static void showMsg(final Context act, int gravity,
                               final String msg, int len) {
        inflater = LayoutInflater.from(act);
        // getView(act);
        // toast 显示位置的偏移量
        int x = 0;
        int y = (int) act.getResources().getDimension(R.dimen.toast_offset_y);
        showMsg(act, gravity, msg, len, x, y);
    }

    /**
     * toast显示的主方法
     *
     * @param act     activity
     * @param gravity 控件位置
     * @param msg     提示内容
     * @param len     toast 显示的时间长度
     * @param x       x轴偏移量
     * @param y       y轴偏移量
     */
    public static void showMsg(final Context act, final int gravity,
                               final String msg, final int len, final int x, final int y) {
        mytoast_view = inflater.inflate(
                R.layout.view_toast, null);
//		mytoast_view.setPadding(15, 15, 15, 15);
        // ImageView image = (ImageView) mytoast_view
        // .findViewById(R.id.toast_image);
        text = (TextView) mytoast_view.findViewById(R.id.toast_text);
        new Thread(new Runnable() {

            public void run() {
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        synchronized (synObj) {
                            if (toast != null) {
                                // toast.cancel();
                                text.setText(msg);
                                toast.setGravity(gravity, x, y);
                                toast.setDuration(len);
                                toast.setView(mytoast_view);
                            } else {
                                toast = new Toast(act);
                                text.setText(msg);
                                toast.setGravity(gravity, x, y);
                                toast.setDuration(len);
                                toast.setView(mytoast_view);
                            }
                            toast.show();
                        }
                    }
                });
            }
        }).start();
    }

    /**
     * 得到提示toast的view
     *
     * @param act
     */
//	public static void getView(Context act) {
//		if (mytoast_view == null) {
//			mytoast_view = new RelativeLayout(act);
//			mytoast_view.setLayoutParams(new RelativeLayout.LayoutParams(
//					LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
//			mytoast_view.setBackgroundColor(act.getResources().getColor(
//					android.R.color.background_dark));
//			// try {
//			// act.getAssets().open("apj");
//			// } catch (IOException e) {
//			// //
//			// e.printStackTrace();
//			// }
//			mytoast_view.setAlpha((float) 0.7);
//			mytoast_view.setPadding(15, 15, 15, 15);
//			if (text == null) {
//				text = new TextView(act);
//				text.setLayoutParams(new LayoutParams(
//						LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
//				text.setTextColor(act.getResources().getColor(
//						android.R.color.white));
//			}
//			mytoast_view.removeAllViews();
//			mytoast_view.addView(text);
//		}

//	}

}
