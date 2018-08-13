package cn.com.sino_device.xianshutushugui.money;

import cn.com.sino_device.xianshutushugui.bean.money.UserDeposit;
import cn.com.sino_device.xianshutushugui.bean.money.UserPay;

/**
 * Created by Android Studio.
 *
 * @author affe
 * @date 2018/6/27
 */
public interface MoneySource {

    interface MoneyCallback {
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
     * 用户交押金
     *
     * @param userDeposit
     * @param moneyCallback
     */
    void userDeposit(UserDeposit userDeposit, MoneyCallback moneyCallback);

    /**
     * 用户充值
     *
     * @param userPay
     * @param moneyCallback
     */
    void userPay(UserPay userPay, MoneyCallback moneyCallback);

}
