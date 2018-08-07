package cn.com.sino_device.xianshutushugui.bean.user;

/**
 * Created by Android Studio.
 * 返回结果 {@link Result}
 *
 * @author affe
 * @date 2018/6/26
 */
public class DonateBook {

    /**
     * 用户ID
     */
    String userId;

    /**
     * 图书ID
     */
    String id;

    /**
     * 定价
     */
    double price;

    /**
     * 上架模式
     */
    String type;

    /**
     * 计费金额/天
     */
    double cost;

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

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    @Override
    public String toString() {
        return "{" +
                "userId:'" + userId + '\'' +
                ", id:'" + id + '\'' +
                ", price:'" + price +
                ", type:'" + type + '\'' +
                ", cost:'" + cost +
                '}';
    }
}
