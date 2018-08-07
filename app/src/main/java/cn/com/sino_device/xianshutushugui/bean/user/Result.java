package cn.com.sino_device.xianshutushugui.bean.user;

/**
 * Created by Android Studio.
 *
 * @author affe
 * @date 2018/6/14
 */
public class Result {

    /**
     * 是否成功
     */
    boolean success;

    /**
     * 错误代码 0成功 1失败
     */
    String errorCode;

    /**
     * 提示信息
     */
    String msg;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    @Override
    public String toString() {
        return "Result{" +
                "success=" + success +
                ", errorCode='" + errorCode + '\'' +
                ", msg='" + msg + '\'' +
                '}';
    }
}
