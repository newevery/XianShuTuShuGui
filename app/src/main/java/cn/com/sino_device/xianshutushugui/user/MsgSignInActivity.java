package cn.com.sino_device.xianshutushugui.user;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import cn.com.sino_device.xianshutushugui.MainActivity;
import cn.com.sino_device.xianshutushugui.R;
import cn.com.sino_device.xianshutushugui.bean.user.UserLogin;
import cn.com.sino_device.xianshutushugui.util.RegularExpressionUtils;
import cn.com.sino_device.xianshutushugui.util.T;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * 验证码登录
 */
public class MsgSignInActivity extends AppCompatActivity implements UserContract.LoginView, View.OnClickListener {

    /**
     * 返回按钮
     */
    private ImageButton ibGoBack;
    /**
     * 手机号
     */
    private EditText etMobile;
    /**
     * 短信验证码
     */
    private EditText etMessageCode;
    /**
     * 获取短信验证码
     */
    private TextView tvMessageCode;
    /**
     * 用于获取验证码倒计时
     */
    private CountDownTimer countDownTimer = new CountDownTimer(60000, 1000) {

        @Override
        public void onTick(long millisUntilFinished) {
            int time = (int) (Math.round((double) millisUntilFinished / 1000) - 1);
            tvMessageCode.setText(String.valueOf(time) + "秒后重新获取");
        }

        @Override
        public void onFinish() {
            tvMessageCode.setEnabled(true);
            tvMessageCode.setText("获取验证码");
        }
    };

    /**
     * 登录
     */
    private Button btnLogin;

    private UserPresenter mUserPresenter;
    private UserContract.LoginPresenter mPresenter;
    private UserLogin userLogin;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_msg_sign_in);

        initView();
        mUserPresenter = new UserPresenter(this);

    }

    private void initView() {
        ibGoBack = findViewById(R.id.ib_go_back);
        ibGoBack.setOnClickListener(this);
        // 手机号
        etMobile = findViewById(R.id.et_mobile);
        // 短信验证码
        etMessageCode = findViewById(R.id.et_message_code);
        tvMessageCode = findViewById(R.id.tv_message_code);
        tvMessageCode.setOnClickListener(this);
        btnLogin = findViewById(R.id.btn_sign_in);
        btnLogin.setOnClickListener(this);

    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ib_go_back:
                MsgSignInActivity.this.finish();
                break;
            // 获取短信验证码，获取之前验证手机号正确性
            case R.id.tv_message_code:
                if (RegularExpressionUtils.checkMobileNumber(etMobile.getText().toString())) {
                    tvMessageCode.setEnabled(false);
                    countDownTimer.start();
                } else {
                    T.showShort(MsgSignInActivity.this, "手机号输入有误！");
                }
                break;
            case R.id.btn_sign_in:
                // TODO 登录前校验验证码是否正确
                Intent intentMain = new Intent(MsgSignInActivity.this, MainActivity.class);
                startActivity(intentMain);
                MsgSignInActivity.this.finish();
                break;
            default:
                break;
        }
    }

    @Override
    public void showLogin(Object result) {

    }

    @Override
    public void setPresenter(UserContract.LoginPresenter presenter) {
        mPresenter = checkNotNull(presenter);
    }
}
