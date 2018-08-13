package cn.com.sino_device.xianshutushugui.donate;


import android.content.Intent;
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
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

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
    private GestureDetector gesture; //手势识别
    private ImageButton scanBtn;
    private ListView lvHavCheck, lvNotCheck;
    private List<LibraryBookBean> HavChecklist = new ArrayList<>();
    private MyListAdapter mHavAdapter;
    private List<LibraryBookBean> NotChecklist = new ArrayList<>();
    private MyListAdapter mNotAdapter;
    private TextView tvNotCheck;
    private TextView tvHavCheck;

    public DonateFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_donate_book, container, false);
        //根据父窗体getActivity()为fragment设置手势识别
        gesture = new GestureDetector(this.getActivity(), new MyOnGestureListener());
        //为fragment添加OnTouchListener监听器
        view.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return gesture.onTouchEvent(event);//返回手势识别触发的事件
            }
        });

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
        lvHavCheck = view.findViewById(R.id.donate_havecheck_lv);
        mHavAdapter = new MyListAdapter(getActivity(), HavChecklist);
        lvHavCheck.setAdapter(mHavAdapter);
        lvHavCheck.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return gesture.onTouchEvent(event);//返回手势识别触发的事件
            }
        });
        lvNotCheck = view.findViewById(R.id.donate_notcheck_lv);
        mNotAdapter = new MyListAdapter(getActivity(), NotChecklist);
        lvNotCheck.setAdapter(mNotAdapter);
        lvNotCheck.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return gesture.onTouchEvent(event);//返回手势识别触发的事件
            }
        });

        lvHavCheck.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getActivity(), BorrowDetailActivity.class);
                intent.putExtra("bookId", HavChecklist.get(position).getId());
                intent.putExtra("tag", "2");
//                intent.putExtra("ISBN","9787534255380");
                getActivity().startActivity(intent);
            }
        });
        tvNotCheck = view.findViewById(R.id.tv_donate_notcheck);
        tvHavCheck = view.findViewById(R.id.tv_donate_havcheck);
        tvNotCheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                lvNotCheck.setVisibility(View.VISIBLE);
                lvHavCheck.setVisibility(View.GONE);
                tvNotCheck.setBackgroundColor(getResources().getColor(R.color.colorPeachPuff));
                tvHavCheck.setBackgroundColor(getResources().getColor(R.color.colorWhite));
            }
        });
        tvHavCheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                lvNotCheck.setVisibility(View.GONE);
                lvHavCheck.setVisibility(View.VISIBLE);
                tvNotCheck.setBackgroundColor(getResources().getColor(R.color.colorWhite));
                tvHavCheck.setBackgroundColor(getResources().getColor(R.color.colorPeachPuff));
            }
        });


        return view;
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
                tvHavCheck.performClick();
                return true;
            } else if ((e2.getX() - e1.getX() > 120) && Math.abs(velocityX) > 200) {
                tvNotCheck.performClick();
                return true;
            }
            return false;
        }
    }

    Handler myHandler = new Handler() {
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 1:
                    mHavAdapter.notifyDataSetChanged();
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
                        HavChecklist.add(JsonUtil.jsonToArrayList(result.getMsg(), LibraryBookBean.class).get(i));
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
