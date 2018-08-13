package cn.com.sino_device.xianshutushugui.setting;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import cn.com.sino_device.xianshutushugui.R;

/**
 * 设置
 *
 * @author affe
 */
public class SettingActivity extends AppCompatActivity implements View.OnClickListener {
    private static final String TAG = "SettingActivity";

    /**
     * 返回按钮
     */
    private ImageButton ibGoBack;

    /**
     * 账号与安全
     */
    private Button btnAccountSecurity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);

        initView();
    }

    private void initView() {
        ibGoBack = findViewById(R.id.ib_go_back);
        ibGoBack.setOnClickListener(this);
        //
        btnAccountSecurity = findViewById(R.id.btn_account_security);
        btnAccountSecurity.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ib_go_back:
                SettingActivity.this.finish();
                break;
            case R.id.btn_account_security:
                Intent intentAccountSecurity = new Intent(SettingActivity.this, AccountSecurityActivity.class);
                startActivity(intentAccountSecurity);
                break;
            default:
                break;
        }
    }
}
