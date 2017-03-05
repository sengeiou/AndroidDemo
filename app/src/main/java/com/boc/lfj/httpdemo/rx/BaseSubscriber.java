package com.boc.lfj.httpdemo.rx;

import rx.Subscriber;

/**
 * Created by Administrator on 2017/2/22.
 */
//可以在这里处理公共的报错
public abstract class BaseSubscriber<T> extends Subscriber<T> implements ResponseInterface{

    @Override
    public void onStart() {
        start();
    }

    @Override
    public void onCompleted() {
        completed();
    }

    @Override
    public void onError(Throwable throwable) {
        String errorString = throwable.getLocalizedMessage();
        if (errorString.contains("UnknowHostException")){

        }else {
            error(throwable.getMessage());
        }
    }

    @Override
    public void onNext(T o) {
        //公共报错可以这里处理
        next(o);
    }


    public abstract void next(T o);

    @Override
    public void start() {

    }

    @Override
    public void completed() {

    }

    @Override
    public void error(String error) {

    }
}
