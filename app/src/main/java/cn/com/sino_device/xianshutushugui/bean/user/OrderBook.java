package cn.com.sino_device.xianshutushugui.bean.user;

/**
 * Created by Android Studio.
 * 返回结果 {@link Result}
 *
 * @author affe
 * @date 2018/6/26
 */
public class OrderBook {

    /**
     * 用户账号
     */
    String userId;

    /**
     * 图书id主键(可多个，“，”隔开)
     */
    String id;

    /**
     * 本数
     */
    int number;

    /**
     * 天数
     */
    int day;

    /**
     * 预约时间（时长）
     */
    String orderTime;

    /**
     * 创建预约时间
     */
    String createTime;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public String getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(String orderTime) {
        this.orderTime = orderTime;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return "{" +
                "userId:'" + userId + '\'' +
                ", id:'" + id + '\'' +
                ", number=" + number +
                ", day=" + day +
                ", orderTime:'" + orderTime + '\'' +
                ", createTime:'" + createTime + '\'' +
                '}';
    }
}
