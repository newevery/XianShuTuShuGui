package cn.com.sino_device.xianshutushugui.WebSocket;


import android.util.Log;

import java.io.UnsupportedEncodingException;
import java.net.URI;

import org.java_websocket.client.WebSocketClient;
import org.java_websocket.drafts.Draft;
import org.java_websocket.framing.Framedata;
import org.java_websocket.handshake.ServerHandshake;


public class MyWebSocketClient extends WebSocketClient {

    private CallBack callBack;

    public MyWebSocketClient(URI serverUri, Draft draft, CallBack callBack) {
        super(serverUri, draft);
        this.callBack = callBack;
    }

    public MyWebSocketClient(URI serverUri, Draft draft) {
        super(serverUri, draft);
    }


    @Override
    public void onOpen(ServerHandshake handshakedata) {
        System.out.println("opened connection");
        // if you plan to refuse connection based on ip or httpfields overload: onWebsocketHandshakeReceivedAsClient
    }

    @Override
    public void onMessage(String message) {
        System.out.println("received  " + message);
        if (message == null || "".equals(message)) {

        } else {
            close();
            callBack.onSuccess(message);
        }

    }

    String message = "";

    @Override
    public void onFragment(Framedata fragment) {

        try {
            message += new String(fragment.getPayloadData().array(), "UTF-8");
            if (fragment.isFin()){
                callBack.onSuccess(message);
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
//        System.out.println("received fragment: " + new String(fragment.getPayloadData().array()));
//
//        Log.i("@@@@@@@@@@@@  ",new String(fragment.getPayloadData().array()));
//        System.out.println(sb);
//        if (new String(fragment.getPayloadData().array()) == null || "".equals(new String(fragment.getPayloadData().array()))) {
//
//        } else {
//            close();
//        sb.append(new String(fragment.getPayloadData().array()));
//            callBack.onSuccess(sb.toString());
////        }
    }

    @Override
    public void onClose(int code, String reason, boolean remote) {
        callBack.onError(reason);
        // The codecodes are documented in class org.java_websocket.framing.CloseFrame
        System.out.println("Connection closed by " + (remote ? "remote peer" : "us"));
    }


    @Override
    public void onError(Exception ex) {
        ex.printStackTrace();
        callBack.onError(ex.toString());
        // if the error is fatal then onClose will be called additionally
    }

    @Override
    public void close() {
        super.close();
        System.out.println("WebSocketClient close");
    }

//
//    public static void main(String[] args) throws URISyntaxException, InterruptedException {
//        try {
//            MyWebSocketClient c = new MyWebSocketClient(new URI("ws://192.168.2.121:8080/jeeplus/syslibSocketServer"), new Draft_17());
//            if (c.connectBlocking()) {
//                UserRegister mUserRegister = new UserRegister();
//                mUserRegister.setName("张三");
//                mUserRegister.setSex("男");
//                mUserRegister.setMobile("13838350239");
//                mUserRegister.setPassword("00000000");
//                mUserRegister.setPhoto("www.baidu.com");
//                mUserRegister.setNo("11111");
//                mUserRegister.setStu_name("张思");
//                mUserRegister.setClasses("3ban");
//                mUserRegister.setDeposit("￥99");
//                mUserRegister.setEmail("zhangsan.qq.com");
//                mUserRegister.setLogin_ip("11.11.11.11");
//                SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//                mUserRegister.setLogin_date(df.format(new Date()) + "");
//                mUserRegister.setCreate_by("self");
//                mUserRegister.setCreate_date(df.format(new Date()) + "");
//
////               Gson gson = new Gson();
////               String msg = gson.toJson(mUserRegister);
////               StringBuffer sb=new StringBuffer();

////               sb.append(msg).insert(sb.indexOf("{")+1,"\"methodName\":\"userRegister\",");
////               System.out.println(sb.toString());
//                String msg1 = mUserRegister.toString();
//                System.out.println(msg1);
//                StringBuffer sb = new StringBuffer();
//                sb.append(msg1).insert(sb.indexOf("{") + 1, "methodName:'userRegister',");
//                System.out.println(sb.toString());
//                c.send(sb.toString());
//                System.out.println("sssssss   " + msg);
//            } else {
//            }
//
//        } catch (URISyntaxException e) {
//            System.out.println("请链接服务器");
//            e.printStackTrace();
//        } catch (InterruptedException e) {
//            System.out.println("请链接服务器");
//            e.printStackTrace();
//        } catch (WebsocketNotConnectedException e) {
//            System.out.println("请链接服务器");
//            e.printStackTrace();
//        }
//    }


}