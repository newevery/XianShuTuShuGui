package cn.com.sino_device.xianshutushugui.setting;

import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import cn.com.sino_device.xianshutushugui.R;
import cn.com.sino_device.xianshutushugui.util.ActivityUtils;

public class AccountSecurityActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_security);

        // 账号与安全Fragment
        AccountSecurityFragment accountSecurityFragment = new AccountSecurityFragment();
        FragmentManager fragmentManager = getSupportFragmentManager();
        ActivityUtils.addFragmentToActivity(fragmentManager, accountSecurityFragment, R.id.contentFrame);

    }

}
