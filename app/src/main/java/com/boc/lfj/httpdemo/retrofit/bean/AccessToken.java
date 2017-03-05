package com.boc.lfj.httpdemo.retrofit.bean;

/**
 * 类名： AccessToken
 * 功能说明：
 * 作者： lfj
 * 创建时间： 2016/11/4
 * 修改内容：
 * 修改人： lfj
 * 修改时间：2016/11/4
 */
public class AccessToken {
    String tokenType;
    String accessToken;

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getTokenType() {
        if (!Character.isUpperCase(tokenType.charAt(0))){
            tokenType = Character.toString(tokenType.charAt(0)).toUpperCase()+tokenType.substring(1);
        }
        return tokenType;
    }

    public void setTokenType(String tokenType) {
        this.tokenType = tokenType;
    }
}
