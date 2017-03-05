package com.boc.lfj.httpdemo.retrofit;

import com.boc.lfj.httpdemo.common.SPUtils;
import com.boc.lfj.httpdemo.constants.BaseConstants;
import com.boc.lfj.httpdemo.constants.SPKeys;
import com.boc.lfj.httpdemo.retrofit.bean.AccessToken;
import com.boc.lfj.httpdemo.retrofit.gson.GsonConverterFactory;
import com.boc.lfj.httpdemo.rx.LogInterceptor;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;


/**
 * 类名： ServiceGenerator
 * 功能说明：
 * 作者： lfj
 * 创建时间： 2016/11/4
 * 修改内容：
 * 修改人： lfj
 * 修改时间：2016/11/4
 */
public class ServiceGenerator {


    private static OkHttpClient.Builder httpClient = new OkHttpClient.Builder();

    private static Retrofit.Builder builder = new Retrofit.Builder()
            .baseUrl(BaseConstants.API_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create());

    public static <S> S create(Class<S> serviceClass) {
        String cookie = SPUtils.getStringValueFromSP(SPKeys.spName, SPKeys.cookie);
        String uid = SPUtils.getStringValueFromSP(SPKeys.spName, SPKeys.uid);
        return createService(serviceClass, cookie, uid);
    }

    public static <S> S createService(Class<S> serviceClass, final String cookie, final String uid) {
        if (cookie != null && uid != null) {
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

    public static <S> S createService(Class<S> serviceClass, final String authToken) {
        if (authToken != null) {
            httpClient.addInterceptor(new Interceptor() {
                @Override
                public Response intercept(Chain chain) throws IOException {
                    Request original = chain.request();
                    Request.Builder requestBuilder = original.newBuilder()
                            .header("Authorization", authToken)
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

    public static <S> S createService(Class<S> serviceClass, final AccessToken token) {
        if (token != null) {
            httpClient.addInterceptor(new Interceptor() {
                @Override
                public Response intercept(Chain chain) throws IOException {
                    Request original = chain.request();
                    Request.Builder requestBuilder = original.newBuilder()
                            .header("Accept", "application/json")
                            .header("Authorization", token.getTokenType() + "" + token.getAccessToken())
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

    /**
     * 不需要cookies
     *
     * @param serviceClass
     * @param <S>
     * @return
     */
    public static <S> S createService(Class<S> serviceClass) {
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
                        .method(original.method(), original.body());
                Request request = requestBuilder.build();
                return chain.proceed(request);
            }
        });
        OkHttpClient client = httpClient.build();
        Retrofit retrofit = builder.client(client).build();
        return retrofit.create(serviceClass);
    }
}
