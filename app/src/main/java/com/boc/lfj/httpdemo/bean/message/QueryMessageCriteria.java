package com.boc.lfj.httpdemo.bean.message;

import com.boc.lfj.httpdemo.bean.BaseBean;

/**
 * 类名： QueryMessageCriteria
 * 功能说明：
 * 作者： lfj
 * 创建时间： 2016/12/28
 * 修改内容：
 * 修改人： lfj
 * 修改时间：2016/12/28
 */
public class QueryMessageCriteria extends BaseBean {

    /**
     * uid : U0000003
     * pageNo : 1
     */
    private String uid;
    private String pageNo;

    public void setUid(String uid) {
        this.uid = uid;
    }

    public void setPageNo(String pageNo) {
        this.pageNo = pageNo;
    }

    public String getUid() {
        return uid;
    }

    public String getPageNo() {
        return pageNo;
    }
}
