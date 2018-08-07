package cn.com.sino_device.xianshutushugui.bean.user;

import java.util.List;

/**
 * Created by Android Studio.
 * 组织机构信息
 *
 * @author affe
 * @date 2018/6/25
 */
public class OrganizationInfo {

    /**
     *
     */
    private boolean success;

    /**
     *
     */
    private String errorCode;

    /**
     *
     */
    private List<Msg> msg;

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public boolean getSuccess() {
        return this.success;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorCode() {
        return this.errorCode;
    }

    public void setMsg(List<Msg> msg) {
        this.msg = msg;
    }

    public List<Msg> getMsg() {
        return this.msg;
    }

    /**
     *
     */
    public class Msg {

        /**
         * 编号
         */
        private String id;

        /**
         * 名称
         */
        private String name;

        /**
         *
         */
        private int sort;

        /**
         *
         */
        private boolean hasChildren;

        /**
         *
         */
        private String code;

        /**
         *
         */
        private String type;

        /**
         * 父级编号
         */
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

    }
}
