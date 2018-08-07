package cn.com.sino_device.xianshutushugui.book;

import cn.com.sino_device.xianshutushugui.bean.user.DonateBook;
import cn.com.sino_device.xianshutushugui.bean.user.GetBookType;
import cn.com.sino_device.xianshutushugui.bean.user.GetBooks;
import cn.com.sino_device.xianshutushugui.bean.user.GetInfo;
import cn.com.sino_device.xianshutushugui.bean.user.GiveBackBook;
import cn.com.sino_device.xianshutushugui.bean.user.OrderBook;
import cn.com.sino_device.xianshutushugui.bean.user.RIOrderBook;

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
