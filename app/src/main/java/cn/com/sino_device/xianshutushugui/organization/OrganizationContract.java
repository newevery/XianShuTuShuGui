package cn.com.sino_device.xianshutushugui.organization;

import cn.com.sino_device.xianshutushugui.BasePresenter;
import cn.com.sino_device.xianshutushugui.BaseView;

/**
 * Created by Android Studio.
 *
 * @author affe
 * @date 2018/6/27
 */
public interface OrganizationContract {

    /**
     * 获取获取组织机构 View
     */
    interface GetInfoView extends BaseView<GetInfoPresenter> {
        void showGetInfo(Object object);
    }


    /**
     * 获取获取组织机构 Presenter
     */
    interface GetInfoPresenter extends BasePresenter {
        void getInfo(Object object);
    }
}
