package com.boc.lfj.httpdemo.bean.message;


import com.boc.lfj.httpdemo.bean.BaseBean;

/**
 * 类名： QueryMessageAbstractEntity
 * 功能说明：
 * 作者： lfj
 * 创建时间： 2017/1/6
 * 修改内容：
 * 修改人： lfj
 * 修改时间：2017/1/6
 */
public class QueryMessageAbstractEntity extends BaseBean {
    private String type;
    private String yid;
    private String iuid;
    private String reyid;
    private String owner;//书记
    private String inviter;//邀请人
    private String getPoint;
    private String applyid;
    private String allPoint;
    
    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    private String uid;

    public String getIuid() {
        return iuid;
    }

    public void setIuid(String iuid) {
        this.iuid = iuid;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getYid() {
        return yid;
    }

    public void setYid(String yid) {
        this.yid = yid;
    }

    public String getReyid() {
        return reyid;
    }

    public void setReyid(String reyid) {
        this.reyid = reyid;
    }

    public String getInviter() {
        return inviter;
    }

    public void setInviter(String inviter) {
        this.inviter = inviter;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public String getAllPoint() {
        return allPoint;
    }

    public void setAllPoint(String allPoint) {
        this.allPoint = allPoint;
    }

    public String getGetPoint() {
        return getPoint;
    }

    public void setGetPoint(String getPoint) {
        this.getPoint = getPoint;
    }
    public String getApplyid() {
        return applyid;
    }

    public void setApplyid(String applyid) {
        this.applyid = applyid;
    }
}
