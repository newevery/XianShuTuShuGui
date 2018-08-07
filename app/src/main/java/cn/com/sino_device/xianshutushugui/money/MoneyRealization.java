package cn.com.sino_device.xianshutushugui.money;

import com.google.gson.Gson;

import cn.com.sino_device.xianshutushugui.WebSocket.CallBack;
import cn.com.sino_device.xianshutushugui.WebSocket.WebSocketInstance;
import cn.com.sino_device.xianshutushugui.bean.user.UserDeposit;
import cn.com.sino_device.xianshutushugui.bean.user.UserPay;

/**
 * Created by Android Studio.
 *
 * @author affe
 * @date 2018/6/28
 */
public class MoneyRealization implements MoneySource {

    @Override
    public void userDeposit(UserDeposit userDeposit, MoneyCallback moneyCallback) {
        WebSocketInstance.wsConnect("userDeposit", userDeposit.toString(), new CallBack() {
            @Override
            public void onSuccess(String message) {
                Gson gson = new Gson();
                ///gson.fromJson(message, xxx.class);
                ///moneyCallback.onSuccess();
            }

            @Override
            public void onError(String error) {
                moneyCallback.onError(error);
            }
        });
    }

    @Override
    public void userPay(UserPay userPay, MoneyCallback moneyCallback) {
        WebSocketInstance.wsConnect("userPay", userPay.toString(), new CallBack() {
            @Override
            public void onSuccess(String message) {
                Gson gson = new Gson();
                ///gson.fromJson(message, xxx.class);
                ///moneyCallback.onSuccess();
            }

            @Override
            public void onError(String error) {
                moneyCallback.onError(error);
            }
        });
    }
}
