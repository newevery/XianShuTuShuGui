package cn.com.sino_device.xianshutushugui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.google.gson.Gson;

import java.text.SimpleDateFormat;
import java.util.Date;

import cn.com.sino_device.xianshutushugui.WebSocket.CallBack;
import cn.com.sino_device.xianshutushugui.WebSocket.WebSocketInstance;
import cn.com.sino_device.xianshutushugui.bean.user.sign.UserLogin;


/**
 * 接口测试
 */
public class TestActivity extends AppCompatActivity {
    private static final String TAG = "TestActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);


        // 用户注册接口
//        UserRegister userRegister = new UserRegister();
//        userRegister.setName("TestActivity");
//        userRegister.setSex("男");
//        userRegister.setMobile("17012345678");
//        userRegister.setPassword("123456");
//        userRegister.setPhoto("img");
//        userRegister.setNo("07");
//        userRegister.setStu_name("TestActivity");
//        userRegister.setClasses("一年级");
//        userRegister.setDeposit("0");
//        userRegister.setEmail("admin@email.com");
//        userRegister.setLogin_ip("0.0.0.0");
//        // 2007-01-31 00:00:00
//        Date date = new Date();
//        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
//        String now = sdf.format(date);
//        userRegister.setLogin_date("2007-01-31 00:00:00");
//        userRegister.setCreate_by("self");
//        userRegister.setCreate_date(now);
//        Button buttonUserRegister = findViewById(R.id.button);
//        buttonUserRegister.setOnClickListener(v -> {
//            Thread thread = new Thread(() -> WebSocketInstance.wsConnect("userRegister", userRegister.toString(), new CallBack() {
//                @Override
//                public void onSuccess(String message) {
//                    Log.i(TAG, message);
//                }
//
//                @Override
//                public void onError(String error) {
//
//                }
//            }));
//            thread.start();
//        });


        // 用户登录接口
        UserLogin userLogin = new UserLogin();
        userLogin.setType("1");
        userLogin.setMobile("13012345678");
        userLogin.setName("王公");
        userLogin.setLogin_ip("0.0.0.0");
        // 2007-01-31 00:00:00
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        String now = sdf.format(date);
        userLogin.setLogin_date(now);
        Button buttonUserRegister = findViewById(R.id.button);
        buttonUserRegister.setOnClickListener(v -> {
            Thread thread = new Thread(() -> WebSocketInstance.wsConnect("userLogin", userLogin.toString(), new CallBack() {
                @Override
                public void onSuccess(String message) {
                    Log.i(TAG, message);
                }

                @Override
                public void onError(String error) {

                }
            }));
            thread.start();
        });


//        GetInfo getInfo = new GetInfo();
//        getInfo.setGrade("4");
//        getInfo.setParent_id("1");
//
//        Button button = findViewById(R.id.button);
//        button.setOnClickListener(v -> {
//            Thread thread = new Thread(() -> WebSocketInstance.wsConnect("getInfo", getInfo.toString(), new CallBack() {
//                @Override
//                public void onSuccess(String message) {
//                    Log.i(TAG, message);
//                    Gson gson = new Gson();
//                    ResultGetInfo resultGetInfo = gson.fromJson(message, ResultGetInfo.class);
//                    Log.i(TAG, resultGetInfo.toString());
//                }
//
//                @Override
//                public void onError(String error) {
//
//                }
//            }));
//            thread.start();
//        });

    }
}
