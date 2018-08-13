package cn.com.sino_device.xianshutushugui.setting;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.RelativeLayout;

import cn.com.sino_device.xianshutushugui.R;
import cn.com.sino_device.xianshutushugui.setting.mobile.MobileFragment;
import cn.com.sino_device.xianshutushugui.setting.password.SecurityCheckFragment;
import cn.com.sino_device.xianshutushugui.util.ActivityUtils;

/**
 * 账号与安全
 *
 * @author affe
 */
public class AccountSecurityFragment extends Fragment implements View.OnClickListener {

    /**
     * 返回按钮
     */
    private ImageButton ibGoBack;

    /**
     * 修改手机号
     */
    private RelativeLayout rlMobile;

    /**
     * 密码设置
     */
    private RelativeLayout rlPassword;


    public AccountSecurityFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_account_security, container, false);

        initView(view);

        return view;
    }

    private void initView(View view) {
        ibGoBack = view.findViewById(R.id.ib_go_back);
        ibGoBack.setOnClickListener(this);
        //
        rlMobile = view.findViewById(R.id.rl_mobile);
        rlMobile.setOnClickListener(this);
        rlPassword = view.findViewById(R.id.rl_password);
        rlPassword.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        // FragmentManager
        FragmentManager fragmentManager = this.getActivity().getSupportFragmentManager();
        switch (v.getId()) {
            case R.id.ib_go_back:
                this.getActivity().finish();
                break;
            case R.id.rl_mobile:
                MobileFragment mobileFragment = new MobileFragment();
                ActivityUtils.replaceFragmentIntoActivity(fragmentManager, mobileFragment, R.id.contentFrame);
                break;
            case R.id.rl_password:
                SecurityCheckFragment securityCheckFragment = new SecurityCheckFragment();
                ActivityUtils.replaceFragmentIntoActivity(fragmentManager, securityCheckFragment, R.id.contentFrame);
                break;
            default:
                break;
        }

    }
}
