package com.boc.lfj.httpdemo.bean.message;


import com.boc.lfj.httpdemo.bean.BaseBean;

public class QueryMessageListEntity extends BaseBean {
    /**
     * 创建日期
     */
    private String createDate;
    /**
     * 消息id
     */
    private String msgId;
    /**
     * 消息级别
     */
    private String msgLevel;
    /**
     * 消息图片
     */
    private String msgPic;
    /**
     * 消息状态
     */
    private String msgStatus;
    /**
     * 消息标题
     */
    private String msgTitle;
    /**
     * 消息文本
     */
    private String msgTxt;
    /**
     * 消息类型 消息类型 R-只读消息 O-可操作消息
     */
    private String msgType;
    /**
     * 阅读日期
     */
    private String readDate;
    /**
     * 用户id
     */
    private String uid;
    /**
     * 云支部id
     */
    private String yid;



    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public String getMsgId() {
        return msgId;
    }

    public void setMsgId(String msgId) {
        this.msgId = msgId;
    }

    public String getMsgLevel() {
        return msgLevel;
    }

    public void setMsgLevel(String msgLevel) {
        this.msgLevel = msgLevel;
    }

    public String getMsgPic() {
        return msgPic;
    }

    public void setMsgPic(String msgPic) {
        this.msgPic = msgPic;
    }

    public String getMsgStatus() {
        return msgStatus;
    }

    public void setMsgStatus(String msgStatus) {
        this.msgStatus = msgStatus;
    }

    public String getMsgTitle() {
        return msgTitle;
    }

    public void setMsgTitle(String msgTitle) {
        this.msgTitle = msgTitle;
    }

    public String getMsgTxt() {
        return msgTxt;
    }

    public void setMsgTxt(String msgTxt) {
        this.msgTxt = msgTxt;
    }

    public String getMsgType() {
        return msgType;
    }

    public void setMsgType(String msgType) {
        this.msgType = msgType;
    }

    public String getReadDate() {
        return readDate;
    }

    public void setReadDate(String readDate) {
        this.readDate = readDate;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getYid() {
        return yid;
    }

    public void setYid(String yid) {
        this.yid = yid;
    }


    /**
     * @return
     */
    private QueryMessageAbstractEntity msgAbstract;


    public QueryMessageAbstractEntity getMsgAbstract() {
        return msgAbstract;
    }

    public void setMsgAbstract(QueryMessageAbstractEntity msgAbstract) {
        this.msgAbstract = msgAbstract;
    }
}