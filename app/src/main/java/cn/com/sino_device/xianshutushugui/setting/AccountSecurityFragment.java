package cn.com.sino_device.xianshutushugui.setting;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.RelativeLayout;

import cn.com.sino_device.xianshutushugui.R;
import cn.com.sino_device.xianshutushugui.util.ActivityUtils;

/**
 * A simple {@link Fragment} subclass.
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
        switch (v.getId()) {
            case R.id.ib_go_back:
                this.getActivity().finish();
                break;
            case R.id.rl_mobile:
                break;
            case R.id.rl_password:
                SecurityCheckFragment securityCheckFragment = new SecurityCheckFragment();
                FragmentManager fragmentManager = this.getActivity().getSupportFragmentManager();
                ActivityUtils.replaceFragmentIntoActivity(fragmentManager, securityCheckFragment, R.id.contentFrame);
                break;
            default:
                break;
        }

    }
}
