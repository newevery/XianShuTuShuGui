package cn.com.sino_device.xianshutushugui.user.userinfo;

import cn.com.sino_device.xianshutushugui.bean.user.GetUserInfo;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by Android Studio.
 *
 * @author affe
 * @date 2018/7/16
 */
public class GetUserInfoPresenter implements GetUserInfoContract.GetUserInfoPresenter{
    private static final String TAG = "UserInfoPresenter";

    private GetUserInfoSource userInfoSource;

    private GetUserInfoContract.GetUserInfoView userInfoView;

    public GetUserInfoPresenter(GetUserInfoContract.GetUserInfoView userInfoView) {
        this.userInfoSource = new GetUserInfoRealization();
        this.userInfoView =  checkNotNull(userInfoView, "userInfoView cannot be null!");
        this.userInfoView.setPresenter(this);
    }

    @Override
    public void getUserInfo(Object object) {
        userInfoSource.getUserInfo((GetUserInfo) object, new GetUserInfoSource.GetUserInfoCallback() {
            @Override
            public void onSuccess(Object success) {
                userInfoView.showGetUserInfo(success);
            }

            @Override
            public void onError(Object error) {
                userInfoView.showGetUserInfo(error);
            }
        });
    }

    @Override
    public void getUserAvatar(Object object) {
        userInfoSource.getUserAvatar((String) object, new GetUserInfoSource.GetUserInfoCallback() {
            @Override
            public void onSuccess(Object success) {
                userInfoView.showGetUserAvatar(success);
            }

            @Override
            public void onError(Object error) {
                userInfoView.showGetUserAvatar(error);
            }
        });
    }


    @Override
    public void start() {

    }
}
