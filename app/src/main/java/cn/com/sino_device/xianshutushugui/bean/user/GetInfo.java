package cn.com.sino_device.xianshutushugui.bean.user;

/**
 * Created by Android Studio.
 * 组织机构
 *
 * @author affe
 * @date 2018/6/26
 */
public class GetInfo {

    /**
     * 机构等级 1：学校 2：科室（管理员层面） 3：年级 4：班级
     */
    String grade;

    /**
     * 父级编号（可空）
     */
    String parent_id;

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public String getParent_id() {
        return parent_id;
    }

    public void setParent_id(String parent_id) {
        this.parent_id = parent_id;
    }

    @Override
    public String toString() {
        return "{" +
                "grade:'" + grade + '\'' +
                ", parent_id:'" + parent_id + '\'' +
                '}';
    }
}
