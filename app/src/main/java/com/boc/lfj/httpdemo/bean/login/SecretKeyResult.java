package com.boc.lfj.httpdemo.bean.login;

import com.boc.lfj.httpdemo.bean.BaseResponseBean;

/**
 * Created by bocai on 2016/12/23.
 */

public class SecretKeyResult extends BaseResponseBean {


    public OtherInfo getOtherInfo() {
        return otherInfo;
    }

    public void setOtherInfo(OtherInfo otherInfo) {
        this.otherInfo = otherInfo;
    }

    /**
     * 报文体字段
     */
    private OtherInfo otherInfo;
    public class OtherInfo {
        /**
         * 加密密码的密钥
         */
        private String IDCardNo;

        public String getIDCardNo() {
            return IDCardNo;
        }

        public void setIDCardNo(String IDCardNo) {
            this.IDCardNo = IDCardNo;
        }
    }
}
