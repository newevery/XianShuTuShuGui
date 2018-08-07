package cn.com.sino_device.xianshutushugui.user;

import android.os.AsyncTask;

import com.google.gson.Gson;

import cn.com.sino_device.xianshutushugui.WebSocket.CallBack;
import cn.com.sino_device.xianshutushugui.WebSocket.WebSocketAsyncTask;
import cn.com.sino_device.xianshutushugui.WebSocket.WebSocketInstance;
import cn.com.sino_device.xianshutushugui.bean.user.GetUserInfo;
import cn.com.sino_device.xianshutushugui.bean.user.Result;
import cn.com.sino_device.xianshutushugui.bean.user.AlterUserInfo;
import cn.com.sino_device.xianshutushugui.bean.user.UserLogin;
import cn.com.sino_device.xianshutushugui.bean.user.AlterUserPwd;
import cn.com.sino_device.xianshutushugui.bean.user.UserRegister;

/**
 * Created by Android Studio.
 *
 * @author affe
 * @date 2018/6/22
 */
public class UserRealization implements UserSource {

    @Override
    public void userRegister(UserRegister userRegister, UserCallback userCallback) {
        new WebSocketAsyncTask(new CallBack() {
            @Override
            public void onSuccess(String message) {
                Gson gson = new Gson();
                Result result = gson.fromJson(message, Result.class);
                userCallback.onSuccess(result);
            }

            @Override
            public void onError(String error) {
                userCallback.onError(error);

            }
        }).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, "userRegister", userRegister.toString());
    }

    @Override
    public void userLogin(UserLogin userLogin, UserCallback userCallback) {
        new WebSocketAsyncTask(new CallBack() {
            @Override
            public void onSuccess(String message) {
                Gson gson = new Gson();
                Result result = gson.fromJson(message, Result.class);
                userCallback.onSuccess(result);
            }

            @Override
            public void onError(String error) {
                userCallback.onError(error);

            }
        }).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, "userLogin", userLogin.toString());
    }

    @Override
    public void alterUserInfo(AlterUserInfo alterUserInfo, UserCallback userCallback) {
        new WebSocketAsyncTask(new CallBack() {
            @Override
            public void onSuccess(String message) {
                Gson gson = new Gson();
                Result result = gson.fromJson(message, Result.class);
                userCallback.onSuccess(result);
            }

            @Override
            public void onError(String error) {
                userCallback.onError(error);

            }
        }).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, "alterUserInfo", alterUserInfo.toString());
    }

    @Override
    public void alterUserPwd(AlterUserPwd alterUserPwd, UserCallback userCallback) {
        new WebSocketAsyncTask(new CallBack() {
            @Override
            public void onSuccess(String message) {
                Gson gson = new Gson();
                Result result = gson.fromJson(message, Result.class);
                userCallback.onSuccess(result);
            }

            @Override
            public void onError(String error) {
                userCallback.onError(error);

            }
        }).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, "alterUserPwd", alterUserPwd.toString());
    }

    @Override
    public void getUserInfo(GetUserInfo getUserInfo, UserCallback userCallback) {

    }
}
