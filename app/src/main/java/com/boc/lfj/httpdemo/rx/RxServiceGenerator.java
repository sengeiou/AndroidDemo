package com.boc.lfj.httpdemo.rx;

import com.boc.lfj.httpdemo.common.SPUtils;
import com.boc.lfj.httpdemo.constants.BaseConstants;
import com.boc.lfj.httpdemo.constants.SPKeys;
import com.boc.lfj.httpdemo.retrofit.gson.GsonConverterFactory;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;

/**
 * Created by Administrator on 2017/2/22.
 */

public class RxServiceGenerator {
    private static OkHttpClient.Builder httpClient = new OkHttpClient.Builder();

    private static Retrofit.Builder builder = new Retrofit.Builder()
            .baseUrl(BaseConstants.API_BASE_URL)
            .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create());


    /**
     * 重置baseUrl
     */
    public static void resetBaseUrl(String url) {
        builder = new Retrofit.Builder()
                .baseUrl(url)
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create());
    }


    public static <S> S create(Class<S> serviceClass) {
        String cookie = SPUtils.getStringValueFromSP(SPKeys.spName, SPKeys.cookie);
        String uid = SPUtils.getStringValueFromSP(SPKeys.spName, SPKeys.uid);
        return createService(serviceClass, cookie, uid);
    }

    public static <S> S createService(Class<S> serviceClass, final String cookie, final String uid) {
        if (cookie != null && uid != null) {
            httpClient.addInterceptor(new LogInterceptor());
            httpClient.addInterceptor(new Interceptor() {
                @Override
                public Response intercept(Chain chain) throws IOException {
                    Request original = chain.request();
                    Request.Builder requestBuilder = original.newBuilder()
                            .header("Accept", "application/json")
                            .header("Content-Type", "application/json; charset=UTF-8")
                            .header("Cache-Control", "no-cache")
                            .header("Accept-Charset", "UTF-8")
                            .header("cookie", cookie)
                            .header("uid", uid)
                            .method(original.method(), original.body());
                    Request request = requestBuilder.build();
                    return chain.proceed(request);

                }
            });
        }
        OkHttpClient client = httpClient.build();
        Retrofit retrofit = builder.client(client).build();
        return retrofit.create(serviceClass);
    }
}
