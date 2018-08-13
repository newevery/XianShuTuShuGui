package cn.com.sino_device.xianshutushugui.WebSocket;

import android.util.Log;

import org.java_websocket.drafts.Draft_17;
import org.java_websocket.exceptions.WebsocketNotConnectedException;

import java.net.URI;
import java.net.URISyntaxException;

public class WebSocketInstance implements CallBack {
    private static MyWebSocketClient c;
    private static String TAG = "WebSocketInstance__ ";

    private WebSocketInstance() {
    }

    public static void wsConnect(String path, String object, CallBack callBack) {
        try {
            if (c == null || c.isClosed()) {
                c = new MyWebSocketClient(new URI("ws://118.24.160.41:8080/jeeplus/syslibSocketServer"), new Draft_17());
            }
            c = new MyWebSocketClient(new URI("ws://118.24.160.41:8080/jeeplus/syslibSocketServer"), new Draft_17(), callBack);
            c.connectBlocking();

            StringBuffer sb = new StringBuffer(object);
//            sb.append(strings[1]).insert(sb.indexOf("{") + 1, "methodName:'" + strings[0] + "',");
            String mobile = "18603195364";
            if (!"".equals(mobile) && mobile != null) {
                sb=sb.insert(sb.indexOf("{") + 1, "mobile:'" + mobile + "',").insert(sb.indexOf("{") + 1, "methodName:'" + path + "',");
            }
            if (sb.toString().endsWith(",}")) {
                sb.replace(sb.length() - 2, sb.length() - 1, "");
            }
            Log.i(TAG, sb.toString());
            c.send(sb.toString());

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


    }


    @Override
    public void onSuccess(String message) {
    }

    @Override
    public void onError(String error) {
    }
}