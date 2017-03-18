package com.boc.lfj.httpdemo.mode.adapter;

/**
 * Created by Administrator on 2017/3/16.
 * 类的适配器
 */

public class Adapter extends Source implements Targetable {
    @Override
    public void method2() {
     System.out.println("this is the targettable method");
    }
}
