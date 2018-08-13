package cn.com.sino_device.xianshutushugui.borrow;


import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

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
import cn.com.sino_device.xianshutushugui.bean.user.Result;


/**
 * @author affe
 */
public class BorrowFragment extends Fragment {
    private static final String TAG = "BorrowFragment";
    private GestureDetector gesture; //手势识别
    ListView lvBorrow;
    private BorrowBookAdapter lvBorrowAdapter;
    private List<BookBorrow> borrowList = new ArrayList<>();
    ListView lvGiveBook;
    private BorrowBookAdapter lvGiveBackAdapter;
    private List<BookBorrow> givebookList = new ArrayList<>();
    private TextView tvGiveBook;
    private TextView tvBorrow;

    /**
     * user : {"mobile":"18603195364"}
     * book : {"bookName":"哈aaaa","id":"4116700"}
     * number : 1
     * orderTime : 2018-07-25 16:52:13
     * createTime : 1532508733000
     * type : 0
     * state : 0
     * day : 22
     * auditId : 0
     * auditResult : 0
     * auditTime : 0
     * reason : 0
     */


    public BorrowFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = null;
        if (null != view) {
            ViewGroup parent = (ViewGroup) view.getParent();
            if (null != parent) {
                parent.removeView(view);
            }
        } else {
            view = inflater.inflate(R.layout.fragment_borrow_book, container, false);
            initView(view);// 控件初始化
        }


        initView(view);
        return view;
    }

    private void initView(View view) {
        //根据父窗体getActivity()为fragment设置手势识别
        gesture = new GestureDetector(this.getActivity(), new MyOnGestureListener());
        //为fragment添加OnTouchListener监听器
        view.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return gesture.onTouchEvent(event);//返回手势识别触发的事件
            }
        });

        lvBorrow = view.findViewById(R.id.lv_borrow);
        lvBorrowAdapter = new BorrowBookAdapter(getActivity(), borrowList);
        lvBorrowAdapter.notifyDataSetChanged();
        lvBorrow.setAdapter(lvBorrowAdapter);
        lvBorrow.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return gesture.onTouchEvent(event);//返回手势识别触发的事件
            }
        });

        lvGiveBook = view.findViewById(R.id.lv_givebook);
        lvGiveBackAdapter = new BorrowBookAdapter(getActivity(), givebookList);
        lvGiveBackAdapter.notifyDataSetChanged();
        lvGiveBook.setAdapter(lvGiveBackAdapter);
        lvGiveBook.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return gesture.onTouchEvent(event);//返回手势识别触发的事件
            }
        });
        tvGiveBook = view.findViewById(R.id.tv_givebook);
        tvBorrow = view.findViewById(R.id.tv_borrow);
        tvBorrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                lvBorrow.setVisibility(View.VISIBLE);
                lvGiveBook.setVisibility(View.GONE);
                tvBorrow.setBackgroundColor(getResources().getColor(R.color.colorPeachPuff));
                tvGiveBook.setBackgroundColor(getResources().getColor(R.color.colorWhite));
            }
        });
        tvGiveBook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                initData(1);
                lvBorrow.setVisibility(View.GONE);
                lvGiveBook.setVisibility(View.VISIBLE);
                tvBorrow.setBackgroundColor(getResources().getColor(R.color.colorWhite));
                tvGiveBook.setBackgroundColor(getResources().getColor(R.color.colorPeachPuff));
            }
        });
    }

    //设置手势识别监听器
    private class MyOnGestureListener extends GestureDetector.SimpleOnGestureListener {
        @Override//此方法必须重写且返回真，否则onFling不起效
        public boolean onDown(MotionEvent e) {
            return true;
        }

        @Override
        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
            if ((e1.getX() - e2.getX() > 120) && Math.abs(velocityX) > 200) {
                tvBorrow.performClick();
                return true;
            } else if ((e2.getX() - e1.getX() > 120) && Math.abs(velocityX) > 200) {
                tvGiveBook.performClick();
                return true;
            }
            return false;
        }
    }

    Handler myHandler = new Handler() {
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 0:
                    System.out.println(borrowList.size());
                    lvBorrowAdapter.notifyDataSetChanged();
                    break;
                case 1:
                    System.out.println(givebookList.size());
                    lvGiveBackAdapter.notifyDataSetChanged();
                    break;
            }
            super.handleMessage(msg);
        }
    };

    /**
     * 初始化数据
     */
    protected void initData(int type) {

        Map<String, String> map = new HashMap<>();
        map.put("state", "0");
        map.put("type", type + "");
        new WebSocketAsyncTask(new CallBack() {
            @Override
            public void onSuccess(String message) {
                Gson gson = new Gson();
                Result result = gson.fromJson(message, Result.class);
                if (result.isSuccess()) {
                    Log.i(TAG + map.toString(), result.getMsg());

                    for (int i = 0; i < JsonUtil.jsonToArrayList(result.getMsg(), BookBorrow.class).size(); i++) {
                        // TODO: 18-7-23 需要根据分会类型做处理
                        if (type == 0) {
                            borrowList.add(JsonUtil.jsonToArrayList(result.getMsg(), BookBorrow.class).get(i));
                        } else {
                            givebookList.add(JsonUtil.jsonToArrayList(result.getMsg(), BookBorrow.class).get(i));
                        }
                    }
                    Message msg = new Message();
                    msg.what = type;
                    myHandler.sendMessage(msg);
                }
            }

            @Override
            public void onError(String error) {

            }
        }).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, "getOrderInfo", JsonUtil.mapToJson(map));

    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser) {
            borrowList.clear();
            givebookList.clear();
            initData(0);

            Log.i(TAG, "页面前台展示");
        }
    }

}
