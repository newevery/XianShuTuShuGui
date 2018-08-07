package cn.com.sino_device.xianshutushugui.user.signup;

import cn.com.sino_device.xianshutushugui.bean.user.UserRegister;

/**
 * Created by Android Studio.
 *
 * @author affe
 * @date 2018/7/16
 */
public interface SignUpSource {

    interface SignUpCallback {
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
     * @param signUpCallback
     */
    void userSignUp(UserRegister userRegister, SignUpCallback signUpCallback);
}
