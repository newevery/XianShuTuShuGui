package cn.com.sino_device.xianshutushugui.book;

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
public class BookDetailActivity extends AppCompatActivity implements View.OnClickListener {
    private static final String TAG = "BookDetailActivity";
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
    private Button btnCancleShare, btnFreeShare, btnChargeShare;

    private String bookId;
    private String context;
    private StringBuffer sb2;
    private DoubanBook book;
    private String ISBN;
    private URL url;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_detail);


        bookTitle = findViewById(R.id.book_title);
        bookImage = findViewById(R.id.book_image);
        bookAuthor = findViewById(R.id.book_author);
        bookPublisher = findViewById(R.id.book_publisher);
        bookPubdate = findViewById(R.id.book_pubdate);
        bookPages = findViewById(R.id.book_pages);
        bookPrice = findViewById(R.id.book_price);
        bookSummary = findViewById(R.id.book_summary);
        btnCancleShare = findViewById(R.id.btn_cancleShare);
        btnCancleShare.setOnClickListener(this);
        btnFreeShare = findViewById(R.id.btn_freeShare);
        btnFreeShare.setOnClickListener(this);
        btnChargeShare = findViewById(R.id.btn_chargeShare);
        btnChargeShare.setOnClickListener(this);


        ISBN = getIntent().getStringExtra("ISBN");
        uploadBook(ISBN);


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
                    book = (DoubanBook) msg.obj;

                    bookTitle.setText(book.getTitle());
                    ImageLoader.getInstance().displayImage(book.getImage(), bookImage);
                    bookAuthor.setText(book.getAuthor().get(0) + "");  // TODO: 18-7-18 需要处理非空
                    bookPublisher.setText(book.getPublisher() + "");
                    bookPubdate.setText(book.getPubdate());
                    bookPages.setText(book.getPages());
                    bookPrice.setTag(book.getPrice());
                    bookSummary.setText(book.getSummary());

                    break;
            }
        }
    };

    public void uploadBook(String isbnNo) {

        String ISBN_URL = "https://api.douban.com/v2/book/isbn/";
        String requestUrl = ISBN_URL + isbnNo;
        try {
            url = new URL(requestUrl);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                    conn.connect();

                    if (conn.getResponseCode() == RETURN_BOOKINFO_STATUS) {
                        InputStream is = conn.getInputStream();
                        InputStreamReader isr = new InputStreamReader(is, "utf-8");
                        BufferedReader br = new BufferedReader(isr);
                        StringBuilder sb = new StringBuilder();

                        String line = null;
                        while ((line = br.readLine()) != null) {
                            sb.append(line);
                        }
                        br.close();
                        context = sb.toString();

                        book = (new Gson()).fromJson(context, DoubanBook.class);
                        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                        sb2 = new StringBuffer();
                        sb2.append(book.toString()).insert(book.toString().indexOf("{") + 1, "regTime:'" + (df.format(new Date()) + "") + "',");
                        Message msg = Message.obtain();
                        msg.obj = book;
                        msg.what = 1;   //标志消息的标志
                        handler.sendMessage(msg);
                        new WebSocketAsyncTask(new CallBack() {
                            @Override
                            public void onSuccess(String message) {
                                Gson gson = new Gson();
                                Result result = gson.fromJson(message, Result.class);
                                if (result.isSuccess()) {
                                    bookId = result.getMsg();
                                    Log.i(TAG, bookId);
                                }
                            }

                            @Override
                            public void onError(String error) {

                            }
                        }).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, "uploadBook", sb2.toString());

                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
//        Log.i(TAG, context);
//        return context;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_cancleShare:
                BookDetailActivity.this.finish();
                break;
            case R.id.btn_chargeShare:
                AlertDialog.Builder builder = new AlertDialog.Builder(BookDetailActivity.this);
                builder.setTitle("请输入");
                builder.setIcon(R.mipmap.ic_launcher);
                EditText editText = new EditText(BookDetailActivity.this);
                builder.setView(editText);
                builder.setPositiveButton("是", (dialog, which) -> {
                    //讲定价上传到服务器
                    Toast.makeText(BookDetailActivity.this, "你讲按照这个价格分析" + editText.getText().toString(), Toast.LENGTH_LONG).show();
                    BookDetailActivity.this.finish();


                    Map<String, String> map = new HashMap<>();
                    map.put("bookId", bookId + "");
                    map.put("price", book.getPrice());
                    map.put("type", "0");
                    map.put("typeId","1");
                    map.put("cost", "2");
                    new WebSocketAsyncTask(new CallBack() {
                        @Override
                        public void onSuccess(String message) {
                            Gson gson = new Gson();
                            Result result = gson.fromJson(message, Result.class);
                            if (result.isSuccess()) {
                                Log.i(TAG, result.getMsg());
//                                System.out.println(result.getMsg());

                            }
                        }

                        @Override
                        public void onError(String error) {

                        }
                    }).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, "donateBook", JsonUtil.mapToJson(map));
                });
                builder.setNegativeButton("否", null);
                builder.show();

                break;
            case R.id.btn_freeShare:
                    //图书类目选择
//                final String[] Catelogs = new String[]{"马列主义、毛泽东思想、邓小平理论",
//                        "哲学、宗教", "社会科学总论", "政治、法律", "军事", "经济",
//                        "文化、科学、教育、体育", "语言、文字", "文学", "艺术", "历史、地理",
//                        "自然科学总论", "数理科学和化学", "天文学、地球科学", "生物科学",
//                        "医药、卫生", "农业科学", "工业技术", "交通运输", "航空、航天",
//                        "环境科学、劳动保护科学（安全科学）", "综合性图书"};
//                AlertDialog alertDialog = new AlertDialog.Builder(this)
//                        .setTitle("选择图书分类")
//                        .setIcon(R.mipmap.ic_launcher)
//                        .setItems(Catelogs, new DialogInterface.OnClickListener() {//添加列表
//                            @Override
//                            public void onClick(DialogInterface dialogInterface, int i) {
//                                Toast.makeText(BookDetailActivity.this, "：" + Catelogs[i], Toast.LENGTH_SHORT).show();
//                            }
//                        })
//                        .create();
//                alertDialog.show();
                Map<String, String> map = new HashMap<>();
                map.put("bookId", bookId + "");
                map.put("price", book.getPrice());
                map.put("type", "1");
                map.put("cost", "");
                map.put("typeId","1");
                new WebSocketAsyncTask(new CallBack() {
                    @Override
                    public void onSuccess(String message) {
                        Gson gson = new Gson();
                        Result result = gson.fromJson(message, Result.class);
                        if (result.isSuccess()) {
                            Log.i(TAG, result.getMsg());
//                                System.out.println(result.getMsg());

                        }
                    }

                    @Override
                    public void onError(String error) {

                    }
                }).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, "donateBook", JsonUtil.mapToJson(map));

                BookDetailActivity.this.finish();
                break;
            default:
                break;
        }
    }


}
