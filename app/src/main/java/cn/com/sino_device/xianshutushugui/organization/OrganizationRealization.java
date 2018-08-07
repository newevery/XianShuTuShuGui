package cn.com.sino_device.xianshutushugui.organization;

import com.google.gson.Gson;

import cn.com.sino_device.xianshutushugui.WebSocket.CallBack;
import cn.com.sino_device.xianshutushugui.WebSocket.WebSocketInstance;
import cn.com.sino_device.xianshutushugui.bean.user.GetInfo;
import cn.com.sino_device.xianshutushugui.bean.user.ResultGetInfo;

/**
 * Created by Android Studio.
 *
 * @author affe
 * @date 2018/6/28
 */
public class OrganizationRealization implements OrganizationSource {

    @Override
    public void getInfo(GetInfo getInfo, InformationCallback informationCallback) {
        WebSocketInstance.wsConnect("getInfo", getInfo.toString(), new CallBack() {
            @Override
            public void onSuccess(String message) {
                Gson gson = new Gson();
                ResultGetInfo resultGetInfo = gson.fromJson(message, ResultGetInfo.class);
                informationCallback.onSuccess(resultGetInfo);
            }

            @Override
            public void onError(String error) {
                informationCallback.onError(error);
            }
        });
    }
}
