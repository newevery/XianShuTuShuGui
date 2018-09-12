package cn.com.sino_device.xianshutushugui.book;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import cn.com.sino_device.xianshutushugui.R;

public class MyMsgActivity extends AppCompatActivity {
    private ImageButton ibGoBack;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_msg);
        ibGoBack = findViewById(R.id.ib_go_back);
        ibGoBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MyMsgActivity.this.finish();
            }
        });
    }
}
