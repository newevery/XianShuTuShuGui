package cn.com.sino_device.xianshutushugui;

import android.app.Application;
import android.content.Context;
import android.support.multidex.MultiDex;

import com.tencent.mm.opensdk.openapi.IWXAPI;

import cn.com.sino_device.xianshutushugui.util.ScreenUtil;
import cn.com.sino_device.xianshutushugui.wxapi.WXEntryActivity;
import cn.jpush.android.api.JPushInterface;

public class App extends Application {

    private static App sInstance;

    /**
     * IWXAPI 是第三方app和微信通信的openapi接口
     */
    public static IWXAPI api;

    @Override
    public void onCreate() {
        super.onCreate();
        ScreenUtil.resetDensity(this);

        JPushInterface.setDebugMode(true);
        JPushInterface.init(this);

        sInstance = this;

        // 在这里为应用设置异常处理，然后程序才能获取未处理的异常
        CrashHandler crashHandler = CrashHandler.getInstance();
        crashHandler.init(this);

        // OpenWeChat
        api = WXEntryActivity.initOpenWeChat(this, GlobalConsts.OPEN_WECHAT_APP_ID);
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }

    public static App getInstance() {
        return sInstance;
    }
}
