package cn.com.sino_device.xianshutushugui.bean.user;

/**
 * Created by Android Studio.
 * 返回结果 {@link ResultGetBooks}
 *
 * @author affe
 * @date 2018/6/26
 */
public class GetBooks {

    /**
     * 用户账号（可为空，空时返回所有图书）
     */
    String userId;

    /**
     * 图书ID
     */
    String id;

    /**
     * 上架模式（免费/计费）
     */
    String type;

    /**
     * 类别
     */
    String booktype;

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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getBooktype() {
        return booktype;
    }

    public void setBooktype(String booktype) {
        this.booktype = booktype;
    }

    @Override
    public String toString() {
        return "{" +
                "userId:'" + userId + '\'' +
                ", id:'" + id + '\'' +
                ", type:'" + type + '\'' +
                ", booktype:'" + booktype + '\'' +
                '}';
    }
}
