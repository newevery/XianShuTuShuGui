package cn.com.sino_device.xianshutushugui.bean.user;

/**
 * Created by Android Studio.
 *
 * @author affe
 * @date 2018/6/26
 */
public class GetBookType {

    /**
     * 类目名称
     */
    String name;

    /**
     * 键值
     */
    String value;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "{" +
                "name:'" + name + '\'' +
                ", value:'" + value + '\'' +
                '}';
    }
}
