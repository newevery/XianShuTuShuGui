package cn.com.sino_device.xianshutushugui.user.signin;

import android.os.AsyncTask;

import com.google.gson.Gson;

import cn.com.sino_device.xianshutushugui.WebSocket.CallBack;
import cn.com.sino_device.xianshutushugui.WebSocket.WebSocketAsyncTask;
import cn.com.sino_device.xianshutushugui.bean.user.Result;
import cn.com.sino_device.xianshutushugui.bean.user.UserLogin;

/**
 * Created by Android Studio.
 *
 * @author affe
 * @date 2018/7/16
 */
public class SignInRealization implements SignInSource {

    @Override
    public void userSignIn(UserLogin userLogin, SignInCallback signInCallback) {
        new WebSocketAsyncTask(new CallBack() {
            @Override
            public void onSuccess(String message) {
                Gson gson = new Gson();
                Result result = gson.fromJson(message, Result.class);
                signInCallback.onSuccess(result);
            }

            @Override
            public void onError(String error) {
                signInCallback.onError(error);

            }
        }).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, "userLogin", userLogin.toString());
    }
}
