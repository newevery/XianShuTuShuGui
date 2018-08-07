package cn.com.sino_device.xianshutushugui.giveback;


import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.google.gson.Gson;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.com.sino_device.xianshutushugui.R;
import cn.com.sino_device.xianshutushugui.WebSocket.CallBack;
import cn.com.sino_device.xianshutushugui.WebSocket.JsonUtil;
import cn.com.sino_device.xianshutushugui.WebSocket.WebSocketAsyncTask;
import cn.com.sino_device.xianshutushugui.bean.book.BookBorrow;
import cn.com.sino_device.xianshutushugui.bean.book.LibraryBookBean;
import cn.com.sino_device.xianshutushugui.bean.user.Result;
import cn.com.sino_device.xianshutushugui.borrow.BorrowDetailActivity;
import cn.com.sino_device.xianshutushugui.library.MyListAdapter;


/**
 * @author affe
 */
public class GivebackFragment extends Fragment implements View.OnClickListener, GivebackContract.View, GivebackBookAdapter.CheckInterface {
    private static final String TAG = "GivebackFragment";

    private List<String> bookIds = new ArrayList<>();
    private Map<Integer, Boolean> stateMap = new HashMap<>();
    private ListView listView;
    private List<LibraryBookBean> mDatas = new ArrayList<>();
    private MyListAdapter mAdapter;
    public GivebackFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_giveback_book, container, false);
//        initView(view);
        listView = view.findViewById(R.id.lv_givevackbook);
        mAdapter = new MyListAdapter(getActivity(), mDatas);
        mAdapter.notifyDataSetChanged();
        listView.setAdapter(mAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getActivity(), BorrowDetailActivity.class);
                intent.putExtra("bookId", mDatas.get(position).getId());
                intent.putExtra("tag", "4");
                getActivity().startActivity(intent);
            }
        });

        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                if (stateMap.containsKey(position)) {
                    System.out.println(stateMap.toString());
                    if (stateMap.get(position)) {
                        view.setBackgroundColor(getResources().getColor(R.color.colorWhite));
                        bookIds.remove(mDatas.get(position).getId());
                        stateMap.put(position, false);
                    } else {
                        view.setBackgroundColor(getResources().getColor(R.color.colorGainsboro));
                        bookIds.add(mDatas.get(position).getId());
                        stateMap.put(position, true);

                    }
                } else {
                    view.setBackgroundColor(getResources().getColor(R.color.colorGainsboro));
                    bookIds.add(mDatas.get(position).getId());
                    stateMap.put(position, true);
                }


                return true;
            }


        });
        return view;
    }


    private void initView(View view) {

//        ///btnBack = view.findViewById(R.id.btn_back);
//        ckAll = view.findViewById(R.id.ck_all);
//        tvSettlementPrice = view.findViewById(R.id.tv_settlement_price);
//        tvSettlementCategory = view.findViewById(R.id.tv_settlement_category);
//        tvEdit = view.findViewById(R.id.tv_edit);
//        lvBorrowBook = view.findViewById(R.id.lv_borrowbook);
//
//        tvEdit.setOnClickListener(this);
//        ckAll.setOnClickListener(this);
//        tvSettlementCategory.setOnClickListener(this);
        ///btnBack.setOnClickListener(this);

    }
    Handler myHandler = new Handler() {
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 1:
                    System.out.println(mDatas);
                    mAdapter.notifyDataSetChanged();
                    break;
            }
            super.handleMessage(msg);
        }
    };
    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser) {
            Log.i(TAG,"页面前台展示");
            initData();
        }
    }
    //初始化数据
    protected void initData() {
        mDatas.clear();
        Map<String,String> map=new HashMap<>();//获取已出库借书   即当前用户属于借阅状态的图书
        map.put("bookstate","4");
        map.put("type","0");
        new WebSocketAsyncTask(new CallBack() {
            @Override
            public void onSuccess(String message) {
                Gson gson = new Gson();
                Result result = gson.fromJson(message, Result.class);
                if (result.isSuccess()) {
                    Log.i(TAG, result.getMsg());
                    for (int i = 0; i < JsonUtil.jsonToArrayList(result.getMsg(), LibraryBookBean.class).size(); i++) {
                        mDatas.add(JsonUtil.jsonToArrayList(result.getMsg(), LibraryBookBean.class).get(i));
                    }
                    Message msg = new Message();
                    msg.what = 1;
                    myHandler.sendMessage(msg);
                }
            }

            @Override
            public void onError(String error) {

            }
        }).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, "getPhoneBooks", JsonUtil.mapToJson(map));
        for (int i = 0; i < 2; i++) {
//            BookBorrow bookBorrow = new BookBorrow();
//            bookBorrow.setTitle("上档次的T桖");
//            bookBorrow.setAuthor("zhangsan");
//            bookBorrow.setId(i);
//            bookBorrow.setPrice(30.6);
//            bookBorrow.setDay(1);
//            bookBorrow.setImage_small("https://img3.doubanio.com/view/subject/s/public/s28754071.jpg");
//            borrowBookList.add(bookBorrow);
        }
        for (int i = 0; i < 2; i++) {
//            BookBorrow bookBorrow = new BookBorrow();
//            bookBorrow.setTitle("算是但是多");
//            bookBorrow.setAuthor("zhangsan");
//            bookBorrow.setId(i);
//            bookBorrow.setPrice(30.6);
//            bookBorrow.setDay(1);
//            bookBorrow.setImage_small("https://img3.doubanio.com/view/subject/s/public/s28754071.jpg");
//            borrowBookList.add(bookBorrow);
        }
//        givebackBookAdapter = new GivebackBookAdapter(getActivity());
//        givebackBookAdapter.setCheckInterface(this);
//        lvBorrowBook.setAdapter(givebackBookAdapter);
//        givebackBookAdapter.setBorrowBookList(borrowBookList);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            //全选按钮
            case R.id.ck_all:
//                if (borrowBookList.size() != 0) {
//                    if (ckAll.isChecked()) {
//                        for (int i = 0; i < borrowBookList.size(); i++) {
//                            borrowBookList.get(i).setChoosed(true);
//                        }
//                        givebackBookAdapter.notifyDataSetChanged();
//                    } else {
//                        for (int i = 0; i < borrowBookList.size(); i++) {
//                            borrowBookList.get(i).setChoosed(false);
//                        }
//                        givebackBookAdapter.notifyDataSetChanged();
//                    }
//                }
//                statistics();
                break;
            default:
                break;
        }
    }

    /**
     * 结算订单、支付
     */
    private void lementOnder() {
        //选中的需要提交的商品清单
//        for (BookBorrow book : borrowBookList) {
//            boolean choosed = book.isChoosed();
//            if (choosed) {
//                String shoppingName = book.getTitle();
//                int day = book.getDay();
//                double price = book.getPrice();
//                ///int size = bean.getDressSize();
//                String author = book.getAuthor();
//                int id = book.getId();
//                Log.d(TAG, id + "----id---" + shoppingName + "---" + day + "---" + price + "--size----" + author + "--attr---" + author);
//            }
//        }
        Toast.makeText(getActivity(), "总价", Toast.LENGTH_LONG).show();

        // TODO 跳转到支付界面
    }

    /**
     * 单选
     *
     * @param position  组元素位置
     * @param isChecked 组元素选中与否
     */
    @Override
    public void checkGroup(int position, boolean isChecked) {
//        borrowBookList.get(position).setChoosed(isChecked);
//        if (isAllCheck()) {
//            ckAll.setChecked(true);
//        } else {
//            ckAll.setChecked(false);
//        }
//        givebackBookAdapter.notifyDataSetChanged();
//        statistics();
    }

    /**
     * 遍历list集合
     *
     * @return
     */
    private boolean isAllCheck() {

//        for (BookBorrow group : borrowBookList) {
//            if (!group.isChoosed()) {
//                return false;
//            }
//        }
        return true;
    }

    /**
     * 统计操作
     * 1.先清空全局计数器
     * 2.遍历所有子元素，只要是被选中状态的，就进行相关的计算操作
     * 3.给底部的textView进行数据填充
     */
    public void statistics() {
//        totleCategory = 0;
//        totalPrice = 0.00;
//        for (int i = 0; i < borrowBookList.size(); i++) {
//            BookBorrow shoppingCartBean = borrowBookList.get(i);
////            if (shoppingCartBean.isChoosed()) {
////                totleCategory++;
////                totalPrice += shoppingCartBean.getPrice() * shoppingCartBean.getDay();
////            }
//        }
//        tvSettlementPrice.setText("合计:" + totalPrice);
//        tvSettlementCategory.setText("结算(" + totleCategory + ")");
    }

    @Override
    public void setPresenter(GivebackContract.Presenter presenter) {

    }
}
