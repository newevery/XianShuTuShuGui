package cn.com.sino_device.xianshutushugui.giveback;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by Android Studio.
 * Date: 2018/5/8
 * Time: 14:38
 *
 * @author affe
 */
public class GivebackPresenter implements GivebackContract.Presenter {
    private static final String TAG = "GivebackPresenter";

    private final GivebackContract.View mGivebackView;

    public GivebackPresenter(GivebackContract.View givebackView) {
        mGivebackView = checkNotNull(givebackView);
        mGivebackView.setPresenter(this);
    }

    @Override
    public void start() {

    }
}
