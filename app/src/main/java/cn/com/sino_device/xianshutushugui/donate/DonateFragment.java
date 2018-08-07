package cn.com.sino_device.xianshutushugui.donate;


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
import android.widget.ImageButton;
import android.widget.ListView;

import com.google.gson.Gson;
import com.xys.libzxing.zxing.activity.CaptureActivity;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.com.sino_device.xianshutushugui.WebSocket.CallBack;
import cn.com.sino_device.xianshutushugui.WebSocket.JsonUtil;
import cn.com.sino_device.xianshutushugui.WebSocket.WebSocketAsyncTask;
import cn.com.sino_device.xianshutushugui.bean.book.LibraryBookBean;
import cn.com.sino_device.xianshutushugui.book.BookDetailActivity;
import cn.com.sino_device.xianshutushugui.R;
import cn.com.sino_device.xianshutushugui.bean.user.Result;
import cn.com.sino_device.xianshutushugui.borrow.BorrowDetailActivity;
import cn.com.sino_device.xianshutushugui.library.MyListAdapter;

import static android.app.Activity.RESULT_OK;


/**
 * 捐书（分享）
 *
 * @author affe
 */
public class DonateFragment extends Fragment implements DonateContract.View {
    private static final String TAG = "DonateFragment";

    private ImageButton scanBtn;
    private ListView listView;
    private List<LibraryBookBean> mDatas = new ArrayList<>();
    private MyListAdapter mAdapter;

    public DonateFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_donate_book, container, false);

        //donateBook // TODO: 18-7-17 需要获取当前用户的分享状态
//        new WebSocketAsyncTask(new CallBack() {
//            @Override
//            public void onSuccess(String message) {
//                Gson gson = new Gson();
//                Result result = gson.fromJson(message, Result.class);
//                if (result.isSuccess()) {
//                    Log.i(TAG, "获取当前用户的分享状态成功");
//                    System.out.println(result.getMsg());
//                }
//            }
//
//            @Override
//            public void onError(String error) {
//
//            }
//        }).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, "donateBook", "");

// TODO: 18-7-19  


        scanBtn = view.findViewById(R.id.donate_scan_IB);
        // 临时写的，后面正是时候要MVP优化
        scanBtn.setOnClickListener(v -> {
            startActivityForResult(new Intent(getActivity(), CaptureActivity.class), 0);//扫码获取ISBN病请求
//            getBookInfo("9787114103117");//9787115403254  9787115414779 9787115226266 9787512423046  9787115145543。。9787114103117
        });
        listView = view.findViewById(R.id.donate_havecheck_lv);
        mAdapter = new MyListAdapter(getActivity(), mDatas);
        mAdapter.notifyDataSetChanged();
        listView.setAdapter(mAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getActivity(), BorrowDetailActivity.class);
                intent.putExtra("bookId",mDatas.get(position).getId());
                intent.putExtra("tag","2");
//                intent.putExtra("ISBN","9787534255380");
                getActivity().startActivity(intent);
            }
        });
        return view;
    }

    Handler myHandler = new Handler() {
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 1:
                    mAdapter.notifyDataSetChanged();
                    break;
            }
            super.handleMessage(msg);
        }
    };

    private void initData() {
        Map<String, String> map = new HashMap();
//        map.put("type", "1");
//        map.put("booktype", "");
        map.put("bookstate", "0");
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
//
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser) {
            Log.i(TAG, "页面前台展示");
            initData();
        }
    }

    @Override
    public void setPresenter(DonateContract.Presenter presenter) {

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        //扫码获取ISBN的结果返回
        if (resultCode == RESULT_OK) {
            Bundle bundle = data.getExtras();
            final String result = bundle.getString("result");
            Intent intent = new Intent(getActivity(), BookDetailActivity.class);
            intent.putExtra("ISBN", result);
            startActivity(intent);

        }
    }


}
