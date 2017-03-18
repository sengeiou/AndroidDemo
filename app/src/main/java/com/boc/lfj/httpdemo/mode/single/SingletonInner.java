package com.boc.lfj.httpdemo.mode.single;

/**
 * Created by Administrator on 2017/3/15.
 */

public class SingletonInner {
    private SingletonInner() {
    }

    private static class SingletonFactory {
        private static SingletonInner instance = new SingletonInner();
    }

    public static SingletonInner getInstance() {
        return SingletonFactory.instance;
    }

    public Object readResolve() {
        return getInstance();
    }
}
