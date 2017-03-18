package com.boc.lfj.httpdemo.mode.factory;

/**
 * Created by Administrator on 2017/3/15.
 */

public class SmsSender implements Sender {
    @Override
    public void Send() {
        System.out.println("smssender");
    }
}
