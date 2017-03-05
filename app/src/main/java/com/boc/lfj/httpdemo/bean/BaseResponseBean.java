package com.boc.lfj.httpdemo.bean;

/**
 * Created by bocai on 2016/12/26.
 */

public class BaseResponseBean extends BaseBean {
    /**
     * 返回正确与否   true是正确返回，false错误返回
     */
    private String flag;
    /**
     * 错误返回时的信息
     */
    private String msg;

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
