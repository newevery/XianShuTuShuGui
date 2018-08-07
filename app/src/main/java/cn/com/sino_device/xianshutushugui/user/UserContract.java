package cn.com.sino_device.xianshutushugui.user;

import cn.com.sino_device.xianshutushugui.BasePresenter;
import cn.com.sino_device.xianshutushugui.BaseView;
import cn.com.sino_device.xianshutushugui.bean.user.Result;
import cn.com.sino_device.xianshutushugui.bean.user.AlterUserInfo;
import cn.com.sino_device.xianshutushugui.bean.user.UserLogin;
import cn.com.sino_device.xianshutushugui.bean.user.AlterUserPwd;
import cn.com.sino_device.xianshutushugui.bean.user.UserRegister;

/**
 * Created by Android Studio.
 * Date: 2018/5/8
 * Time: 11:57
 *
 * @author affe
 */
public interface UserContract {

    /**
     * 用户注册 View
     */
    interface RegisterView extends BaseView<RegisterPresenter> {
        void showRegister(Object object);
    }

    /**
     * 用户登录 View
     */
    interface LoginView extends BaseView<LoginPresenter> {
        void showLogin(Object object);
    }

    /**
     * 用户信息修改 View
     */
    interface AlterUserInfoView extends BaseView<AlterUserInfoPresenter> {
        void showAlterUserInfo(Object object);
    }

    /**
     * 用户密码修改 View
     */
    interface AlterUserPwdView extends BaseView<AlterUserPwdPresenter> {
        void showAlterUserPwd(Object object);
    }


    /**
     * 用户注册 Presenter
     */
    interface RegisterPresenter extends BasePresenter {
        void userRegister(Object object);
    }

    /**
     * 用户登录 Presenter
     */
    interface LoginPresenter extends BasePresenter {
        void userLogin(Object object);
    }

    /**
     * 用户信息修改 Presenter
     */
    interface AlterUserInfoPresenter extends BasePresenter {
        void alterUserInfo(Object object);
    }

    /**
     * 用户密码修改 Presenter
     */
    interface AlterUserPwdPresenter extends BasePresenter {
        void alterUserPwd(Object object);
    }


}
