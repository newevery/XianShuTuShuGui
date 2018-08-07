package cn.com.sino_device.xianshutushugui.bean;

/**
 * Created by Android Studio.
 * Date: 2018/5/4
 * Time: 10:58
 *
 * 这是为了用于开发测试而写的一个类
 *
 * @author affe
 */
public class Book {

    /**
     * 图书照片预览地址
     */
    private String image;

    /**
     * 书名
     */
    private String title;

    /**
     * 作者
     */
    ///private List<String> author;
    private String author;

    /**
     * 出版社
     */
    private String publisher;

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    /*    public List<String> getAuthor() {
        return author;
    }

    public void setAuthor(List<String> author) {
        this.author = author;
    }*/

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }
}
