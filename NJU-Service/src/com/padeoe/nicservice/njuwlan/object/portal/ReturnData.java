package com.padeoe.nicservice.njuwlan.object.portal;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.annotation.JSONField;

/**
 * Created by padeoe on 4/24/15.
 */

/**
 * 登陆返回信息
 */
public class ReturnData {
    @JSONField(name = "reply_code")
    private String reply_code;
    @JSONField(name = "reply_msg")
    private String reply_message;
    @JSONField(name = "userinfo")
    private UserInfo UserInfo;

    public ReturnData() {
    }

    public ReturnData(String reply_code, String reply_message, UserInfo UserInfo) {
        this.reply_code = reply_code;
        this.reply_message = reply_message;
        this.UserInfo = UserInfo;
    }

    public static ReturnData getFromJson(String jsonobject) {
        try {
            ReturnData returnData = JSON.parseObject(jsonobject, ReturnData.class);
            return returnData;
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }

    public void setReply_code(String reply_code) {
        this.reply_code = reply_code;
    }

    public String getReply_code() {
        return this.reply_code;
    }

    public void setReply_message(String reply_message) {
        this.reply_message = reply_message;
    }

    public String getReply_message() {
        return this.reply_message;
    }

    public void setUserInfo(UserInfo userInfo) {
        this.UserInfo = userInfo;
    }

    public UserInfo getUserInfo() {
        return this.UserInfo;
    }

}