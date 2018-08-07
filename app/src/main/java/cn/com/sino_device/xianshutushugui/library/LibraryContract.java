package cn.com.sino_device.xianshutushugui.library;

import java.util.List;

import cn.com.sino_device.xianshutushugui.BasePresenter;
import cn.com.sino_device.xianshutushugui.BaseView;
import cn.com.sino_device.xianshutushugui.bean.Book;

/**
 * Created by Android Studio.
 * Date: 2018/5/3
 * Time: 14:37
 *
 * @author affe
 */
public interface LibraryContract {

    interface View extends BaseView<Presenter> {
        void setLoading(boolean active);

        void showBooks(List<Book> books);
    }

    interface Presenter extends BasePresenter {
        void loadBooks(boolean forceUpdate);

        void queryBook(String keyword);
    }
}
