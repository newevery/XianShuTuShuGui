package cn.com.sino_device.xianshutushugui.organization;

import cn.com.sino_device.xianshutushugui.bean.user.GetInfo;

/**
 * Created by Android Studio.
 *
 * @author affe
 * @date 2018/6/27
 */
public interface OrganizationSource {

    interface InformationCallback {
        /**
         * 成功
         *
         * @param success
         */
        void onSuccess(Object success);

        /**
         * 出错
         *
         * @param error
         */
        void onError(Object error);
    }

    /**
     *
     * @param getInfo
     * @param informationCallback
     */
    void getInfo(GetInfo getInfo, InformationCallback informationCallback);

}
