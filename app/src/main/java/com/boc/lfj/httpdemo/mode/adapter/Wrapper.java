package com.boc.lfj.httpdemo.mode.adapter;

/**
 * Created by Administrator on 2017/3/16.
 * 对象适配器
 */

public class Wrapper implements Targetable{
    private Source source;
    public Wrapper(Source source){
        super();
        this.source = source;
    }
    @Override
    public void method1() {
        source.method1();
    }

    @Override
    public void method2() {
        System.out.println("this is the targetable method");
    }
}
