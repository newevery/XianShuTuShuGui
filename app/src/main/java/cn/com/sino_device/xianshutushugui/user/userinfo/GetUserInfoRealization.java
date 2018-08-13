package cn.com.sino_device.xianshutushugui.user.userinfo;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;

import com.google.gson.Gson;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import cn.com.sino_device.xianshutushugui.WebSocket.CallBack;
import cn.com.sino_device.xianshutushugui.WebSocket.WebSocketAsyncTask;
import cn.com.sino_device.xianshutushugui.bean.user.GetUserInfo;
import cn.com.sino_device.xianshutushugui.bean.user.Result;

/**
 * Created by Android Studio.
 *
 * @author affe
 * @date 2018/7/16
 */
public class GetUserInfoRealization implements GetUserInfoSource {
    private static final String TAG = "GetUserInfoRealization";

    @Override
    public void getUserInfo(GetUserInfo getUserInfo, GetUserInfoCallback getUserInfoCallback) {
        new WebSocketAsyncTask(new CallBack() {
            @Override
            public void onSuccess(String message) {
                Log.i(TAG, message);
                Gson gson = new Gson();
                Result result = gson.fromJson(message, Result.class);
                getUserInfoCallback.onSuccess(result);
            }

            @Override
            public void onError(String error) {
                getUserInfoCallback.onError(error);

            }
        }).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, "getUserInfo", getUserInfo.toString());
    }

    @Override
    public void getUserAvatar(String url, GetUserInfoCallback userInfoCallback) {
        new Thread(() -> {
            Bitmap bmp;
            try {
                URL myurl = new URL(url);
                // 获得连接
                HttpURLConnection conn = (HttpURLConnection) myurl.openConnection();
                // 设置超时
                conn.setConnectTimeout(6000);
                conn.setDoInput(true);
                // 不缓存
                conn.setUseCaches(false);
                conn.connect();
                // 获得图片的数据流
                InputStream is = conn.getInputStream();
                bmp = BitmapFactory.decodeStream(is);
                is.close();
                userInfoCallback.onSuccess(bmp);
            } catch (Exception e) {
                e.printStackTrace();
                userInfoCallback.onError(e);
            }


        }).start();
    }
}
