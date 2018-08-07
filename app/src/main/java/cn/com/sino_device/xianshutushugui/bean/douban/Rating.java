package cn.com.sino_device.xianshutushugui.bean.douban;

/**
 * 豆瓣评分
 *
 * @author affe
 */
public class Rating {

    /**
     *
     */
    private int max;

    /**
     * 评分人数
     */
    private int numRaters;

    /**
     * 平均分
     */
    private String average;

    /**
     *
     */
    private int min;

    public void setMax(int max) {
        this.max = max;
    }

    public int getMax() {
        return this.max;
    }

    public void setNumRaters(int numRaters) {
        this.numRaters = numRaters;
    }

    public int getNumRaters() {
        return this.numRaters;
    }

    public void setAverage(String average) {
        this.average = average;
    }

    public String getAverage() {
        return this.average;
    }

    public void setMin(int min) {
        this.min = min;
    }

    public int getMin() {
        return this.min;
    }

    @Override
    public String toString() {
        return "{" +
                "max:" + max +
                ", numRaters:" + numRaters +
                ", average:'" + average + '\'' +
                ", min:" + min +
                '}';
    }
}