package com.boc.lfj.httpdemo.retrofit;

import com.boc.lfj.httpdemo.bean.BaseResponseBean;
import com.boc.lfj.httpdemo.bean.login.LoginResult;
import com.boc.lfj.httpdemo.bean.login.SecretKeyRequest;
import com.boc.lfj.httpdemo.bean.login.SecretKeyResult;
import com.boc.lfj.httpdemo.bean.message.QueryMessageCriteria;
import com.boc.lfj.httpdemo.bean.message.QueryMessageResponse;
import com.boc.lfj.httpdemo.constants.BaseConstants;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Header;
import retrofit2.http.POST;

/**
 * 类名： NetService
 * 功能说明：
 * 作者： lfj
 * 创建时间： 2017/2/20
 * 修改内容：
 * 修改人： lfj
 * 修改时间：2017/2/20
 */
public interface NetService {
    @POST(BaseConstants.get_secret_key)
    Call<SecretKeyResult> requestSecretKey(@Body SecretKeyRequest criteria);
    
    @POST(BaseConstants.loginUrl)
    Call<LoginResult> login(@Body SecretKeyRequest criteria);
    
    @POST(BaseConstants.queryMessageUrl)
    Call<QueryMessageResponse> queryMessages(@Body QueryMessageCriteria criteria);

    @POST(BaseConstants.queryDeletableUrl)
    Call<BaseResponseBean> queryDeletable(@Header("yid") String yid);

    @POST(BaseConstants.queryDeletableUrl)
    Call<BaseResponseBean> queryDeletable();
}
