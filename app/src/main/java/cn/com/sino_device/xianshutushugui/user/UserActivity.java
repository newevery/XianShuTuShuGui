package cn.com.sino_device.xianshutushugui.user;

import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.google.gson.Gson;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import cn.com.sino_device.xianshutushugui.R;
import cn.com.sino_device.xianshutushugui.WebSocket.CallBack;
import cn.com.sino_device.xianshutushugui.WebSocket.JsonUtil;
import cn.com.sino_device.xianshutushugui.WebSocket.WebSocketAsyncTask;
import cn.com.sino_device.xianshutushugui.bean.user.ResultGetUserInfo;
import cn.com.sino_device.xianshutushugui.book.BookBean;
import cn.com.sino_device.xianshutushugui.book.OrderBookBean;
import cn.com.sino_device.xianshutushugui.book.Result;
import cn.com.sino_device.xianshutushugui.user.edit.EditUserinfoActivity;
import cn.com.sino_device.xianshutushugui.util.SPUtils;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * 个人中心
 *
 * @author affe
 */
public class UserActivity extends AppCompatActivity implements View.OnClickListener {
    private String TAG = "UserActivity";
    /**
     * 返回按钮
     */
    private ImageButton ibGoBack;
    /**
     * 编辑资料按钮
     */
    private TextView tvEditInfo;
    /**
     * 用户头像
     */
    private CircleImageView civAvatar;
    /**
     * 用户昵称
     */
    private TextView tvNickname;
    /**
     * 用户性别
     */
    private ImageView ivSex;
    /**
     * 用户摘要信息
     */
    private TextView tvAbstract;
    private EditText etUserName;
    private RadioGroup rgSex;
    private EditText etMobile;
    private EditText etNO;
    private EditText etStuName;
    private TextView tvChooseClass;


    Handler handler = new Handler() {
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 1:
                    String userInfo = (String) msg.obj;
                    ResultGetUserInfo resultGetUserInfo = new Gson().fromJson(userInfo, ResultGetUserInfo.class);

//                    ivSex = findViewById(R.id.iv_sex);
//        tvAbstract = findViewById(R.id.tv_abstract);
                    etUserName.setText(resultGetUserInfo.getName());
                    etMobile.setText(resultGetUserInfo.getMobile());
                    etNO.setText(resultGetUserInfo.getNo());

                    etStuName.setText(resultGetUserInfo.getStuName());
                    tvChooseClass.setText(resultGetUserInfo.getClasses());
                    if ("男".equals(resultGetUserInfo.getSex())) {
                        rgSex.check(R.id.rb_man);
                    } else {
                        rgSex.check(R.id.rb_woman);
                    }

                    break;
            }
            super.handleMessage(msg);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);

        initView();
        initData();
    }

    private void initView() {
        ibGoBack = findViewById(R.id.ib_go_back);
        ibGoBack.setOnClickListener(this);
        tvEditInfo = findViewById(R.id.tv_edit_info);
        tvEditInfo.setOnClickListener(this);
        civAvatar = findViewById(R.id.civ_avatar);
        civAvatar.setOnClickListener(this);
        civAvatar.setImageURI(Uri.fromFile(new File(getFilesDir(), "avatar_current.jpg")));
//        tvNickname = findViewById(R.id.tv_nickname);

        etUserName = findViewById(R.id.et_username);
        rgSex = findViewById(R.id.rg_sex);
        etMobile = findViewById(R.id.et_mobile);
        etNO = findViewById(R.id.et_no);
        etStuName = findViewById(R.id.et_stu_name);
        tvChooseClass = findViewById(R.id.tv_choose_classes);


    }

    /**
     * 初始化数据
     */
    protected void initData() {
        final Map<String, String> map = new HashMap<>();
        String mobile = SPUtils.get(this.getApplicationContext(), "CURRENT_USER", "").toString();
        if (!"".equals(mobile)) {
            map.put("mobile", mobile);
        }
        map.put("null", "");
        new WebSocketAsyncTask(new CallBack() {
            @Override
            public void onSuccess(String message) {
                Gson gson = new Gson();
                Result result = gson.fromJson(message, Result.class);
                if (result.isSuccess()) {
                    Log.i(TAG + map.toString(), result.getMsg());
                    Message msg = new Message();
                    msg.obj = result.getMsg();
                    msg.what = 1;
                    handler.sendMessage(msg);
                }
            }

            @Override
            public void onError(String error) {

            }
        }).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, "getUserInfo", JsonUtil.mapToJson(map));

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ib_go_back:
                UserActivity.this.finish();
                break;
            case R.id.tv_edit_info:
                Intent intent = new Intent(UserActivity.this, EditUserinfoActivity.class);
                startActivity(intent);
                break;
//            case R.id.civ_avatar:
//                // TODO 修改头像
//                break;
            default:
                break;
        }
    }
}
