package cn.com.sino_device.xianshutushugui.user.signin;

import cn.com.sino_device.xianshutushugui.bean.user.UserLogin;

/**
 * Created by Android Studio.
 *
 * @author affe
 * @date 2018/7/16
 */
public interface SignInSource {

    interface SignInCallback {
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
     * 用户登录
     *
     * @param userLogin
     * @param signInCallback
     */
    void userSignIn(UserLogin userLogin, SignInCallback signInCallback);

}
