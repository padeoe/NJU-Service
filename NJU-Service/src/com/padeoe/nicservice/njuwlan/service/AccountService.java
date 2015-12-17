package com.padeoe.nicservice.njuwlan.service;

import com.padeoe.nicservice.njuwlan.utils.NetworkUtils;

/**
 * Created by padeoe on 2015/9/26.
 */
public class AccountService {
    /**
     * 修改密码
     *
     * @param username 用户名
     * @param oldPassword 旧密码
     * @param newPassword 新密码
     * @return 服务器返回消息
     */
    public static String changePassword(String username, String oldPassword, String newPassword) {
        if (LoginService.getInstance().isPortalOnline()) {
            String result = NetworkUtils.connectAndPost("oldpassword=" + oldPassword + "&newpassword=" + newPassword + "&confirmpassword=" + newPassword, "http://"+NetworkUtils.getCurrentPortalIP()+"/portal_io/selfservice/modifypassword", 200);
            return result;
        } else {
            //首先登陆bras.nju.edu.cn获取cookie
            String cookie = null;
            String[] loginResult = NetworkUtils.postAndGetCookie("username=" + username + "&password=" + oldPassword, "http://"+NetworkUtils.getCurrentBrasIP()+":8080/manage/self/auth/login", 200);
            if (OfflineQueryService.isLoginSuccess(loginResult[0])) {
                cookie = loginResult[1];
                //登陆成功，修改密码
                String result = NetworkUtils.postWithCookie("oldPassword=" + oldPassword + "&newPassword=" + newPassword + "&confirmPassword=" + newPassword, cookie, "http://"+NetworkUtils.getCurrentBrasIP()+":8080/manage/self/userinfo/modifypassword", 200);
                return result;
            } else {
                isChangePasswSuccess("");
                return loginResult[0];
            }
        }
    }

    /**
     * 判断是否更改密码成功
     * @param result {@link AccountService#changePassword(String username, String oldPassword, String newPassword)}返回值
     * @return
     */
    public static boolean isChangePasswSuccess(String result) {
        if (result.equals("{\"reply_msg\":\"操作成功\",\"reply_code\":0}\n") || result.endsWith("\"reply_code\":0,\"request_uri\":\"/manage/self/userinfo/modifypassword\"}\n")) {
            return true;
        }
        return false;
    }

}
