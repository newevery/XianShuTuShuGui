package cn.com.sino_device.xianshutushugui.util;

import com.google.gson.Gson;

import cn.com.sino_device.xianshutushugui.bean.IP;

import static cn.com.sino_device.xianshutushugui.GlobalConsts.IP_INFO_URL;

/**
 * Created by Android Studio.
 *
 * @author affe
 * @date 2018/6/20
 */
public class IPInfoUtil {

    public IPInfoUtil() {
        /* cannot be instantiated */
        throw new UnsupportedOperationException("cannot be instantiated");
    }

    public static IP getIPInfo() {
        HttpRequest httpRequest = new HttpRequest();
        String requestResult = httpRequest.sendGet(IP_INFO_URL, null);
        Gson gson = new Gson();
        IP ip = gson.fromJson(requestResult, IP.class);
        return ip;
    }

}
