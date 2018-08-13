package cn.com.sino_device.xianshutushugui.book;

import cn.com.sino_device.xianshutushugui.bean.book.DonateBook;
import cn.com.sino_device.xianshutushugui.bean.book.GetBookType;
import cn.com.sino_device.xianshutushugui.bean.book.GetBooks;
import cn.com.sino_device.xianshutushugui.bean.book.GiveBackBook;
import cn.com.sino_device.xianshutushugui.bean.book.OrderBook;
import cn.com.sino_device.xianshutushugui.bean.book.RIOrderBook;
import cn.com.sino_device.xianshutushugui.bean.book.UploadBook;

/**
 * Created by Android Studio.
 *
 * @author affe
 * @date 2018/6/27
 */
public interface BookSource {

    interface BookCallback {
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
     * 获取图书
     *
     * @param getBooks
     * @param bookCallback
     */
    void getBooks(GetBooks getBooks, BookCallback bookCallback);

    /**
     * 图书上传
     *
     * @param uploadBook
     * @param bookCallback
     */
    void uploadBook(UploadBook uploadBook, BookCallback bookCallback);

    /**
     * 图书捐赠接口
     *
     * @param donateBook
     * @param bookCallback
     */
    void donateBook(DonateBook donateBook, BookCallback bookCallback);

    /**
     * 图书预约借阅
     *
     * @param orderBook
     * @param bookCallback
     */
    void orderBook(OrderBook orderBook, BookCallback bookCallback);

    /**
     * 获取图书预约(借还书)信息接口
     *
     * @param riOrderBook
     * @param bookCallback
     */
    void riOrderBook(RIOrderBook riOrderBook, BookCallback bookCallback);

    /**
     * 图书预约还书
     *
     * @param giveBackBook
     * @param bookCallback
     */
    void giveBackBook(GiveBackBook giveBackBook, BookCallback bookCallback);

    /**
     * 获取图书类目
     *
     * @param getBookType
     * @param bookCallback
     */
    void getBookType(GetBookType getBookType, BookCallback bookCallback);

}
