package cn.com.sino_device.xianshutushugui.bean.user;

/**
 * Created by Android Studio.
 *
 * @author affe
 * @date 2018/6/14
 */
public class AlterUserPwd {

    /**
     * 用户名(可空)
     */
    String name;

    /**
     * 手机
     */
    String mobile;

    /**
     * 密码
     */
    String password;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "{" +
                "name:'" + name + '\'' +
                ", mobile:'" + mobile + '\'' +
                ", password:'" + password + '\'' +
                '}';
    }
}
