package cn.com.sino_device.xianshutushugui.setting;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import cn.com.sino_device.xianshutushugui.R;
import cn.com.sino_device.xianshutushugui.util.ActivityUtils;

/**
 * A simple {@link Fragment} subclass.
 */
public class PasswordFragment extends Fragment implements View.OnClickListener {

    /**
     * 返回按钮
     */
    private ImageButton ibGoBack;

    public PasswordFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_password, container, false);

        initView(view);

        return view;
    }

    private void initView(View view) {
        ibGoBack = view.findViewById(R.id.ib_go_back);
        ibGoBack.setOnClickListener(this);
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
            // 确认
            case R.id.btn_confirm:
                break;
            default:
                break;
        }
    }
}
