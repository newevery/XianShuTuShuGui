package cn.com.sino_device.xianshutushugui.WebSocket;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;


import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Map;

public class JsonUtil {
    private static String TAG = "JsonUtil__ ";

    public static void jsonParse(String date) {
        //判断数据是空
        if (date != null) {
            try {
                //将字符串转换成jsonObject对象
                JSONObject jsonObject = new JSONObject(date);
                //获取返回数据中flag的值
                String resultCode = jsonObject.getString("success");
                //如果返回的值是success则正确
                if (resultCode.equals("true")) {
                    Log.i(TAG, "msg");
                }

            } catch (JSONException e) {
                e.printStackTrace();
            }


        }
    }

    public static String mapToJson(Map<String, String> map) {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        for (Map.Entry<String, String> entry : map.entrySet()) {
            String key = entry.getKey().toString();
            String value = entry.getValue().toString();
            sb.append(key + ":" + "'" + value + "'" + ',');
        }
        sb.append("}");
        return sb.toString();
    }
    public static <T> ArrayList<T> jsonToArrayList(String json, Class<T> clazz)
    {
        Type type = new TypeToken<ArrayList<JsonObject>>()
        {}.getType();
        ArrayList<JsonObject> jsonObjects = new Gson().fromJson(json, type);
        ArrayList<T> arrayList = new ArrayList<>();
        for (JsonObject jsonObject : jsonObjects)
        {
            arrayList.add(new Gson().fromJson(jsonObject, clazz));
        }
        return arrayList;
    }
}
