package cn.com.sino_device.xianshutushugui.borrow;


import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;
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

    ListView lvBorrow;
    private BorrowBookAdapter lvBorrowAdapter;
    private List<BookBorrow> borrowList = new ArrayList<>();
    ListView lvGiveBook;
    private BorrowBookAdapter lvGiveBackAdapter;
    private List<BookBorrow> givebookList = new ArrayList<>();

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
        lvBorrow = view.findViewById(R.id.lv_borrow);
        lvBorrowAdapter = new BorrowBookAdapter(getActivity(), borrowList);
        lvBorrowAdapter.notifyDataSetChanged();
        lvBorrow.setAdapter(lvBorrowAdapter);


        lvGiveBook = view.findViewById(R.id.lv_givebook);
        lvGiveBackAdapter = new BorrowBookAdapter(getActivity(), givebookList);
        lvGiveBackAdapter.notifyDataSetChanged();
        lvGiveBook.setAdapter(lvGiveBackAdapter);
        view.findViewById(R.id.tv_borrow).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    lvBorrow.setVisibility(View.VISIBLE);
                    lvGiveBook.setVisibility(View.GONE);

            }
        });
        view.findViewById(R.id.tv_givebook).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                lvBorrow.setVisibility(View.GONE);
                lvGiveBook.setVisibility(View.VISIBLE);

            }
        });
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
            initData(1);
            Log.i(TAG, "页面前台展示");
        }
    }

}
