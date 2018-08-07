package cn.com.sino_device.xianshutushugui.bean.user;

/**
 * Created by Android Studio.
 * Date: 2018/5/14
 * Time: 18:40
 *
 * @author affe
 */
public class UserLogin {

    /**
     * 登录方式
     * 0：用户名&密码登录
     * 1：手机&验证码登陆
     */
    private String type;

    /**
     * 用户名(可空)
     */
    private String name;

    /**
     * 手机(可空)
     */
    private String mobile;

    /**
     * 密码
     */
    private String password;

    /**
     * 登陆IP
     */
    private String login_ip;

    /**
     * 登陆时间
     */
    private String login_date;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

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

    public String getLogin_ip() {
        return login_ip;
    }

    public void setLogin_ip(String login_ip) {
        this.login_ip = login_ip;
    }

    public String getLogin_date() {
        return login_date;
    }

    public void setLogin_date(String login_date) {
        this.login_date = login_date;
    }

    @Override
    public String toString() {
        return "{" +
                "type:'" + type + '\'' +
                ", name:'" + name + '\'' +
                ", mobile:'" + mobile + '\'' +
                ", password:'" + password + '\'' +
                ", login_ip:'" + login_ip + '\'' +
                ", login_date:'" + login_date + '\'' +
                '}';
    }
}
