package cn.com.sino_device.xianshutushugui.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Android Studio.
 *
 * @author affe
 * @date 2018/6/11
 */
public class RegularExpressionUtils {

    public RegularExpressionUtils() {
        /* cannot be instantiated */
        throw new UnsupportedOperationException("cannot be instantiated");
    }


    /**
     * 校验手机号是否合法
     *
     * @param string
     * @return
     */
    public static boolean checkMobileNumber(String string) {
        // 匹配规则
        String pattern = "^(13[0-9]|14[01,4-9]|15[0-3,5-9]|16[6]|17[01,3-8]|18[0-9]|19[89])\\d{8}$";
        Pattern r = Pattern.compile(pattern);
        Matcher m = r.matcher(string);
        return m.find();

    }
}
