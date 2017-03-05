package com.boc.lfj.httpdemo.constants;

/**
 * 类名： BaseConstants
 * 功能说明：
 * 作者： lfj
 * 创建时间： 2017/2/20
 * 修改内容：
 * 修改人： lfj
 * 修改时间：2017/2/20
 */
public class BaseConstants {
    public static final String API_BASE_URL = "http://22.188.14.44:8080/pbpsma/";
    /**
     *新用户获取加密密钥
     */
    public static final String get_secret_key = "unlogin/get_secret_key ";
    /**
     *登录
     */
    public static final String loginUrl = "login";
    /**
     * 查询我的消息
     */
    public static final String queryMessageUrl = "group/queryMyMsg";
    /**
     * 查询可删除成员
     */
    public static final String queryDeletableUrl = "subject/queryCanDeleteSubjectList";

}
