package cn.com.sino_device.xianshutushugui.donate;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by Android Studio.
 * Date: 2018/5/11
 * Time: 10:26
 *
 * @author affe
 */
public class DonatePresenter implements DonateContract.Presenter {
    private static final String TAG = "DonatePresenter";

    private final DonateContract.View mDonateView;

    public DonatePresenter(DonateContract.View donatekView) {
        mDonateView = checkNotNull(donatekView);
        mDonateView.setPresenter(this);
    }

    @Override
    public void start() {

    }
}
