package cn.com.sino_device.xianshutushugui.user.signup;

import cn.com.sino_device.xianshutushugui.BasePresenter;
import cn.com.sino_device.xianshutushugui.BaseView;

/**
 * Created by Android Studio.
 *
 * @author affe
 * @date 2018/7/16
 */
public interface SignUpContract {

    /**
     * 用户注册 View
     */
    interface SignUpView extends BaseView<SignUpPresenter> {
        void showSignUp(Object object);
    }

    /**
     * 用户注册 Presenter
     */
    interface SignUpPresenter extends BasePresenter {
        void userSignUp(Object object);
    }
}
