package cn.com.sino_device.xianshutushugui.util;

import com.github.qcloudsms.SmsSingleSender;
import com.github.qcloudsms.SmsSingleSenderResult;
import com.github.qcloudsms.httpclient.HTTPException;

import org.json.JSONException;

import java.io.IOException;

/**
 * Created by Android Studio.
 *
 * @author affe
 * @date 2018/6/12
 */
public class QCloudSMSUtil {

    public QCloudSMSUtil() {
        /* cannot be instantiated */
        throw new UnsupportedOperationException("cannot be instantiated");
    }

    /**
     * 短信应用SDK AppID
     * 1400开头
     */
    static int appid = 1400095984;

    /**
     * 短信应用SDK AppKey
     */
    static String appkey = "cdb7dc952496d5b20ab95eaa0950d84a";

    /**
     * 需要发送短信的手机号码
     */
    //String[] phoneNumbers = {"21212313123", "12345678902", "12345678903"};

    /**
     * 短信模板ID，需要在短信应用中申请
     * NOTE: 这里的模板ID`7839`只是一个示例，真实的模板ID需要在短信控制台中申请
     */
    int templateId = 7839;

    /**
     * 签名
     * NOTE: 这里的签名"腾讯云"只是一个示例，真实的签名需要在短信控制台中申请，
     * 另外签名参数使用的是`签名内容`，而不是`签名ID`
     */
    String smsSign = "闲书科技";


    public static void sendmessage(String phoneNumber) {
        String code = String.valueOf((int) ((Math.random() * 9 + 1) * 1000));
        try {
            SmsSingleSender ssender = new SmsSingleSender(appid, appkey);
            SmsSingleSenderResult result = ssender.send(0, "86", phoneNumber,
                    "【腾讯云】您的验证码是：" + code, "", "");
            System.out.print(result);
        } catch (HTTPException e) {
            // HTTP响应码错误
            e.printStackTrace();
        } catch (JSONException e) {
            // json解析错误
            e.printStackTrace();
        } catch (IOException e) {
            // 网络IO错误
            e.printStackTrace();
        }
    }


}
