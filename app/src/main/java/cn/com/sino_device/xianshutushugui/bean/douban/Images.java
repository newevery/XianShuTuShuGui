package cn.com.sino_device.xianshutushugui.bean.douban;

/**
 * 图书照片预览地址
 *
 * @author affe
 */
public class Images {

    /**
     * 小图
     */
    private String small;

    /**
     * 大图
     */
    private String large;

    /**
     * 适中
     */
    private String medium;

    public void setSmall(String small) {
        this.small = small;
    }

    public String getSmall() {
        return this.small;
    }

    public void setLarge(String large) {
        this.large = large;
    }

    public String getLarge() {
        return this.large;
    }

    public void setMedium(String medium) {
        this.medium = medium;
    }

    public String getMedium() {
        return this.medium;
    }

    @Override
    public String toString() {
        return "{" +
                "small:'" + small + '\'' +
                ", large:'" + large + '\'' +
                ", medium:'" + medium + '\'' +
                '}';
    }
}
