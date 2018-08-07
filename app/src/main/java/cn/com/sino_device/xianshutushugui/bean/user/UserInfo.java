package cn.com.sino_device.xianshutushugui.bean.user;

/**
 * Created by Android Studio.
 *
 * @author affe
 * @date 2018/6/14
 */
public class UserInfo {

    /**
     * 性别
     */
    String sex;

    /**
     * 用户头像
     */
    String photo;

    /**
     * 学号
     */
    String no;

    /**
     * 学生姓名
     */
    String stu_name;

    /**
     * 所在班级
     */
    String classes;

    /**
     * 邮箱
     */
    String email;

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getNo() {
        return no;
    }

    public void setNo(String no) {
        this.no = no;
    }

    public String getStu_name() {
        return stu_name;
    }

    public void setStu_name(String stu_name) {
        this.stu_name = stu_name;
    }

    public String getClasses() {
        return classes;
    }

    public void setClasses(String classes) {
        this.classes = classes;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "{" +
                "sex:'" + sex + '\'' +
                ", photo:'" + photo + '\'' +
                ", no:'" + no + '\'' +
                ", stu_name:'" + stu_name + '\'' +
                ", classes:'" + classes + '\'' +
                ", email:'" + email + '\'' +
                '}';
    }
}
