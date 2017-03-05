package com.boc.lfj.httpdemo.service;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.boc.lfj.httpdemo.common.log.Logger;

public class RestartServiceReceiver extends BroadcastReceiver {
    public RestartServiceReceiver() {
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        Logger.d("restart  service");
        Intent service = new Intent(context, UndeadService.class);
        context.startService(service);
    }
}
