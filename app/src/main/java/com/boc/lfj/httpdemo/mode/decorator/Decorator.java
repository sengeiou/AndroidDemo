package com.boc.lfj.httpdemo.mode.decorator;

/**
 * Created by Administrator on 2017/3/16.
 */

public class Decorator implements Sourceable {
    private Source source;

    public Decorator(Source source) {
        super();
        this.source = source;
    }

    @Override
    public void method() {
        System.out.println("before");
        source.method();
        System.out.println("before");
    }
}
