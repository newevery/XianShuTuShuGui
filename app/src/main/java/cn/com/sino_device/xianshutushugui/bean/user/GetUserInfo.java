package cn.com.sino_device.xianshutushugui.bean.user;

/**
 * Created by Android Studio.
 * 返回结果 {@link ResultGetUserInfo}
 *
 * @author affe
 * @date 2018/6/26
 */
public class GetUserInfo {

    String mobile;

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    @Override
    public String toString() {
        return "{" +
                "mobile:'" + mobile + '\'' +
                '}';
    }
}
