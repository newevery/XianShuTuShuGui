package cn.com.sino_device.xianshutushugui.library;

import android.util.Log;

import com.google.gson.Gson;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import cn.com.sino_device.xianshutushugui.bean.Book;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by Android Studio.
 * Date: 2018/5/3
 * Time: 14:39
 *
 * @author affe
 */
public class LibraryPresenter implements LibraryContract.Presenter {
    private static final String TAG = "LibraryPresenter";

    private final LibraryContract.View mLibraryView;

    private boolean mFirstLoad = true;

    public LibraryPresenter(LibraryContract.View libraryView) {
        mLibraryView = checkNotNull(libraryView, "libraryView cannot be null!");
        mLibraryView.setPresenter(this);
    }

    @Override
    public void start() {
        loadBooks(false);
    }

    @Override
    public void loadBooks(boolean forceUpdate) {
        loadBooks(forceUpdate || mFirstLoad, true);
        mFirstLoad = false;
    }

    private void loadBooks(final boolean forceUpdate, final boolean showLoadingUI) {
        if (showLoadingUI) {
            mLibraryView.setLoading(true);
        }
        mLibraryView.setLoading(false);



    }

    @Override
    public void queryBook(String keyword) {
        queryBook(keyword, true);
    }

    private void queryBook(final String keyword, final boolean showLoadingUI) {
        if (showLoadingUI) {
            mLibraryView.setLoading(true);
        }
        mLibraryView.setLoading(false);

    }


}
