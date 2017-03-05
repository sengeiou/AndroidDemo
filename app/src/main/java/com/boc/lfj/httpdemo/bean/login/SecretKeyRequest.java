package com.boc.lfj.httpdemo.bean.login;

import com.boc.lfj.httpdemo.bean.BaseBean;

/**
 * Created by bocai on 2016/12/23.
 * 获取密钥赋值uid，登录赋值uid与pwd
 */

public class SecretKeyRequest extends BaseBean {
    /**
     * 登录用户名
     */
    private String uid;
    /**
     * 加密后的密码
     */
    private String pwd;

    public SecretKeyRequest(String uid) {
        this.uid = uid;
    }

    public SecretKeyRequest(String uid, String pwd) {
        this.uid = uid;
        this.pwd = pwd;
    }


}
