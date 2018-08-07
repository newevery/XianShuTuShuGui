package cn.com.sino_device.xianshutushugui.setting;


import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import cn.com.sino_device.xianshutushugui.R;
import cn.com.sino_device.xianshutushugui.util.ActivityUtils;

/**
 * A simple {@link Fragment} subclass.
 */
public class SecurityCheckFragment extends Fragment implements View.OnClickListener {


    /**
     * 返回按钮
     */
    private ImageButton ibGoBack;
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
     * 下一步
     */
    private Button btnNext;

    public SecurityCheckFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_security_check, container, false);

        initView(view);

        return view;
    }

    private void initView(View view) {
        ibGoBack = view.findViewById(R.id.ib_go_back);
        ibGoBack.setOnClickListener(this);
        //
        // 短信验证码
        etMessageCode = view.findViewById(R.id.et_message_code);
        tvMessageCode = view.findViewById(R.id.tv_message_code);
        tvMessageCode.setOnClickListener(this);
        //
        btnNext = view.findViewById(R.id.btn_next);
        btnNext.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        // FragmentManager
        FragmentManager fragmentManager = this.getActivity().getSupportFragmentManager();
        switch (v.getId()) {
            // 返回到 AccountSecurityFragment
            case R.id.ib_go_back:
                AccountSecurityFragment accountSecurityFragment = new AccountSecurityFragment();
                ActivityUtils.replaceFragmentIntoActivity(fragmentManager, accountSecurityFragment, R.id.contentFrame);
                break;
            // 获取短信验证码
            case R.id.tv_message_code:
                tvMessageCode.setEnabled(false);
                countDownTimer.start();
                break;
            // 跳转到
            case R.id.btn_next:
                PasswordFragment passwordFragment = new PasswordFragment();
                ActivityUtils.replaceFragmentIntoActivity(fragmentManager, passwordFragment, R.id.contentFrame);
                break;
            default:
                break;
        }

    }
}
