package cn.com.sino_device.xianshutushugui.WebSocket;

import android.os.AsyncTask;
import android.util.Log;

import org.java_websocket.drafts.Draft_17;
import org.java_websocket.exceptions.WebsocketNotConnectedException;

import java.net.URI;
import java.net.URISyntaxException;

import cn.com.sino_device.xianshutushugui.util.SPUtils;

/**
 * Created by Android Studio.
 *
 * @author affe
 * @date 2018/7/2
 */
public class WebSocketAsyncTask extends AsyncTask<String, Integer, CallBack> {

    private static MyWebSocketClient c;
    private CallBack callBack;
    private static String TAG = "WebSocketAsyncTask__";

    public WebSocketAsyncTask(CallBack callBack) {
        this.callBack = callBack;
    }

    @Override
    protected void onProgressUpdate(Integer... values) {
        super.onProgressUpdate(values);
    }

    @Override
    protected CallBack doInBackground(String... strings) {
        // TODO: 18-7-17请求服务器获取图书类目  192.168.14.14:8080
        try {
            if (c == null || c.isClosed()) {
                c = new MyWebSocketClient(new URI("ws://192.168.14.14:8080/jeeplus/syslibSocketServer"), new Draft_17());
            }
            c = new MyWebSocketClient(new URI("ws://192.168.14.14:8080/jeeplus/syslibSocketServer"), new Draft_17(), callBack);
            if (c.connectBlocking()) {
                StringBuffer sb = new StringBuffer();
//               sb.append(strings[1]).insert(sb.indexOf("{") + 1, "methodName:'" + strings[0] + "',");

                if (!"".equals(strings[0]) && strings[0] != null) {
                    sb.append(strings[1]).insert(sb.indexOf("{") + 1, "methodName:'" + strings[0] + "',");
                }
                if (sb.toString().endsWith(",}")) {
                    sb.replace(sb.length() - 2, sb.length() - 1, "");
                }
                Log.i(TAG, sb.toString());
                c.send(sb.toString());
            } else {
                Log.i(TAG, "WebsocketNotConnectedException");
                if (c == null || c.isClosed()) {
                    c = new MyWebSocketClient(new URI("ws://192.168.14.14:8080/jeeplus/syslibSocketServer"), new Draft_17());
                }
                c = new MyWebSocketClient(new URI("ws://192.168.14.14:8080/jeeplus/syslibSocketServer"), new Draft_17(), callBack);
                if (c.connectBlocking()) {
                    StringBuffer sb = new StringBuffer();
//               sb.append(strings[1]).insert(sb.indexOf("{") + 1, "methodName:'" + strings[0] + "',");
                    if (!"".equals(strings[0]) && strings[0] != null) {
                        sb.append(strings[1]).insert(sb.indexOf("{") + 1, "methodName:'" + strings[0] + "',");
                    }
                    if (sb.toString().endsWith(",}")) {
                        sb.replace(sb.length() - 2, sb.length() - 1, "");
                    }
                    Log.i(TAG, sb.toString());
                    c.send(sb.toString());
                } else {
                    Log.i(TAG, "WebsocketNotConnectedException");
                    Log.i(TAG, "WebsocketNotConnectedException");
                    if (c == null || c.isClosed()) {
                        c = new MyWebSocketClient(new URI("ws://192.168.14.14:8080/jeeplus/syslibSocketServer"), new Draft_17());
                    }
                    c = new MyWebSocketClient(new URI("ws://192.168.14.14:8080/jeeplus/syslibSocketServer"), new Draft_17(), callBack);
                    if (c.connectBlocking()) {
                        StringBuffer sb = new StringBuffer();
//               sb.append(strings[1]).insert(sb.indexOf("{") + 1, "methodName:'" + strings[0] + "',");
                        if (!"".equals(strings[0]) && strings[0] != null) {
                            sb.append(strings[1]).insert(sb.indexOf("{") + 1, "methodName:'" + strings[0] + "',");
                        }
                        if (sb.toString().endsWith(",}")) {
                            sb.replace(sb.length() - 2, sb.length() - 1, "");
                        }
                        Log.i(TAG, sb.toString());
                        c.send(sb.toString());
                    } else {
                        Log.i(TAG, "WebsocketNotConnectedException   服务器故障请稍后再试");
                    }
                }
            }


        } catch (URISyntaxException e) {
            callBack.onError(e.toString());
            e.printStackTrace();
        } catch (InterruptedException e) {
            callBack.onError(e.toString());
            e.printStackTrace();
        } catch (WebsocketNotConnectedException e) {
            callBack.onError(e.toString());
            e.printStackTrace();
        }
        return callBack;
    }
}
