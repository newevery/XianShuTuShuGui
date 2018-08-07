package cn.com.sino_device.xianshutushugui.user;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import cn.com.sino_device.xianshutushugui.R;
import cn.com.sino_device.xianshutushugui.bean.user.UserRegister;
import cn.com.sino_device.xianshutushugui.util.RegularExpressionUtils;
import cn.com.sino_device.xianshutushugui.util.T;
import cn.com.sino_device.xianshutushugui.util.VerificationCode;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * 用户注册
 */
public class SignUpActivityBAKSMS extends AppCompatActivity implements UserContract.RegisterView, View.OnClickListener {
    private static final String TAG = "SignUpActivityBAKSMS";

    /**
     * 返回按钮
     */
    private ImageButton ibGoBack;
    /**
     * 手机号
     */
    private EditText etMobile;
    /**
     * 图片验证码
     */
    private EditText etImageCode;
    /**
     * 获取图片验证码
     */
    private ImageView ivImageCode;
    /**
     * 用于保存图片控件随机生成的验证码
     */
    private String imageCode;
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
     * 设置密码
     */
    private TextInputEditText tietPassword;
    /**
     * 注册
     */
    private Button btnSignUp;


    private UserPresenter mUserPresenter;
    private UserContract.RegisterPresenter mPresenter;
    private UserRegister userRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up_bak_sms);

        initView();
        mUserPresenter = new UserPresenter(this);
        ///mPresenter.userRegister();

    }

    private void initView() {
        // 返回
        ibGoBack = findViewById(R.id.ib_go_back);
        ibGoBack.setOnClickListener(this);
        // 手机号
        etMobile = findViewById(R.id.et_mobile);
        // 图片验证码
        etImageCode = findViewById(R.id.et_verification_code);
        ivImageCode = findViewById(R.id.iv_verification_code);
        ivImageCode.setOnClickListener(this);
        ivImageCode.setImageBitmap(VerificationCode.getInstance().createBitmap());
        imageCode = VerificationCode.getInstance().getCode().toLowerCase();
        // 短信验证码
        etMessageCode = findViewById(R.id.et_message_code);
        tvMessageCode = findViewById(R.id.tv_message_code);
        tvMessageCode.setOnClickListener(this);
        // 注册
        btnSignUp = findViewById(R.id.btn_sign_up);
        btnSignUp.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ib_go_back:
                SignUpActivityBAKSMS.this.finish();
                break;
            // 获取图片验证码
            case R.id.iv_verification_code:
                ivImageCode.setImageBitmap(VerificationCode.getInstance().createBitmap());
                imageCode = VerificationCode.getInstance().getCode().toLowerCase();
                break;
            // 获取短信验证码
            case R.id.tv_message_code:
                if (RegularExpressionUtils.checkMobileNumber(etMobile.getText().toString())) {
                    tvMessageCode.setEnabled(false);
                    countDownTimer.start();
                } else {
                    T.showShort(SignUpActivityBAKSMS.this, "手机号不合法");
                }
                break;
            // 注册
            case R.id.btn_sign_up:
                // TODO 用户注册
                userRegister = new UserRegister();
                mPresenter.userRegister(userRegister);
//                mPresenter.userRegister(
//                        "赵大明",
//                        "男",
//                        "13012345678",
//                        "000000",
//                        "翠花JPG",
//                        "n0",
//                        "赵小明",
//                        "一年二班",
//                        "0.0",
//                        "zdm@email.com",
//                        "192.168.0.1",
//                        "2018-05-12 11:00:02",
//                        "test",
//                        "2018-05-12 11:00:02"
//                );
                break;
            default:
                break;
        }
    }

    @Override
    public void setPresenter(UserContract.RegisterPresenter presenter) {
        mPresenter = checkNotNull(presenter);
    }

    @Override
    public void showRegister(Object result) {

    }

}
