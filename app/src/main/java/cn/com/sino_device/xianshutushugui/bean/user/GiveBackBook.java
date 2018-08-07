package cn.com.sino_device.xianshutushugui.bean.user;

/**
 * Created by Android Studio.
 * 返回结果 {@link Result}
 *
 * @author affe
 * @date 2018/6/26
 */
public class GiveBackBook {

    /**
     * 用户账号
     */
    String userId;

    /**
     * 借还书信息编号
     */
    String orderID;

    /**
     * 预约时间
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

    public String getOrderID() {
        return orderID;
    }

    public void setOrderID(String orderID) {
        this.orderID = orderID;
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
                ", orderID:'" + orderID + '\'' +
                ", orderTime:'" + orderTime + '\'' +
                ", createTime:'" + createTime + '\'' +
                '}';
    }
}
