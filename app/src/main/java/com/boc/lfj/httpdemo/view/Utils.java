package com.boc.lfj.httpdemo.view;

import android.content.Context;
import android.content.res.Resources;
import android.util.TypedValue;

/**
 * Created by Administrator on 2017/3/2.
 */
public class Utils {
    /**
     * dp转px
     *
     * @param resources
     * @param dpVal
     * @return
     */
    public static int dp2px(Resources resources, float dpVal)
    {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,
                dpVal, resources.getDisplayMetrics());
    }

    /**
     * sp转px
     *
     * @param resources
     * @param spVal
     * @return
     */
    public static int sp2px(Resources resources, float spVal)
    {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP,
                spVal, resources.getDisplayMetrics());
    }

    /**
     * px转dp
     *
     * @param resources
     * @param pxVal
     * @return
     */
    public static float px2dp(Resources resources, float pxVal)
    {
        final float scale = resources.getDisplayMetrics().density;
        return (pxVal / scale);
    }

    /**
     * px转sp
     *
     * @param resources
     * @param pxVal
     * @return
     */
    public static float px2sp(Resources resources, float pxVal)
    {
        return (pxVal / resources.getDisplayMetrics().scaledDensity);
    }
}
