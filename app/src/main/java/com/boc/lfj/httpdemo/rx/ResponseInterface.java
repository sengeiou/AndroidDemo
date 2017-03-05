package com.boc.lfj.httpdemo.rx;

/**
 * Created by Administrator on 2017/2/22.
 */

public interface ResponseInterface {
    void start();

    void error(String error);

    void completed();
}
