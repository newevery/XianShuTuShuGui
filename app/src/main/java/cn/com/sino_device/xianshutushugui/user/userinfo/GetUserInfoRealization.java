package cn.com.sino_device.xianshutushugui.user.userinfo;

import android.os.AsyncTask;
import android.util.Log;

import com.google.gson.Gson;

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
}
