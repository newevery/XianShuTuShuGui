package cn.com.sino_device.xianshutushugui.library;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;


import com.google.gson.Gson;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.com.sino_device.xianshutushugui.R;
import cn.com.sino_device.xianshutushugui.WebSocket.CallBack;
import cn.com.sino_device.xianshutushugui.WebSocket.JsonUtil;
import cn.com.sino_device.xianshutushugui.WebSocket.WebSocketAsyncTask;
import cn.com.sino_device.xianshutushugui.bean.book.LibraryBookBean;
import cn.com.sino_device.xianshutushugui.bean.user.Result;
import cn.com.sino_device.xianshutushugui.borrow.BorrowDetailActivity;


public class LibraryListFragment extends Fragment {


    private static final String TAG = "LibraryListFragment";
    private static int postion;
    private static Context mContext;
    private int curPage = 1;
    private List<LibraryBookBean> mDatas = new ArrayList<>();
    private MyListAdapter mAdapter;
    private PullToRefreshListView mPullRefreshListView;


    public static LibraryListFragment newInstance(int pos, Context context) {
        mContext = context;
        postion = pos;
//        Bundle args = new Bundle();
//        args.putInt(NEWS_TYPE, pos);
        LibraryListFragment fragment = new LibraryListFragment();
//        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_library_list, container, false);
        mPullRefreshListView = (PullToRefreshListView) view.findViewById(R.id.pull_refresh_list);
        mPullRefreshListView.setHasPullUpFriction(false); // 设置没有上拉阻力
        mPullRefreshListView.setHasPullDownFriction(false);
        mPullRefreshListView.setMode(PullToRefreshBase.Mode.BOTH);
// TODO: 18-7-19
//        LibraryBookBean libraryBookBean = new LibraryBookBean();
//        libraryBookBean.setAuthor("sssssss ");
//        libraryBookBean.setCost("11");
//        libraryBookBean.setName("qqqqqqq");
//        libraryBookBean.setImages("https://img3.doubanio.com/view/subject/m/public/s8914925.jpg");
//        libraryBookBean.setType("qqqqqqqqqqqqq");
//        mDatas.add(libraryBookBean);
//        mDatas.add(libraryBookBean);
//        mDatas.add(libraryBookBean);
//        mDatas.add(libraryBookBean);
//        mDatas.add(libraryBookBean);

        ListView actualListView = mPullRefreshListView.getRefreshableView();

        // Need to use the Actual ListView when registering for Context Menu
        registerForContextMenu(actualListView);

        mAdapter = new MyListAdapter(getActivity(), mDatas);
        mAdapter.notifyDataSetChanged();
        // You can also just use setListAdapter(mAdapter) or
        actualListView.setAdapter(mAdapter);

        mPullRefreshListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getActivity(), BorrowDetailActivity.class);
                intent.putExtra("bookId",mDatas.get(position).getId());
                intent.putExtra("tag","1");
//                intent.putExtra("ISBN","9787534255380");
                 getActivity().startActivity(intent);
            }
        });
        // Set a listener to be invoked when the list should be refreshed.
        mPullRefreshListView.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener<ListView>() {
            @Override
            public void onRefresh(PullToRefreshBase<ListView> refreshView) {
                getbooks(1 + "");
            }
        });

        // Add an end-of-list listener  最后一个item可见。。。
        mPullRefreshListView.setOnLastItemVisibleListener(new PullToRefreshBase.OnLastItemVisibleListener() {

            @Override
            public void onLastItemVisible() {
                curPage += 1;
                Log.i(TAG, curPage + "");
                getbooks(curPage + "");
                // Call onRefreshComplete when the list has been refreshed.
//                mPullRefreshListView.onRefreshComplete();
            }
        });
        getbooks("1");

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);


    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser) {
            Log.i(TAG,"页面前台展示");
            getbooks("1");
        }
    }
    private void initData() {

//        mDatas=getbooks(curPage + "");
//        Bundle bundle = getArguments();
//        newsType = bundle.getInt(NEWS_TYPE, Constant.NEWS_TYPE_YEJIE);
//        mAdapter = new LibraryBookListAdapter(mContext);
//        mDatas = new ArrayList<>();
//        LibraryBookBean libraryBookBean = new LibraryBookBean();
//        libraryBookBean.setAuthor("sssssss ");
//        libraryBookBean.setCost(11.00);
//        libraryBookBean.setName("qqqqqqq");
//        libraryBookBean.setPhoto2("https://img3.doubanio.com/view/subject/m/public/s8914925.jpg");
//        libraryBookBean.setPublisher("qqqqqqqqqqqqq");
//        mDatas.add(libraryBookBean);
//        mDatas.add(libraryBookBean);
//        mDatas.add(libraryBookBean);
//        mDatas.add(libraryBookBean);
//        mDatas.add(libraryBookBean);
//        mAdapter.setDatas(mDatas);
    }

    // TODO: 18-7-17 获取当前类目下面的图书
    private List<LibraryBookBean> getbooks(String page) {
        Map<String, String> map = new HashMap();
//        map.put("type", postion + "");
//        map.put("type", "");
//        map.put("booktype", "");
//        map.put("bookstate", "0");
        map.put("limit", page);

        Log.i(TAG, "准备获取Book");


//        WebSocketInstance.wsConnect("getPhoneBooks", JsonUtil.mapToJson(map), new CallBack() {
//            @Override
//            public void onSuccess(String message) {
//                Gson gson = new Gson();
//                Result result = gson.fromJson(message, Result.class);
//                if (result.isSuccess()) {
//                    Log.i(TAG, result.getMsg());
//                    for (int i = 0; i < JsonUtil.jsonToArrayList(result.getMsg(), LibraryBookBean.class).size(); i++) {
//                        mDatas.add(JsonUtil.jsonToArrayList(result.getMsg(), LibraryBookBean.class).get(i));
//                    }
//                    System.out.println(curPage + "-------" + mDatas.size());
//                    mAdapter.notifyDataSetChanged();
//                }
//            }
//
//            @Override
//            public void onError(String error) {
//
//            }
//        });


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
                    System.out.println(curPage + "-------" + mDatas.size());
                    Message msg = new Message();
                    msg.what = 1;
                    myHandler.sendMessage(msg);
                }
            }

            @Override
            public void onError(String error) {

            }
        }).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, "getPhoneBooks", JsonUtil.mapToJson(map));


        return mDatas;
    }

    Handler myHandler = new Handler() {
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 1:
                    mAdapter.notifyDataSetChanged();
                    mPullRefreshListView.onRefreshComplete();
                    break;
            }
            super.handleMessage(msg);
        }
    };

}
