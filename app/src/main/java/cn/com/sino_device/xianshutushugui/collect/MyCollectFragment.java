package cn.com.sino_device.xianshutushugui.collect;


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
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.google.gson.Gson;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.com.sino_device.xianshutushugui.R;
import cn.com.sino_device.xianshutushugui.WebSocket.CallBack;
import cn.com.sino_device.xianshutushugui.WebSocket.JsonUtil;
import cn.com.sino_device.xianshutushugui.WebSocket.WebSocketAsyncTask;
import cn.com.sino_device.xianshutushugui.bean.book.BookBorrow;
import cn.com.sino_device.xianshutushugui.bean.user.Result;
import cn.com.sino_device.xianshutushugui.borrow.BorrowDetailActivity;
import cn.com.sino_device.xianshutushugui.util.StringUtil;

/**
 * A simple {@link Fragment} subclass.
 */
public class MyCollectFragment extends Fragment {
    private String TAG = "MyCollectFragment";
    private List<CollectBook> mDatas;
    private CollectBookAdapter myAdapter;
    private ListView listView;
    private List<String> bookIds = new ArrayList<>();
    private Map<Integer, Boolean> stateMap = new HashMap<>();

    public MyCollectFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        View view = inflater.inflate(R.layout.fragment_my_collect, container, false);
        listView = view.findViewById(R.id.lv_mycollect);
        mDatas = new ArrayList<>();
        myAdapter = new CollectBookAdapter(getActivity(), mDatas);
        listView.setAdapter(myAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getActivity(), BorrowDetailActivity.class);
                intent.putExtra("bookId", mDatas.get(position).getBookId());
                intent.putExtra("tag", "3");
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
                        bookIds.remove(mDatas.get(position).getBookId());
                        stateMap.put(position, false);
                    } else {
                        view.setBackgroundColor(getResources().getColor(R.color.colorGainsboro));
                        bookIds.add(mDatas.get(position).getBookId());
                        stateMap.put(position, true);

                    }
                } else {
                    view.setBackgroundColor(getResources().getColor(R.color.colorGainsboro));
                    bookIds.add(mDatas.get(position).getBookId());
                    stateMap.put(position, true);
                }
                return true;
            }
        });
        CheckBox ckAll = view.findViewById(R.id.ck_all);
        ckAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ckAll.isChecked()) {
                    for (int i = 0; i < mDatas.size(); i++) {
                        ((View) getViewByPosition(i, listView)).setBackgroundColor(getResources().getColor(R.color.colorGainsboro));
                        stateMap.put(i, true);
                        if (!bookIds.contains(mDatas.get(i).getBookId())) {
                            bookIds.add(mDatas.get(i).getBookId());
                        }
                    }
                } else {
                    for (int i = 0; i < mDatas.size(); i++) {
                        ((View) getViewByPosition(i, listView)).setBackgroundColor(getResources().getColor(R.color.colorWhite));
                        stateMap.put(i, false);
                        bookIds.clear();
                    }
                }
            }
        });
        EditText et_day = view.findViewById(R.id.tv_borrow_day);
        Button btnSubmit = view.findViewById(R.id.tv_submit);
        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String s = bookIds.toString();
                s = s.replace("[", "").replace("]", "");

                if ("".equals(s)) {
                    Toast.makeText(getActivity(), "当前未选择图书", Toast.LENGTH_LONG).show();
                } else if (et_day.getText().toString() == null || "".equals(et_day.getText().toString())){
                    Toast.makeText(getActivity(), "请输入借阅天数", Toast.LENGTH_LONG).show();
                } else {
                    Map<String,String> map=new HashMap<>();
                    map.put("bookId",s);
                    map.put("number",bookIds.size()+"");
                    map.put("orderTime",et_day.getText().toString());
                    map.put("createTime", StringUtil.getDate(new Date(),"yyyy-MM-dd"));
                    map.put("type","0");

                    new WebSocketAsyncTask(new CallBack() {
                        @Override
                        public void onSuccess(String message) {
                            Gson gson = new Gson();
                            Result result = gson.fromJson(message, Result.class);
                            if (result.isSuccess()) {
                                Log.i(TAG, result.getMsg());
                            }
                        }

                        @Override
                        public void onError(String error) {

                        }
                    }).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, "orderBook", JsonUtil.mapToJson(map));
                }
            }
        });
        return view;
    }

    public View getViewByPosition(int pos, ListView listView) {
        final int firstListItemPosition = listView.getFirstVisiblePosition();
        final int lastListItemPosition = firstListItemPosition + listView.getChildCount() - 1;

        if (pos < firstListItemPosition || pos > lastListItemPosition) {
            return listView.getAdapter().getView(pos, null, listView);
        } else {
            final int childIndex = pos - firstListItemPosition;
            return listView.getChildAt(childIndex);
        }
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser) {
            Log.i(TAG, "页面前台展示");
            bookIds.clear();
            initData();
        }
    }

    Handler myHandler = new Handler() {
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 1:
                    System.out.println("数据长度  " + mDatas.size());
                    myAdapter.notifyDataSetChanged();
                    break;
            }
            super.handleMessage(msg);
        }
    };

    protected void initData() {
        mDatas.clear();
//        Map<String, String> map = new HashMap<>();//获取收藏图书
//        map.put("state", "0");
//        map.put("type", "0");
        new WebSocketAsyncTask(new CallBack() {
            @Override
            public void onSuccess(String message) {
                Gson gson = new Gson();
                Result result = gson.fromJson(message, Result.class);
                if (result.isSuccess()) {
                    Log.i(TAG, result.getMsg());
                    for (int i = 0; i < JsonUtil.jsonToArrayList(result.getMsg(), CollectBook.class).size(); i++) {
                        // TODO: 18-7-23 需要根据分会类型做处理
                        mDatas.add(JsonUtil.jsonToArrayList(result.getMsg(), CollectBook.class).get(i));
                    }
                    System.out.println(TAG + mDatas);
                    Message msg = new Message();
                    msg.what = 1;
                    myHandler.sendMessage(msg);
                }
            }

            @Override
            public void onError(String error) {

            }
        }).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, "getCollectBook", "{}");
    }
}
