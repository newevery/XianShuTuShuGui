package cn.com.sino_device.xianshutushugui.user.signup;

import android.os.AsyncTask;

import com.google.gson.Gson;

import cn.com.sino_device.xianshutushugui.WebSocket.CallBack;
import cn.com.sino_device.xianshutushugui.WebSocket.WebSocketAsyncTask;
import cn.com.sino_device.xianshutushugui.bean.user.Result;
import cn.com.sino_device.xianshutushugui.bean.user.UserRegister;

/**
 * Created by Android Studio.
 *
 * @author affe
 * @date 2018/7/16
 */
public class SignUpRealization implements SignUpSource {

    @Override
    public void userSignUp(UserRegister userRegister, SignUpCallback signUpCallback) {
        new WebSocketAsyncTask(new CallBack() {
            @Override
            public void onSuccess(String message) {
                Gson gson = new Gson();
                Result result = gson.fromJson(message, Result.class);
                signUpCallback.onSuccess(result);
            }

            @Override
            public void onError(String error) {
                signUpCallback.onError(error);

            }
        }).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, "userRegister", userRegister.toString());
    }
}
