package cn.com.sino_device.xianshutushugui.money;

import cn.com.sino_device.xianshutushugui.BasePresenter;
import cn.com.sino_device.xianshutushugui.BaseView;
import cn.com.sino_device.xianshutushugui.bean.user.Result;
import cn.com.sino_device.xianshutushugui.bean.user.UserDeposit;
import cn.com.sino_device.xianshutushugui.bean.user.UserPay;

/**
 * Created by Android Studio.
 *
 * @author affe
 * @date 2018/6/27
 */
public interface MoneyContract {

    /**
     * 用户交押金 View
     */
    interface DepositView extends BaseView<DepositPresenter> {
        void showDeposit(Result result);
    }

    /**
     * 用户充值 View
     */
    interface PayView extends BaseView<PayPresenter> {
        void showPay(Result result);
    }

    /**
     * 用户交押金 Presenter
     */
    interface DepositPresenter extends BasePresenter {
        void userDeposit(UserDeposit userDeposit);
    }
    
    /**
     * 用户充值 Presenter
     */
    interface PayPresenter extends BasePresenter {
        void userPay(UserPay userPay);
    }
}
