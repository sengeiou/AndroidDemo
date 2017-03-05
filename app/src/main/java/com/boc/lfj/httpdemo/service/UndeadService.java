package com.boc.lfj.httpdemo.service;

import android.app.Notification;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Binder;
import android.os.IBinder;

import com.boc.lfj.httpdemo.R;
import com.boc.lfj.httpdemo.common.log.Logger;

/**
 * 如何保证service在后台不被Kill
 * <p>
 * 一、onStartCommand方法，返回START_STICKY
 * START_STICKY 在运行onStartCommand后service进程被kill后，那将保留在开始状态，但是不保留那些传入的intent。
 * 不久后service就会再次尝试重新创建，因为保留在开始状态，在创建     service后将保证调用onstartCommand。
 * 如果没有传递任何开始命令给service，那将获取到null的intent。
 * <p>
 * START_NOT_STICKY 在运行onStartCommand后service进程被kill后，并且没有新的intent传递给它。
 * Service将移出开始状态，并且直到新的明显的方法（startService）调用才重新创建。
 * 因为如果没有传递任何未决定的intent那么service是不会启动，也就是期间onstartCommand不会接收到任何null的intent。
 * <p>
 * START_REDELIVER_INTENT 在运行onStartCommand后service进程被kill后，系统将会再次启动service，
 * 并传入最后一个intent给onstartCommand。直到调用stopSelf(int)才停止传递intent。
 * 如果在被kill后还有未处理好的intent，那被kill后服务还是会自动启动。因此onstartCommand不会接收到任何null的intent。
 * <p>
 * 二、提升service优先级
 * <p>
 * 在AndroidManifest.xml文件中对于intent-filter可以通过android:priority = "1000"这个属性设置最高优先级，1000是最高值，如果数字越小则优先级越低，同时适用于广播。
 * <p>
 * 三、提升service进程优先级
 * <p>
 * Android中的进程是托管的，当系统进程空间紧张的时候，会依照优先级自动进行进程的回收。Android将进程分为6个等级,它们按优先级顺序由高到低依次是:
 * <p>
 * 前台进程( FOREGROUND_APP)
 * 可视进程(VISIBLE_APP )
 * 次要服务进程(SECONDARY_SERVER )
 * 后台进程 (HIDDEN_APP)
 * 内容供应节点(CONTENT_PROVIDER)
 * 空进程(EMPTY_APP)
 * 当service运行在低内存的环境时，将会kill掉一些存在的进程。因此进程的优先级将会很重要，可以使用startForeground 将service放到前台状态。这样在低内存时被kill的几率会低一些。
 * <p>
 * 四、onDestroy方法里重启service
 * <p>
 * service +broadcast  方式，就是当service走ondestory的时候，发送一个自定义的广播，当收到广播的时候，重新启动service；
 * <p>
 * 五、Application加上Persistent属性
 * <p>
 * 六、监听系统广播判断Service状态
 * <p>
 * 通过系统的一些广播，比如：手机重启、界面唤醒、应用状态改变等等监听并捕获到，然后判断我们的Service是否还存活，别忘记加权限啊。
 */
public class UndeadService extends Service {
    private Notification.Builder builder;
    private Notification notification;
    private int i;
    private MyBinder mBinder = new MyBinder();

    public UndeadService() {

    }


    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        buildNotifition();
        startForeground(100, notification);
        return START_STICKY;
    }

    @Override
    public IBinder onBind(Intent intent) {
        buildNotifition();
        startForeground(100, notification);
        return mBinder;
    }


    @Override
    public void onDestroy() {
        Logger.d("stop  service");
        sendBroadcast(new Intent(this,RestartServiceReceiver.class));
        super.onDestroy();
    }

    private void buildNotifition() {
        builder = new Notification.Builder(this);
        Intent intent = new Intent(this, HandleServiceActivity.class);
        notification = builder.setContentIntent(PendingIntent.getActivity(this, 0, intent, 0))
                .setLargeIcon(BitmapFactory.decodeResource(this.getResources(), R.mipmap.ic_launcher))
                .setContentTitle("前台service")
                .setContentText("我是杀不死的")
                .setWhen(System.currentTimeMillis())
                .build();
    }

    public interface BinderInterface {
        int getI();

        void setI(int a);
    }

    public class MyBinder extends Binder implements BinderInterface {

        @Override
        public int getI() {
            return i;
        }

        @Override
        public void setI(int a) {
            i = a;
        }
    }
}
