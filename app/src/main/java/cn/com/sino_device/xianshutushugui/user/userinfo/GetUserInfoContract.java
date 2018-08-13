package cn.com.sino_device.xianshutushugui.user.userinfo;

import cn.com.sino_device.xianshutushugui.BasePresenter;
import cn.com.sino_device.xianshutushugui.BaseView;

/**
 * Created by Android Studio.
 *
 * @author affe
 * @date 2018/7/16
 */
public interface GetUserInfoContract {

    /**
     * 获取用户信息 View
     */
    interface GetUserInfoView extends BaseView<GetUserInfoPresenter> {
        void showGetUserInfo(Object object);
        void showGetUserAvatar(Object object);
    }


    /**
     * 获取用户信息 Presenter
     */
    interface GetUserInfoPresenter extends BasePresenter {
        void getUserInfo(Object object);
        void getUserAvatar(Object object);
    }
}
