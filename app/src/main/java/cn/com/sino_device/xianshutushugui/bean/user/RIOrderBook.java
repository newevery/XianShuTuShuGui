package cn.com.sino_device.xianshutushugui.bean.user;

/**
 * Created by Android Studio.
 * 返回结果 {@link ResultRIOrderBook}
 *
 * @author affe
 * @date 2018/6/26
 */
public class RIOrderBook {

    /**
     * 用户账号
     */
    String userId;

    /**
     * 状态 0：未审核 1：已审核
     */
    String state;

    /**
     * 预约类型 0：借书 1：还书
     */
    String type;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "{" +
                "userId:'" + userId + '\'' +
                ", state:'" + state + '\'' +
                ", type:'" + type + '\'' +
                '}';
    }
}
