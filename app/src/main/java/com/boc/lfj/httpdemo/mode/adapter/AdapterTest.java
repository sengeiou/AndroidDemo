package com.boc.lfj.httpdemo.mode.adapter;

/**
 * Created by Administrator on 2017/3/16.
 */

public class AdapterTest {

    public static void main(String[] args ){
        Targetable target = new Adapter();
        target.method1();
        target.method2();
    }
}
