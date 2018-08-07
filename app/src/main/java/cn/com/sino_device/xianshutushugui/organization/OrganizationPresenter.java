package cn.com.sino_device.xianshutushugui.organization;

import cn.com.sino_device.xianshutushugui.bean.user.GetInfo;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by Android Studio.
 *
 * @author affe
 * @date 2018/6/27
 */
public class OrganizationPresenter implements OrganizationContract.GetInfoPresenter {
    private static final String TAG = "OrganizationPresenter";

    private OrganizationSource organizationSource;

    private OrganizationContract.GetInfoView mGetInfoView;

    public OrganizationPresenter(OrganizationContract.GetInfoView getInfoView) {
        this.organizationSource = new OrganizationRealization();
        mGetInfoView = checkNotNull(getInfoView, "getInfoView cannot be null!");
        mGetInfoView.setPresenter(this);

    }

    @Override
    public void getInfo(Object object) {
        organizationSource.getInfo((GetInfo) object, new OrganizationSource.InformationCallback() {
            @Override
            public void onSuccess(Object success) {
                mGetInfoView.showGetInfo(success);
            }

            @Override
            public void onError(Object error) {
                mGetInfoView.showGetInfo(error);
            }
        });
    }

    @Override
    public void start() {

    }
}
