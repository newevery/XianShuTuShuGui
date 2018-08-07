package cn.com.sino_device.xianshutushugui.borrow;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by Android Studio.
 * Date: 2018/5/14
 * Time: 10:06
 *
 * @author affe
 */
public class BorrowPresenter implements BorrowContract.Presenter {
    private static final String TAG = "BorrowPresenter";

    private final BorrowContract.View mBorrowView;

    public BorrowPresenter(BorrowContract.View borrowView) {
        mBorrowView = checkNotNull(borrowView);
        mBorrowView.setPresenter(this);
    }

    @Override
    public void start() {

    }
}
