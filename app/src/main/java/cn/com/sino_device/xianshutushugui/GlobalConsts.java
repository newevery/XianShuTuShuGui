package cn.com.sino_device.xianshutushugui;

/**
 * @author affe
 */
public class GlobalConsts {

    /**
     * 服务请求地址
     */
    public static final String BASE_URL = "http://123.207.181.101/";

    /**
     * 获取公网IP请求地址
     */
    public static final String IP_INFO_URL = "http://wolongbridge.com/developer/getIpInfo.php";

    /**
     * 最短密码长度
     */
    public static final int PASSWORD_MIN_LENGTH = 6;
    /**
     * 最大密码长度
     */
    public static final int PASSWORD_MAX_LENGTH = 18;

    /**
     * Toast提示信息：登录成功
     */
    public static final String SIGN_IN_SUCCESS = "登录成功";

    /**
     * 错误码 0
     * 请求成功、正确
     */
    public static final String ERROR_CODE_SUCCESS = "0";

    /**
     * 组织机构类型
     */
    public static final String ORGANIZATION_TYPE_SCHOOL = "1";
    public static final String ORGANIZATION_TYPE_OFFICE = "2";
    public static final String ORGANIZATION_TYPE_GRADE = "3";
    public static final String ORGANIZATION_TYPE_CLAZZ = "4";

    /**
     * 微信开放平台 APP_ID
     */
    public static final String OPEN_WECHAT_APP_ID = "wx302443bf2da7b5ab";
    /**
     * 微信开放平台 SECRET
     */
    public static final String OPEN_WECHAT_SECRET = "26305f7a5c78d7b3bf722e321c25946a";

}