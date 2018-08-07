package cn.com.sino_device.xianshutushugui.user.signin;

import cn.com.sino_device.xianshutushugui.bean.user.UserLogin;
import cn.com.sino_device.xianshutushugui.user.UserSource;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by Android Studio.
 *
 * @author affe
 * @date 2018/7/16
 */
public class SignInPresenter implements SignInContract.SignInPresenter {
    private static final String TAG = "SignInPresenter";

    private SignInSource signInSource;

    private SignInContract.SignInView signInView;

    public SignInPresenter(SignInContract.SignInView signInView) {
        this.signInSource = new SignInRealization();
        this.signInView = checkNotNull(signInView, "signInView cannot be null!");
        this.signInView.setPresenter(this);
    }

    @Override
    public void userSignIn(Object object) {
        signInSource.userSignIn((UserLogin) object, new SignInSource.SignInCallback() {
            @Override
            public void onSuccess(Object success) {
                signInView.showSignIn(success);
            }

            @Override
            public void onError(Object error) {
                signInView.showSignIn(error);
            }
        });
    }

    @Override
    public void start() {

    }
}
