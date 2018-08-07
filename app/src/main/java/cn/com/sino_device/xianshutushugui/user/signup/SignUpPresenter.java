package cn.com.sino_device.xianshutushugui.user.signup;

import cn.com.sino_device.xianshutushugui.bean.user.UserRegister;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by Android Studio.
 *
 * @author affe
 * @date 2018/7/16
 */
public class SignUpPresenter implements SignUpContract.SignUpPresenter {
    private static final String TAG = "SignUpPresenter";

    private SignUpSource signUpSource;

    private SignUpContract.SignUpView signUpView;

    public SignUpPresenter(SignUpContract.SignUpView signUpView) {
        this.signUpSource = new SignUpRealization();
        this.signUpView = checkNotNull(signUpView, "signUpView cannot be null!");
        this.signUpView.setPresenter(this);
    }

    @Override
    public void userSignUp(Object object) {
        signUpSource.userSignUp((UserRegister) object, new SignUpSource.SignUpCallback() {
            @Override
            public void onSuccess(Object success) {
                signUpView.showSignUp(success);
            }

            @Override
            public void onError(Object error) {
                signUpView.showSignUp(error);
            }
        });
    }

    @Override
    public void start() {

    }
}
