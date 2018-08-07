package cn.com.sino_device.xianshutushugui.bean.user;

/**
 * Created by Android Studio.
 *
 * @author affe
 * @date 2018/7/10
 */
public class UploadPhoto {

    String mobile;
    String photo;

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    @Override
    public String toString() {
        return "{" +
                "mobile:'" + mobile + '\'' +
                ", photo:'" + photo + '\'' +
                '}';
    }
}
