package cn.com.sino_device.xianshutushugui.bean.douban;

/**
 * 豆瓣成员常用的标签
 *
 * @author affe
 */
public class Tags {
    /**
     *
     */
    private int count;

    /**
     * 标签标题
     */
    private String name;

    /**
     * 标签名称
     */
    private String title;

    public void setCount(int count) {
        this.count = count;
    }

    public int getCount() {
        return this.count;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitle() {
        return this.title;
    }

    @Override
    public String toString() {
        return "{" +
                "count:" + count +
                ", name:'" + name + '\'' +
                ", title:'" + title + '\'' +
                '}';
    }
}
