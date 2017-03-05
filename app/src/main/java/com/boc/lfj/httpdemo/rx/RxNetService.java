package com.boc.lfj.httpdemo.rx;

import com.boc.lfj.httpdemo.bean.BaseResponseBean;
import com.boc.lfj.httpdemo.constants.BaseConstants;

import retrofit2.http.Header;
import retrofit2.http.POST;
import rx.Observable;

/**
 * Created by Administrator on 2017/2/22.
 */

public interface RxNetService {

    @POST(BaseConstants.queryDeletableUrl)
    Observable<BaseResponseBean> queryDeletable(@Header("yid") String yid);
}
