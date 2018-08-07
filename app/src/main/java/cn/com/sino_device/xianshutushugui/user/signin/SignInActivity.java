package cn.com.sino_device.xianshutushugui.user.signin;

import android.content.Intent;
import android.os.Bundle;
import android.os.Looper;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;

import cn.com.sino_device.xianshutushugui.GlobalConsts;
import cn.com.sino_device.xianshutushugui.MainActivity;
import cn.com.sino_device.xianshutushugui.R;
import cn.com.sino_device.xianshutushugui.bean.user.Result;
import cn.com.sino_device.xianshutushugui.bean.user.UserLogin;
import cn.com.sino_device.xianshutushugui.user.MsgSignInActivity;
import cn.com.sino_device.xianshutushugui.user.signup.SignUpActivity;
import cn.com.sino_device.xianshutushugui.util.RegularExpressionUtils;
import cn.com.sino_device.xianshutushugui.util.SPUtils;

import static cn.com.sino_device.xianshutushugui.GlobalConsts.ERROR_CODE_SUCCESS;
import static com.google.common.base.Preconditions.checkNotNull;

/**
 * 用户登录
 */
public class SignInActivity extends AppCompatActivity implements SignInContract.SignInView, View.OnClickListener, CompoundButton.OnCheckedChangeListener {
    private static final String TAG = "SignInActivity";


    private TextInputLayout tilAccount;
    private TextInputEditText tietAccount;
    private TextInputLayout tilPassword;
    private TextInputEditText tietPassword;

    private CheckBox cbRememberPassword;

    private Button btnLogin;
    private TextView tvSignInMsgCode;
    private TextView tvSignUp;

    private SignInPresenter mSignInPresenter;
    private SignInContract.SignInPresenter mPresenter;
    private UserLogin userLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        initView();
        mSignInPresenter = new SignInPresenter(this);

    }

    private void initView() {
        tilAccount = findViewById(R.id.til_account);
        tietAccount = findViewById(R.id.tiet_account);
        tilPassword = findViewById(R.id.til_password);
        tietPassword = findViewById(R.id.tiet_password);
        //
        boolean check = (boolean) SPUtils.get(this, "ISCHECKED", false);
        if (check) {
            String account = (String) SPUtils.get(this, "ACCOUNT", "");
            String password = (String) SPUtils.get(this, "PASSWORD", "");
            tietAccount.setText(account);
            tietPassword.setText(password);
        }
        cbRememberPassword = findViewById(R.id.cb_remember_password);
        cbRememberPassword.setOnCheckedChangeListener(this);
        btnLogin = findViewById(R.id.btn_sign_in);
        btnLogin.setOnClickListener(this);
        tvSignInMsgCode = findViewById(R.id.tv_sign_in_msg_code);
        tvSignInMsgCode.setOnClickListener(this);
        tvSignUp = findViewById(R.id.tv_sign_up);
        tvSignUp.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_sign_in_msg_code:
                Intent intentMsgSignIn = new Intent(SignInActivity.this, MsgSignInActivity.class);
                startActivity(intentMsgSignIn);
                break;
            case R.id.btn_sign_in:
                String account = tietAccount.getText().toString();
                String password = tietPassword.getText().toString();

                // 验证
                if (validateAccount(account) && validatePassword(password)) {
                    userLogin = new UserLogin();
                    /**
                     * 以账号或手机号登录，先判断 account 为账号还是手机号，并设置登录账号类型
                     */
                    if (RegularExpressionUtils.checkMobileNumber(account)) {
                        // 如果登录名为手机号，则设置登录类型为“1”，并不设置“Name”
                        userLogin.setType("1");
                        userLogin.setMobile(account);
                    } else {
                        // 如果登录名为账号，则设置登录类型为“0”，并不设置“Mobile”
                        userLogin.setType("0");
                        userLogin.setName(account);
                    }
                    userLogin.setPassword(password);
                    /**
                     * TODO 用户IP，需通过接口获取
                     */
                    userLogin.setLogin_ip("0.0.0.0");
                    // 2007-01-31 00:00:00
                    Date date = new Date();
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
                    String now = sdf.format(date);
                    userLogin.setLogin_date(now);
                    // 登录
                    mPresenter.userSignIn(userLogin);
                    //
                    boolean ischecked = (boolean) SPUtils.get(this, "ISCHECKED", false);
                    if (ischecked) {
                        SPUtils.put(this, "ACCOUNT", account);
                        SPUtils.put(this, "PASSWORD", password);
                    }
                }
                break;
            case R.id.tv_sign_up:
                Intent intentSignUp = new Intent(SignInActivity.this, SignUpActivity.class);
                startActivity(intentSignUp);
                break;
            default:
                break;
        }
    }

    @Override
    public void setPresenter(SignInContract.SignInPresenter presenter) {
        mPresenter = checkNotNull(presenter);
    }

    @Override
    public void showSignIn(Object object) {
        Log.i(TAG, object.toString());
        if (object.getClass() == Result.class) {
            Result result = (Result) object;
            //
            if (ERROR_CODE_SUCCESS.equals(result.getErrorCode())) {
                // 登陆成功
                // TODO 根据返回信息，如果用户信息未完善，跳转至用户信息编辑界面
                ///Intent intentMain = new Intent(SignInActivity.this, EditUserinfoActivity.class);
                ///startActivity(intentMain);
                ///SignInActivity.this.finish();
                // TODO 保存用户登录名或手机号，{"success":true,"errorCode":"0","msg":"登录成功"}
                String userInfo = userLogin.getMobile() != null ? userLogin.getMobile() : userLogin.getName();
                SPUtils.put(this, "CURRENT_USER", userInfo);
                // 跳转到主界面
                Intent intentMain = new Intent(SignInActivity.this, MainActivity.class);
                startActivity(intentMain);
                SignInActivity.this.finish();
                Looper.prepare();
                Toast.makeText(SignInActivity.this, result.getMsg(), Toast.LENGTH_SHORT).show();
                Looper.loop();
            } else {
                // 登陆失败
                Looper.prepare();
                Toast.makeText(SignInActivity.this, result.getMsg(), Toast.LENGTH_LONG).show();
                Looper.loop();
            }
        } else {
            Log.i(TAG, object.toString());
            Looper.prepare();
            Toast.makeText(SignInActivity.this, object.toString(), Toast.LENGTH_SHORT).show();
            Looper.loop();
        }
    }


    /**
     * 验证用户名
     *
     * @param account
     * @return
     */
    private boolean validateAccount(String account) {
        if (TextUtils.isEmpty(account)) {
            showError(tilAccount, "用户名不能为空");
            return false;
        }
        return true;
    }

    /**
     * 验证密码
     *
     * @param password
     * @return
     */
    private boolean validatePassword(String password) {
        if (TextUtils.isEmpty(password)) {
            showError(tilPassword, "密码不能为空");
            return false;
        }
        if (password.length() < GlobalConsts.PASSWORD_MIN_LENGTH
                || password.length() > GlobalConsts.PASSWORD_MAX_LENGTH) {
            showError(tilPassword, "密码长度为6-18位");
            return false;
        }
        return true;
    }

    /**
     * 显示错误提示，并获取焦点
     *
     * @param textInputLayout
     * @param error
     */
    private void showError(TextInputLayout textInputLayout, String error) {
        textInputLayout.setError(error);
        Objects.requireNonNull(textInputLayout.getEditText()).setFocusable(true);
        textInputLayout.getEditText().setFocusableInTouchMode(true);
        textInputLayout.getEditText().requestFocus();
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        if (buttonView.getId() == R.id.cb_remember_password) {
            if (isChecked) {
                SPUtils.put(this, "ISCHECKED", true);
                SPUtils.put(this, "ACCOUNT", tietAccount.getText().toString());
                SPUtils.put(this, "PASSWORD", tietPassword.getText().toString());
            } else {
                SPUtils.put(this, "ISCHECKED", false);
                SPUtils.remove(this, "ACCOUNT");
                SPUtils.remove(this, "PASSWORD");
            }
        }
    }

}
