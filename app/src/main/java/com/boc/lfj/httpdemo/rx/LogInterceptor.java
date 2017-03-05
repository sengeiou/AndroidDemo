package com.boc.lfj.httpdemo.rx;

import com.boc.lfj.httpdemo.common.log.Logger;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;

/**
 * Created by Administrator on 2017/2/22.
 */

public class LogInterceptor implements Interceptor {



    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        Logger.d("------url:"+request.toString());
        long t1 = System.nanoTime();
        Response response = chain.proceed(chain.request());
        long t2 = System.nanoTime();
        Logger.v("------请求的网络信息:"+response.request().url()+response.headers());
        MediaType mediaType = response.body().contentType();
        String content = response.body().string();
        Logger.d("------response body:"+content);

        return response.newBuilder().body(ResponseBody.create(mediaType,content)).build();
    }
}
