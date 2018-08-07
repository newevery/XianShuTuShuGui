package cn.com.sino_device.xianshutushugui.bean.douban;

import java.util.List;

/**
 * 豆瓣图书Api V2
 * 根据isbn获取图书信息
 * 请求地址：https://api.douban.com/v2/book/isbn/9787121321580
 *
 * @author affe
 */
public class DoubanBook {

    /**
     * 豆瓣评分
     */
    private Rating rating;

    /**
     * TODO
     */
    private String subtitle;

    /**
     * 作者
     */
    private List<String> author;

    /**
     * 出版年
     */
    private String pubdate;

    /**
     * 豆瓣成员常用的标签
     */
    private List<Tags> tags;

    /**
     * TODO
     */
    private String origin_title;

    /**
     * medium图书照片预览地址
     */
    private String image;

    /**
     * 装帧(精装、平装、单行、合订、普及、缩印、袖珍、特藏、豪华等等)
     */
    private String binding;

    /**
     * 译者
     */
    private List<String> translator;

    /**
     * 目录
     */
    private String catalog;

    /**
     * 页数
     */
    private String pages;

    /**
     * 图书照片预览地址(small、large、medium)
     */
    private Images images;

    /**
     * TODO
     */
    private String alt;

    /**
     * 豆瓣图书ID
     */
    private String id;

    /**
     * 出版社
     */
    private String publisher;

    /**
     * 10位ISBN
     */
    private String isbn10;

    /**
     * 13位ISBN
     */
    private String isbn13;

    /**
     * 书名
     */
    private String title;

    /**
     * TODO
     */
    private String url;

    /**
     * TODO
     */
    private String alt_title;

    /**
     * TODO
     */
    private String author_intro;

    /**
     * 内容简介
     */
    private String summary;

    /**
     * 定价
     */
    private String price;

    public void setRating(Rating rating) {
        this.rating = rating;
    }

    public Rating getRating() {
        return this.rating;
    }

    public void setSubtitle(String subtitle) {
        this.subtitle = subtitle;
    }

    public String getSubtitle() {
        return this.subtitle;
    }

    public void setAuthor(List<String> author) {
        this.author = author;
    }

    public List<String> getAuthor() {
        if (author.size()==0){
            author.add("暂无");
        }
        return this.author;
    }

    public void setPubdate(String pubdate) {
        this.pubdate = pubdate;
    }

    public String getPubdate() {
        return this.pubdate;
    }

    public void setTags(List<Tags> tags) {
        this.tags = tags;
    }

    public List<Tags> getTags() {
        return this.tags;
    }

    public void setOrigin_title(String origin_title) {
        this.origin_title = origin_title;
    }

    public String getOrigin_title() {
        return this.origin_title;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getImage() {
        return this.image;
    }

    public void setBinding(String binding) {
        this.binding = binding;
    }

    public String getBinding() {
        return this.binding;
    }

    public void setTranslator(List<String> translator) {
        this.translator = translator;
    }

    public List<String> getTranslator() {
        return this.translator;
    }

    public void setCatalog(String catalog) {
        this.catalog = catalog;
    }

    public String getCatalog() {
        return this.catalog;
    }

    public void setPages(String pages) {
        this.pages = pages;
    }

    public String getPages() {
        return this.pages;
    }

    public void setImages(Images images) {
        this.images = images;
    }

    public Images getImages() {
        return this.images;
    }

    public void setAlt(String alt) {
        this.alt = alt;
    }

    public String getAlt() {
        return this.alt;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return this.id;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public String getPublisher() {
        if (publisher==null || "".equals(publisher)){
            publisher=("暂无");
        }
        return this.publisher;
    }

    public void setIsbn10(String isbn10) {
        this.isbn10 = isbn10;
    }

    public String getIsbn10() {
        return this.isbn10;
    }

    public void setIsbn13(String isbn13) {
        this.isbn13 = isbn13;
    }

    public String getIsbn13() {
        return this.isbn13;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitle() {
        return this.title;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUrl() {
        return this.url;
    }

    public void setAlt_title(String alt_title) {
        this.alt_title = alt_title;
    }

    public String getAlt_title() {
        return this.alt_title;
    }

    public void setAuthor_intro(String author_intro) {
        this.author_intro = author_intro;
    }

    public String getAuthor_intro() {
        return this.author_intro;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getSummary() {
        if (summary==null || "".equals(summary)){
            summary=("暂无");
        }
        return this.summary;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getPrice() {
        return this.price;
    }

    @Override
    public String toString() {



        summary = summary.replace("\n", "").replace(" ","").replace("\t","").replace("\r","");
        price=price.replace("CNY ", "").replace("元", "");
        return "{" +
//                "rating:" + rating +
//                ", subtitle:'" + subtitle + '\'' +
                " author:'" + author +'\'' +
                ", pubdate:'" + pubdate + '\'' +
//                ", tags:" + tags +
//                ", origin_title:'" + origin_title + '\'' +
//               ", image:'" + image + '\'' +
//                ", binding:'" + binding + '\'' +
//                ", translator:" + translator +
//                ", catalog:'" + catalog + '\'' +
                ", pages:'" + pages + '\'' +
                ", images:" + images +
//                ", alt:'" + alt + '\'' +
//                ", id:'" + id + '\'' +
                ", publisher:'" + publisher + '\'' +
//                ", isbn10:'" + isbn10 + '\'' +
                ", isbnCode:'" + isbn13 + '\'' +
                ", name:'" + title + '\'' +
                ", url:'" + url + '\'' +
//                ", alt_title:'" + alt_title + '\'' +
//                ", author_intro:'" + author_intro + '\'' +
                ", introduction:'" + summary + '\'' +
                ", price:'" + price + '\'' +
                '}';
    }
}