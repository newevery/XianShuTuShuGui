package cn.com.sino_device.xianshutushugui.user;

import cn.com.sino_device.xianshutushugui.bean.user.Result;
import cn.com.sino_device.xianshutushugui.bean.user.AlterUserInfo;
import cn.com.sino_device.xianshutushugui.bean.user.UserLogin;
import cn.com.sino_device.xianshutushugui.bean.user.AlterUserPwd;
import cn.com.sino_device.xianshutushugui.bean.user.UserRegister;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by Android Studio.
 * Date: 2018/5/9
 * Time: 11:03
 *
 * @author affe
 */
public class UserPresenter implements UserContract.RegisterPresenter, UserContract.LoginPresenter, UserContract.AlterUserInfoPresenter, UserContract.AlterUserPwdPresenter {
    private static final String TAG = "UserPresenter";

    private UserSource userSource;

    private UserContract.RegisterView mRegisterView;
    private UserContract.LoginView mLoginView;
    private UserContract.AlterUserInfoView mAlterUserInfoView;
    private UserContract.AlterUserPwdView mAlterUserPwdView;

    public UserPresenter(UserContract.RegisterView registerView) {
        this.userSource = new UserRealization();
        mRegisterView = checkNotNull(registerView, "registerView cannot be null!");
        mRegisterView.setPresenter(this);
    }

    public UserPresenter(UserContract.LoginView loginView) {
        this.userSource = new UserRealization();
        mLoginView = checkNotNull(loginView, "registerView cannot be null!");
        mLoginView.setPresenter(this);
    }

    public UserPresenter(UserContract.AlterUserInfoView alterUserInfoView) {
        this.userSource = new UserRealization();
        mAlterUserInfoView = checkNotNull(alterUserInfoView, "registerView cannot be null!");
        mAlterUserInfoView.setPresenter(this);
    }

    public UserPresenter(UserContract.AlterUserPwdView alterUserPwdView) {
        this.userSource = new UserRealization();
        mAlterUserPwdView = checkNotNull(alterUserPwdView, "registerView cannot be null!");
        mAlterUserPwdView.setPresenter(this);
    }

    @Override
    public void start() {

    }

    @Override
    public void userRegister(Object object) {
        userSource.userRegister((UserRegister) object, new UserSource.UserCallback() {
            @Override
            public void onSuccess(Object success) {
                mRegisterView.showRegister(success);
            }

            @Override
            public void onError(Object error) {
                mRegisterView.showRegister(error);
            }
        });
    }


    @Override
    public void userLogin(Object object) {
        userSource.userLogin((UserLogin) object, new UserSource.UserCallback() {
            @Override
            public void onSuccess(Object success) {
                mLoginView.showLogin(success);
            }

            @Override
            public void onError(Object error) {
                mLoginView.showLogin(error);
            }
        });
    }

    @Override
    public void alterUserInfo(Object object) {
        userSource.alterUserInfo((AlterUserInfo) object, new UserSource.UserCallback() {
            @Override
            public void onSuccess(Object success) {
                mAlterUserInfoView.showAlterUserInfo(success);
            }

            @Override
            public void onError(Object error) {
                mAlterUserInfoView.showAlterUserInfo(error);
            }
        });
    }

    @Override
    public void alterUserPwd(Object object) {
        userSource.alterUserPwd((AlterUserPwd) object, new UserSource.UserCallback() {
            @Override
            public void onSuccess(Object success) {
                mAlterUserPwdView.showAlterUserPwd(success);
            }

            @Override
            public void onError(Object error) {
                mAlterUserPwdView.showAlterUserPwd(error);
            }
        });
    }



}
