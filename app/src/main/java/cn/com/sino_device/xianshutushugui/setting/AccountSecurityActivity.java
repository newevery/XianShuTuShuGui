package cn.com.sino_device.xianshutushugui.setting;

import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import cn.com.sino_device.xianshutushugui.R;
import cn.com.sino_device.xianshutushugui.base.BaseActivity;
import cn.com.sino_device.xianshutushugui.util.ActivityUtils;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * 账号与安全
 *
 * @author affe
 */
public class AccountSecurityActivity extends BaseActivity {
    private static final String TAG = "AccountSecurityActivity";

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
