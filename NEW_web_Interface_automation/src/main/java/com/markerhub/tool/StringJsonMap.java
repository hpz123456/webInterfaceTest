package com.markerhub.tool;


import com.google.gson.Gson;
import com.jayway.jsonpath.JsonPath;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class StringJsonMap {


    //返回数据解析（json）
    public static String analysisJson(String json, String analysisCode) {
        try {
            String analysisValue = JsonPath.read(json, analysisCode);
            return analysisValue;
        } catch (Exception e) {
            return null;
        }
    }

    //String转map
    public static Map<String, Object> StringMap(String jsonString) {
        Gson gson = new Gson();
        Map<String, Object> map = new HashMap<String, Object>();
        map = gson.fromJson(jsonString, map.getClass());
        return map;
    }

    //String转map
    public static Map<String, String> StMap(String jsonString) {
        Gson gson = new Gson();
        Map<String, String> map = new HashMap<String, String>();
        map = gson.fromJson(jsonString, map.getClass());
        return map;
    }

    public static void main(String[] args) {
        String jsonString = "{\"0\":[{\"name\":\"品牌\",\"value\":\"1,2,3,4\"}],\"1\":[{\"name\":\"材质\",\"value\":\"\"},{\"name\":\"风格\",\"value\":\"\"}],\"2\":[{\"name\":\"类型\",\"value\":\"运动,休闲,登山\"}],\"3\":[{\"name\":\"适用季节\",\"value\":\"春,夏,秋,冬\"}],\"4\":[{\"name\":\"生产日期\",\"value\":\"\"},{\"name\":\"结束日期\",\"value\":\"\"}]}";
//        String st = StringJsonMap.analysisJson(jsonString, "$.0[0].name");
//        System.out.println(st);
//        Map<String, Object> map = StringMap("{\"goods_id\":\"140861765\",\"cat_id\":\"210\",\"goods_sn\":\"171073501\",\"goods_sn_back\":\"171073501\",\"goods_upc\":null,\"goods_name\":\"Lace-Up Boxer Swimming Trunks\"}");
        Map<String, Object> map = StringMap(jsonString);
//
        for (String m : map.keySet()) {
//            System.out.println(m);
            List o = (List) map.get(m);
//            System.out.println(map.get(m));
            System.out.println(o.get(0).toString());
        }
        for (String m : map.keySet()) {
//            System.out.println(m);
            List o = (List) map.get(m);
//            System.out.println(map.get(m));
            System.out.println(o.toString());
        }

    }


}
