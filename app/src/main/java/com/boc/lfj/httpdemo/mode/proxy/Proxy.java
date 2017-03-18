package com.boc.lfj.httpdemo.mode.proxy;

/**
 * Created by Administrator on 2017/3/16.
 */

public class Proxy implements Sourceable {
    private Source source;

    public Proxy(){
        super();
        source = new Source();
    }


    @Override
    public void method() {
        before();
        source.method();
        after();
    }

    private void before() {

    }

    private void after() {

    }
}
