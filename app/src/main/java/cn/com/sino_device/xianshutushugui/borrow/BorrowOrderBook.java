package cn.com.sino_device.xianshutushugui.borrow;

public class BorrowOrderBook {

    private String id;
    private String author;
    private String pubdate;
    private String unit;
    private String name;
    private String photo1;
    private String introduction;
    private String price;
    private String level;
    private String type;
    private String pages;
    private String isbnCode;
    private String state;

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    private String mobile;
    private String catalog;
    private String cost;
    private String isCollect;

    public String getIsCollect() {
        return isCollect;
    }

    public void setIsCollect(String isCollect) {
        this.isCollect = isCollect;
    }

    public String getPubdate() {
        return pubdate;
    }

    public void setPubdate(String pubdate) {
        this.pubdate = pubdate;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getCatalog() {
        return catalog;
    }

    public void setCatalog(String catalog) {
        this.catalog = catalog;
    }

    public String getCost() {
        return cost;
    }

    public void setCost(String cost) {
        this.cost = cost;
    }

    public void setId(String id) {
        this.id = id;
    }
    public String getId() {
        return id;
    }

    public void setAuthor(String author) {
        this.author = author;
    }
    public String getAuthor() {
        return author;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }
    public String getUnit() {
        return unit;
    }

    public void setName(String name) {
        this.name = name;
    }
    public String getName() {
        return name;
    }

    public void setPhoto1(String photo1) {
        this.photo1 = photo1;
    }
    public String getPhoto1() {
        return photo1;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }
    public String getIntroduction() {
        return introduction;
    }

    public String getPrice() {
        return price;
    }

    public void setLevel(String level) {
        this.level = level;
    }
    public String getLevel() {
        return level;
    }

    public void setType(String type) {
        this.type = type;
    }
    public String getType() {
        return type;
    }

    public void setPages(String pages) {
        this.pages = pages;
    }
    public String getPages() {
        return pages;
    }

    public void setIsbnCode(String isbnCode) {
        this.isbnCode = isbnCode;
    }
    public String getIsbnCode() {
        return isbnCode;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }
    public String getMobile() {
        return mobile;
    }
}