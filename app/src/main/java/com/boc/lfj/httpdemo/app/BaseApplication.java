package com.boc.lfj.httpdemo.app;

import android.app.Application;

import com.boc.lfj.httpdemo.crash.CrashHandler;

/**
 * 类名： BaseApplication
 * 功能说明：
 * 作者： lfj
 * 创建时间： 2017/2/20
 * 修改内容：
 * 修改人： lfj
 * 修改时间：2017/2/20
 */
public class BaseApplication extends Application {
    private static BaseApplication application;


    @Override
    public void onCreate() {
        super.onCreate();
        application = this;
        CrashHandler crashHandler = CrashHandler.getInstance();
        crashHandler.init(getApplicationContext());
    }

    public static BaseApplication getInstance() {
        if (null == application) {
            application = new BaseApplication();
        }
        return application;
    }
    
}
