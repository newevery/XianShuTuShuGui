package cn.com.sino_device.xianshutushugui.user;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import cn.com.sino_device.xianshutushugui.R;
import cn.com.sino_device.xianshutushugui.bean.user.Result;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * @author affe
 */
public class UserPwdActivity extends AppCompatActivity implements UserContract.AlterUserPwdView {
    private static final String TAG = "UserPwdActivity";

    private UserPresenter mUserPresenter;
    private UserContract.AlterUserPwdPresenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_pwd);
        mUserPresenter = new UserPresenter(this);
        ///mPresenter.alterUserPwd();
    }

    @Override
    public void setPresenter(UserContract.AlterUserPwdPresenter presenter) {
        mPresenter = checkNotNull(presenter);
    }

    @Override
    public void showAlterUserPwd(Object result) {

    }
}
