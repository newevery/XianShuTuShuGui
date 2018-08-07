package cn.com.sino_device.xianshutushugui.bean.user;

/**
 * Created by Android Studio.
 *
 * @author affe
 * @date 2018/7/16
 */
public class ResultGetUserInfo {

    private String id;

    private String createDate;

    private String updateDate;

    private String name;

    private String sex;

    private String mobile;

    private String password;

    private String photo;

    private String no;

    private String deposit;

    private String email;

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return this.id;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public String getCreateDate() {
        return this.createDate;
    }

    public void setUpdateDate(String updateDate) {
        this.updateDate = updateDate;
    }

    public String getUpdateDate() {
        return this.updateDate;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getSex() {
        return this.sex;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getMobile() {
        return this.mobile;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getPhoto() {
        return this.photo;
    }

    public void setNo(String no) {
        this.no = no;
    }

    public String getNo() {
        return this.no;
    }

    public void setDeposit(String deposit) {
        this.deposit = deposit;
    }

    public String getDeposit() {
        return this.deposit;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmail() {
        return this.email;
    }

    @Override
    public String toString() {
        return "ResultGetUserInfo{" +
                "id='" + id + '\'' +
                ", createDate='" + createDate + '\'' +
                ", updateDate='" + updateDate + '\'' +
                ", name='" + name + '\'' +
                ", sex='" + sex + '\'' +
                ", mobile='" + mobile + '\'' +
                ", password='" + password + '\'' +
                ", photo='" + photo + '\'' +
                ", no='" + no + '\'' +
                ", deposit='" + deposit + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
