package cn.com.sino_device.xianshutushugui.bean.user;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

/**
 * Created by Android Studio.
 *
 * @author affe
 * @date 2018/6/26
 */
public class ResultGetInfo {

    private String id;

    private String name;

    private int sort;

    private boolean hasChildren;

    private String code;

    private String type;

    private String parentId;

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return this.id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public void setSort(int sort) {
        this.sort = sort;
    }

    public int getSort() {
        return this.sort;
    }

    public void setHasChildren(boolean hasChildren) {
        this.hasChildren = hasChildren;
    }

    public boolean getHasChildren() {
        return this.hasChildren;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getCode() {
        return this.code;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getType() {
        return this.type;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public String getParentId() {
        return this.parentId;
    }

    public ResultGetInfo(String id, String name, int sort, boolean hasChildren, String code, String type, String parentId) {
        this.id = id;
        this.name = name;
        this.sort = sort;
        this.hasChildren = hasChildren;
        this.code = code;
        this.type = type;
        this.parentId = parentId;
    }
}
