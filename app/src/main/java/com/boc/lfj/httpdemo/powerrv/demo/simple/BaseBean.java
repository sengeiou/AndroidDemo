package com.boc.lfj.httpdemo.powerrv.demo.simple;

/**
 * Created by Administrator on 2017/3/21.
 */

public class BaseBean {
    public BaseBean(String name){
        this.name=name;
    }
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
