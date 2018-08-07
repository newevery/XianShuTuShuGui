package cn.com.sino_device.xianshutushugui.user.userinfo;

import cn.com.sino_device.xianshutushugui.bean.user.GetUserInfo;

/**
 * Created by Android Studio.
 *
 * @author affe
 * @date 2018/7/16
 */
public interface GetUserInfoSource {

    interface GetUserInfoCallback {
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
     * 获取用户信息
     * @param getUserInfo
     * @param userInfoCallback
     */
    void getUserInfo(GetUserInfo getUserInfo, GetUserInfoCallback userInfoCallback);


}
