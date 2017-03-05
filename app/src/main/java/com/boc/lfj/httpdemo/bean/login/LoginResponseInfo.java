package com.boc.lfj.httpdemo.bean.login;

import com.boc.lfj.httpdemo.bean.BaseBean;

/**
 * 登录返回用户信息
 */
public class LoginResponseInfo extends BaseBean {

    private String BirthDay;
    private String BusinessTel;
    private String EmailAddress;
    private String HeadPortrait;
    private String IDCardNo;
    /**
     * 手机号
     */
    private String MobilePhone;
    /**
     * 国家
     */
    private String Nation;
    /**
     * 本地地址
     */
    private String NativePlace;
    /**
     * 组织名称
     */
    private String OrganizationName;
    /**
     * 政治面貌
     */
    private String PoliticsStatus;
    private String Sex;
    private String UserName;
    private String uid;
    private String sessionid;
    /**
     * 首次登陆返回的提示语
     */
    private String content;

    /**
     * 首次登录积分
     */
    private String point;

    /**
     * 是否有未读消息 Y-有未读消息，N-没有未读消息
     */
    private String isUnReadMsg;

    private String userID;

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getBirthDay() {
        return BirthDay;
    }

    public void setBirthDay(String birthDay) {
        BirthDay = birthDay;
    }

    public String getBusinessTel() {
        return BusinessTel;
    }

    public void setBusinessTel(String businessTel) {
        BusinessTel = businessTel;
    }

    public String getEmailAddress() {
        return EmailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        EmailAddress = emailAddress;
    }

    public String getHeadPortrait() {
        return HeadPortrait;
    }

    public void setHeadPortrait(String headPortrait) {
        HeadPortrait = headPortrait;
    }

    public String getIDCardNo() {
        return IDCardNo;
    }

    public void setIDCardNo(String IDCardNo) {
        this.IDCardNo = IDCardNo;
    }

    public String getMobilePhone() {
        return MobilePhone;
    }

    public void setMobilePhone(String mobilePhone) {
        MobilePhone = mobilePhone;
    }

    public String getNation() {
        return Nation;
    }

    public void setNation(String nation) {
        Nation = nation;
    }

    public String getNativePlace() {
        return NativePlace;
    }

    public void setNativePlace(String nativePlace) {
        NativePlace = nativePlace;
    }

    public String getOrganizationName() {
        return OrganizationName;
    }

    public void setOrganizationName(String organizationName) {
        OrganizationName = organizationName;
    }

    public String getPoliticsStatus() {
        return PoliticsStatus;
    }

    public void setPoliticsStatus(String politicsStatus) {
        PoliticsStatus = politicsStatus;
    }

    public String getSex() {
        return Sex;
    }

    public void setSex(String sex) {
        Sex = sex;
    }

    public String getUserName() {
        return UserName;
    }

    public void setUserName(String userName) {
        UserName = userName;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getSessionid() {
        return sessionid;
    }

    public void setSessionid(String sessionid) {
        this.sessionid = sessionid;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getPoint() {
        return point;
    }

    public void setPoint(String point) {
        this.point = point;
    }

    public String getIsUnReadMsg() {
        return isUnReadMsg;
    }

    public void setIsUnReadMsg(String isUnReadMsg) {
        this.isUnReadMsg = isUnReadMsg;
    }
}
