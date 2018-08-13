package cn.com.sino_device.xianshutushugui.borrow;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import cn.com.sino_device.xianshutushugui.R;
import cn.com.sino_device.xianshutushugui.WebSocket.CallBack;
import cn.com.sino_device.xianshutushugui.WebSocket.JsonUtil;
import cn.com.sino_device.xianshutushugui.WebSocket.WebSocketAsyncTask;
import cn.com.sino_device.xianshutushugui.bean.douban.DoubanBook;
import cn.com.sino_device.xianshutushugui.bean.user.Result;

/**
 * @author null
 */
public class BorrowDetailActivity extends AppCompatActivity {
    private static final String TAG = "BorrowDetailActivity";
    // 返回图书信息
    public static final int RETURN_BOOKINFO_STATUS = 200;
    // 图书不存在
    public static final int BOOK_NOT_FOUND_STATUS = 404;


    private TextView bookTitle;
    private ImageView bookImage;
    private TextView bookAuthor;
    private TextView bookPublisher;
    private TextView bookPubdate;
    private TextView bookPages;
    private TextView bookPrice;
    private TextView bookSummary;
    private Button btnCancleShare, btnCollection, btnDirectBorrow;

    private String bookId;
    private String context;
    private StringBuffer sb2;
    private BorrowOrderBook book;
    private String ISBN;
    private URL url;
    private String tag;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_borrow_detail);


        bookTitle = findViewById(R.id.book_title);
        bookImage = findViewById(R.id.book_image);
        bookAuthor = findViewById(R.id.book_author);
        bookPublisher = findViewById(R.id.book_publisher);
        bookPubdate = findViewById(R.id.book_pubdate);
        bookPages = findViewById(R.id.book_pages);
        bookPrice = findViewById(R.id.book_price);
        bookSummary = findViewById(R.id.book_summary);
        btnCancleShare = findViewById(R.id.btn_cancle);
        btnCancleShare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BorrowDetailActivity.this.finish();
            }
        });
        btnCollection = findViewById(R.id.btn_collection);//加入收藏
        btnDirectBorrow = findViewById(R.id.btn_directborrow);//直接借阅

        bookId = getIntent().getStringExtra("bookId");
        tag = getIntent().getStringExtra("tag");
        if (tag != null && !"".equals(tag)) {//  1书库 2 捐书 3 收藏 4
            if ("1".equals(tag)) {
                btnCollection.setVisibility(View.VISIBLE);
                btnCollection.setText("加入收藏");
                btnCollection.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Map<String, String> map = new HashMap<>();
                        map.put("bookId", bookId);
                        map.put("flag", "1");
                        new WebSocketAsyncTask(new CallBack() {
                            @Override
                            public void onSuccess(String message) {
                                Gson gson = new Gson();
                                Result result = gson.fromJson(message, Result.class);
                                if (result.isSuccess()) {
                                    Log.i(TAG, result.getMsg());
                                    runOnUiThread(new Runnable() {
                                        @Override
                                        public void run() {
                                            Toast.makeText(BorrowDetailActivity.this, result.getMsg(), Toast.LENGTH_LONG).show();
                                        }
                                    });
                                    BorrowDetailActivity.this.finish();
                                }
                            }

                            @Override
                            public void onError(String error) {

                            }
                        }).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, "collectBook", JsonUtil.mapToJson(map));
                    }
                });
                btnDirectBorrow.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Map<String, String> map2 = new HashMap<>();
                        map2.put("bookId", bookId + "");
                        map2.put("number", "1");
                        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                        map2.put("orderTime", df.format(new Date()) + "");
                        map2.put("createTime", df.format(new Date()) + "");
                        map2.put("day", "22");
                        map2.put("type", "0");
                        new WebSocketAsyncTask(new CallBack() {
                            @Override
                            public void onSuccess(String message) {
                                Gson gson = new Gson();
                                Result result = gson.fromJson(message, Result.class);
                                if (result.isSuccess()) {
                                    Log.i(TAG, result.getMsg());

                                    runOnUiThread(new Runnable() {
                                        @Override
                                        public void run() {
                                            Toast.makeText(BorrowDetailActivity.this, result.getMsg(), Toast.LENGTH_LONG).show();
                                        }
                                    });
                                    BorrowDetailActivity.this.finish();
                                }
                            }

                            @Override
                            public void onError(String error) {

                            }
                        }).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, "orderBook", JsonUtil.mapToJson(map2));


                    }
                });
            } else if ("2".equals(tag)) {
                btnCollection.setVisibility(View.GONE);
                btnDirectBorrow.setText("分享");
                btnDirectBorrow.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                    }
                });
            } else if ("3".equals(tag)) {
//                btnCollection.setVisibility(View.GONE);
                btnCollection.setText("分享");
                btnCollection.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                    }
                });
                btnDirectBorrow.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                    }
                });
            } else if ("4".equals(tag)) {
//                btnCollection.setVisibility(View.GONE);
                btnCollection.setText("分享");
                btnCollection.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                    }
                });
                btnDirectBorrow.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        new WebSocketAsyncTask(new CallBack() {
                            @Override
                            public void onSuccess(String message) {
                                Gson gson = new Gson();
                                Result result = gson.fromJson(message, Result.class);
                                if (result.isSuccess()) {
                                    Log.i(TAG, result.getMsg());

//                                   if ()
                                }
                            }

                            @Override
                            public void onError(String error) {

                            }
                        }).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, "orderBook", "{}");


                        Map<String, String> map2 = new HashMap<>();
                        map2.put("bookId", bookId + "");
                        map2.put("number", "1");
                        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                        map2.put("orderTime", df.format(new Date()) + "");
                        map2.put("createTime", df.format(new Date()) + "");
                        map2.put("day", "22");
                        map2.put("type", "0");
                        new WebSocketAsyncTask(new CallBack() {
                            @Override
                            public void onSuccess(String message) {
                                Gson gson = new Gson();
                                Result result = gson.fromJson(message, Result.class);
                                if (result.isSuccess()) {
                                    Log.i(TAG, result.getMsg());
                                    runOnUiThread(new Runnable() {
                                        @Override
                                        public void run() {
                                            Toast.makeText(BorrowDetailActivity.this, result.getMsg(), Toast.LENGTH_LONG).show();
                                        }
                                    });
                                    BorrowDetailActivity.this.finish();
                                }
                            }

                            @Override
                            public void onError(String error) {

                            }
                        }).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, "orderBook", JsonUtil.mapToJson(map2));

                    }
                });

            } else if ("5".equals(tag)) {
//                btnCollection.setVisibility(View.GONE);
                btnCollection.setText("分享");
                btnDirectBorrow.setText("直接归还");
                btnCollection.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                    }
                });

                btnDirectBorrow.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (book.getIsCollect() == "1" || "1".equals(book.getIsCollect())) {

                        }
                        Map<String, String> map2 = new HashMap<>();
                        map2.put("bookId", bookId + "");
                        map2.put("number", "1");
                        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                        map2.put("orderTime", df.format(new Date()) + "");
                        map2.put("createTime", df.format(new Date()) + "");
                        map2.put("day", "22");
                        map2.put("type", "1");
                        new WebSocketAsyncTask(new CallBack() {
                            @Override
                            public void onSuccess(String message) {
                                Gson gson = new Gson();
                                Result result = gson.fromJson(message, Result.class);
                                if (result.isSuccess()) {
                                    Log.i(TAG, result.getMsg());
                                    runOnUiThread(new Runnable() {
                                        @Override
                                        public void run() {
                                            Toast.makeText(BorrowDetailActivity.this, result.getMsg(), Toast.LENGTH_LONG).show();
                                        }
                                    });
                                    BorrowDetailActivity.this.finish();
                                }
                            }

                            @Override
                            public void onError(String error) {

                            }
                        }).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, "orderBook", JsonUtil.mapToJson(map2));
                    }
                });
            }
        }

        uploadBook(bookId);


    }

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {      //判断标志位
                case 1:
                    /**
                     获取数据，更新UI
                     */

                    book = (BorrowOrderBook) msg.obj;


                    bookTitle.setText(book.getName());
                    ImageLoader.getInstance().displayImage(book.getPhoto1(), bookImage);
                    bookAuthor.setText("作者： " + book.getAuthor());  // TODO: 18-7-18 需要处理非空
                    bookPublisher.setText("拥有者： " + book.getUnit() + "");
                    bookPubdate.setText("出版时间： " + book.getPubdate());
                    bookPages.setText("页码： " + book.getPages());
                    bookPrice.setTag("定价： " + book.getPrice());
                    bookSummary.setText(book.getIntroduction());
                    if (tag != null && !"".equals(tag)) {
                        System.out.println(book.getState());
                        if (book.getIsCollect() == "1" || "1".equals(book.getIsCollect())) {
                            btnCollection.setText("已收藏");
                            btnCollection.setClickable(false);

                        }
                        if (book.getState() == "1" || "1".equals(book.getState())) {
                            btnDirectBorrow.setText("已借阅");
                            btnDirectBorrow.setClickable(false);

                        }
                        break;
                    }


            }
        }
    };

    public void uploadBook(String id) {


        Map<String, String> map = new HashMap<>();
        map.put("bookId", id);// TODO: 18-7-25
//        map.put("flag", "1");
        map.put("limit", "0");
        new WebSocketAsyncTask(new CallBack() {
            @Override
            public void onSuccess(String message) {
                Gson gson = new Gson();
                Result result = gson.fromJson(message, Result.class);
                if (result.isSuccess()) {
                    Log.i(TAG, result.getMsg());
//                    for (int i = 0; i < JsonUtil.jsonToArrayList(result.getMsg(), BorrowOrderBook.class).size(); i++) {
//                        // TODO: 18-7-23 需要根据分会类型做处理
////                        mDatas.add(Jso nUtil.jsonToArrayList(result.getMsg(), LibraryBookBean.class).get(i));
//                    }
//                    String json=result.getMsg().substring(1,result.getMsg().length()-1).replaceAll("\\\\","");
                    book = JsonUtil.jsonToArrayList(result.getMsg(), BorrowOrderBook.class).get(0);
                    Message msg = new Message();
                    msg.obj = book;
                    msg.what = 1;
                    handler.sendMessage(msg);
                }
            }

            @Override
            public void onError(String error) {

            }
        }).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, "getPhoneBooks", JsonUtil.mapToJson(map));

//        String ISBN_URL = "https://api.douban.com/v2/book/isbn/";
//        String requestUrl = ISBN_URL + isbnNo;
//        try {
//            url = new URL(requestUrl);
//        } catch (MalformedURLException e) {
//            e.printStackTrace();
//        }
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                try {
//                    HttpURLConnection conn = (HttpURLConnection) url.openConnection();
//                    conn.connect();
//
//                    if (conn.getResponseCode() == RETURN_BOOKINFO_STATUS) {
//                        InputStream is = conn.getInputStream();
//                        InputStreamReader isr = new InputStreamReader(is, "utf-8");
//                        BufferedReader br = new BufferedReader(isr);
//                        StringBuilder sb = new StringBuilder();
//
//                        String line = null;
//                        while ((line = br.readLine()) != null) {
//                            sb.append(line);
//                        }
//                        br.close();
//                        context = sb.toString();
//
//                        book = (new Gson()).fromJson(context, DoubanBook.class);
//                        bookId=book.getId();// TODO: 18-7-25
//                        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//                        sb2 = new StringBuffer();
//                        sb2.append(book.toString()).insert(book.toString().indexOf("{") + 1, "regTime:'" + (df.format(new Date()) + "") + "',");
//                        Message msg = Message.obtain();
//                        msg.obj = book;
//                        msg.what = 1;   //标志消息的标志
//                        handler.sendMessage(msg);
////                        new WebSocketAsyncTask(new CallBack() {
////                            @Override
////                            public void onSuccess(String message) {
////                                Gson gson = new Gson();
////                                Result result = gson.fromJson(message, Result.class);
////                                if (result.isSuccess()) {
////                                    bookId = result.getMsg();
////                                    Log.i(TAG, bookId);
////                                }
////                            }
////
////                            @Override
////                            public void onError(String error) {
////
////                            }
////                        }).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, "uploadBook", sb2.toString());
//
//                    }
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            }
//        }).start();
//        Log.i(TAG, context);
//        return context;
    }

//    @Override
//    public void onClick(View view) {
//        switch (view.getId()) {
//            case R.id.btn_cancle:
//                BorrowDetailActivity.this.finish();
//                break;
//            case R.id.btn_collection:
//                Map<String, String> map = new HashMap<>();
//                map.put("bookId", bookId);
//                map.put("flag", "1");
//                new WebSocketAsyncTask(new CallBack() {
//                    @Override
//                    public void onSuccess(String message) {
//                        Gson gson = new Gson();
//                        Result result = gson.fromJson(message, Result.class);
//                        if (result.isSuccess()) {
//                            Log.i(TAG, result.getMsg());
////                                System.out.println(result.getMsg());
//                            BorrowDetailActivity.this.finish();
//                        }
//                    }
//
//                    @Override
//                    public void onError(String error) {
//
//                    }
//                }).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, "collectBook", JsonUtil.mapToJson(map));
//
//                break;
//            case R.id.btn_directborrow:
//
//                Map<String, String> map2 = new HashMap<>();
//                map2.put("bookId", bookId + "");
//                map2.put("number", "1");
//                SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//                map2.put("orderTime", df.format(new Date()) + "");
//                map2.put("createTime", df.format(new Date()) + "");
//                map2.put("day", "22");
//                map2.put("type", "0");
//                new WebSocketAsyncTask(new CallBack() {
//                    @Override
//                    public void onSuccess(String message) {
//                        Gson gson = new Gson();
//                        Result result = gson.fromJson(message, Result.class);
//                        if (result.isSuccess()) {
//                            Log.i(TAG, result.getMsg());
//
//                        }
//                    }
//
//                    @Override
//                    public void onError(String error) {
//
//                    }
//                }).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, "orderBook", JsonUtil.mapToJson(map2));
//
//                BorrowDetailActivity.this.finish();
//                break;
//            default:
//                break;
//        }
//    }


}
