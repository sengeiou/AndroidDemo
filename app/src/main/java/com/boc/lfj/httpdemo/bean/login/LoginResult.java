package com.boc.lfj.httpdemo.bean.login;


import com.boc.lfj.httpdemo.bean.BaseResponseBean;

/**
 * Created by bocai on 2016/12/23.
 */

public class LoginResult extends BaseResponseBean {

    private LoginResponseInfo otherInfo;

    public LoginResponseInfo getOtherInfo() {
        return otherInfo;
    }

    public void setOtherInfo(LoginResponseInfo otherInfo) {
        this.otherInfo = otherInfo;
    }

}
