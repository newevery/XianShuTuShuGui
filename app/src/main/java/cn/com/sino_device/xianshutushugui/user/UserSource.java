package cn.com.sino_device.xianshutushugui.user;


import cn.com.sino_device.xianshutushugui.bean.user.AlterUserInfo;
import cn.com.sino_device.xianshutushugui.bean.user.GetUserInfo;
import cn.com.sino_device.xianshutushugui.bean.user.UserLogin;
import cn.com.sino_device.xianshutushugui.bean.user.AlterUserPwd;
import cn.com.sino_device.xianshutushugui.bean.user.UserRegister;

public interface UserSource {

    interface UserCallback {
        /**
         * 成功
         *
         * @param success
         */
        void onSuccess(Object success);

        /**
         * 出错
         *
         * @param error
         */
        void onError(Object error);
    }

    /**
     * 用户注册
     *
     * @param userRegister
     * @param userCallback
     */
    void userRegister(UserRegister userRegister, UserCallback userCallback);

    /**
     * 用户登录
     *
     * @param userLogin
     * @param userCallback
     */
    void userLogin(UserLogin userLogin, UserCallback userCallback);

    /**
     * 用户信息修改
     *
     * @param alterUserInfo
     * @param userCallback
     */
    void alterUserInfo(AlterUserInfo alterUserInfo, UserCallback userCallback);

    /**
     * 用户密码修改
     *
     * @param alterUserPwd
     * @param userCallback
     */
    void alterUserPwd(AlterUserPwd alterUserPwd, UserCallback userCallback);

    /**
     * 获取用户信息
     * @param getUserInfo
     * @param userCallback
     */
    void getUserInfo(GetUserInfo getUserInfo, UserCallback userCallback);

}