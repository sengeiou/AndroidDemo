package com.boc.lfj.httpdemo.mode.single;

/**
 * Created by Administrator on 2017/3/15.
 */

public class Singleton {
    private static Singleton instance = null;

    private Singleton() {

    }

    public static Singleton getInstance() {
        if (instance == null) {
            instance = new Singleton();
        }
        return instance;
    }

    public static synchronized Singleton getInstance1(){
        if (instance == null) {
            instance = new Singleton();
        }
        return instance;
    }

    public static Singleton getInstance2(){
        if (instance==null){
            synchronized (instance){
                if (instance==null){
                    instance = new Singleton();
                }
            }
        }
        return instance;
    }


}
