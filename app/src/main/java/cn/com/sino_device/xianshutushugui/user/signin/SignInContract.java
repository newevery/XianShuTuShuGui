package cn.com.sino_device.xianshutushugui.user.signin;

import cn.com.sino_device.xianshutushugui.BasePresenter;
import cn.com.sino_device.xianshutushugui.BaseView;
import cn.com.sino_device.xianshutushugui.user.UserContract;

/**
 * Created by Android Studio.
 *
 * @author affe
 * @date 2018/7/16
 */
public interface SignInContract {

    /**
     * 用户登录 View
     */
    interface SignInView extends BaseView<SignInPresenter> {
        void showSignIn(Object object);
    }

    /**
     * 用户登录 Presenter
     */
    interface SignInPresenter extends BasePresenter {
        void userSignIn(Object object);
    }
}
