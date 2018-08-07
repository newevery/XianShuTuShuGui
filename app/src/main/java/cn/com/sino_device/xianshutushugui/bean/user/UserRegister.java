package cn.com.sino_device.xianshutushugui.bean.user;

/**
 * Created by Android Studio.
 * User: affe
 * Date: 2018/5/7
 * Time: 15:30
 * <p>
 * 用户注册接口
 * 请求参数：见下
 * 返回：
 * 参数名称        格式        说明
 * responsenum    string     返回码：0成功 1失败，当前用户已存在
 * responsemsg    String     返回说明：失败时返回失败原因
 *
 * @author affe
 */
public class UserRegister {

    /**
     * 用户名
     */
    private String name;

    /**
     * 性别
     */
    private String sex;

    /**
     * 手机
     */
    private String mobile;

    /**
     * 密码
     */
    private String password;

    /**
     * 用户头像
     */
    private String photo;

    /**
     * 学号
     */
    private String no;

    /**
     * 学生姓名
     */
    private String stu_name;

    /**
     * 所在班级
     */
    private String classes;

    /**
     * 押金
     */
    private String deposit;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 登陆IP
     */
    private String login_ip;

    /**
     * 登陆时间
     */
    private String login_date;

    /**
     * 创建者（self: 本人）
     */
    private String create_by;

    /**
     * 创建时间
     */
    private String create_date;

    public void setName(String name) {
        this.name = name;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public void setNo(String no) {
        this.no = no;
    }

    public void setStu_name(String stu_name) {
        this.stu_name = stu_name;
    }

    public void setClasses(String classes) {
        this.classes = classes;
    }

    public void setDeposit(String deposit) {
        this.deposit = deposit;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setLogin_ip(String login_ip) {
        this.login_ip = login_ip;
    }

    public void setLogin_date(String login_date) {
        this.login_date = login_date;
    }

    public void setCreate_by(String create_by) {
        this.create_by = create_by;
    }

    public void setCreate_date(String create_date) {
        this.create_date = create_date;
    }

    @Override
    public String toString() {
        return "{" +
                "name:'" + name + '\'' +
                ", sex:'" + sex + '\'' +
                ", mobile:'" + mobile + '\'' +
                ", password:'" + password + '\'' +
                ", photo:'" + photo + '\'' +
                ", no:'" + no + '\'' +
                ", stu_name:'" + stu_name + '\'' +
                ", classes:'" + classes + '\'' +
                ", deposit:'" + deposit + '\'' +
                ", email:'" + email + '\'' +
                ", login_ip:'" + login_ip + '\'' +
                ", login_date:'" + login_date + '\'' +
                ", create_by:'" + create_by + '\'' +
                ", create_date:'" + create_date + '\'' +
                '}';
    }
}
