package cn.com.sino_device.xianshutushugui.money;

import cn.com.sino_device.xianshutushugui.bean.user.Result;
import cn.com.sino_device.xianshutushugui.bean.user.UserDeposit;
import cn.com.sino_device.xianshutushugui.bean.user.UserPay;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by Android Studio.
 *
 * @author affe
 * @date 2018/6/27
 */
public class MoneyPresenter implements MoneyContract.DepositPresenter, MoneyContract.PayPresenter {
    private static final String TAG = "MoneyPresenter";

    private MoneySource moneySource;

    private MoneyContract.DepositView mDepositView;
    private MoneyContract.PayView mPayView;

    public MoneyPresenter(MoneyContract.DepositView depositView) {
        this.moneySource = new MoneyRealization();
        mDepositView = checkNotNull(depositView, "depositView cannot be null!");
        mDepositView.setPresenter(this);
    }

    public MoneyPresenter(MoneyContract.PayView payView) {
        this.moneySource = new MoneyRealization();
        mPayView = checkNotNull(payView, "payView cannot be null!");
        mPayView.setPresenter(this);

    }

    @Override
    public void start() {

    }

    @Override
    public void userDeposit(UserDeposit userDeposit) {
        moneySource.userDeposit(userDeposit, new MoneySource.MoneyCallback() {
            @Override
            public void onSuccess(Object success) {
                mDepositView.showDeposit((Result) success);
            }

            @Override
            public void onError(Object error) {

            }
        });
    }

    @Override
    public void userPay(UserPay userPay) {
        moneySource.userPay(userPay, new MoneySource.MoneyCallback() {
            @Override
            public void onSuccess(Object success) {
                mPayView.showPay((Result) success);
            }

            @Override
            public void onError(Object error) {

            }
        });
    }

}
