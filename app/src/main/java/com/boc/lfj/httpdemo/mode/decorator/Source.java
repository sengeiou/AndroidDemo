package com.boc.lfj.httpdemo.mode.decorator;

/**
 * Created by Administrator on 2017/3/16.
 */

public class Source implements Sourceable {
    @Override
    public void method() {
        System.out.println("the original method");
    }
}
