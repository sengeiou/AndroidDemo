package com.boc.lfj.httpdemo.bean.message;

import com.boc.lfj.httpdemo.bean.BaseBean;

import java.util.List;

public class QueryMessageInfoEntity extends BaseBean {
    /**
     * pageNo : 000000
     * recordCount : 1
     * list : [{"uid":"U0000003","msgType":"R","yid":20,"msgTitle":"test1","readDate":"","msgAbstract":"fuck","msgId":1,"msgTxt":"testmaliang","msgLevel":"","msgPic":"","msgStatus":"N","createDate":"2016-02-20 00:00:00.0"}]
     */
    private String pageNo;
    private String recordCount;
    private List<QueryMessageListEntity> list;

    public void setPageNo(String pageNo) {
        this.pageNo = pageNo;
    }

    public void setRecordCount(String recordCount) {
        this.recordCount = recordCount;
    }

    public void setList(List<QueryMessageListEntity> list) {
        this.list = list;
    }

    public String getPageNo() {
        return pageNo;
    }

    public String getRecordCount() {
        return recordCount;
    }

    public List<QueryMessageListEntity> getList() {
        return list;
    }


}
