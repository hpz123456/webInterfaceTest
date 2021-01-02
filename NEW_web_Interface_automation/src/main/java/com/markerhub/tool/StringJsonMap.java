package com.markerhub.tool;


import com.google.common.reflect.TypeToken;
import com.google.gson.*;
import com.jayway.jsonpath.JsonPath;

import java.lang.reflect.Type;
import java.util.*;

public class StringJsonMap {
    public static Gson getGson() {
        Gson gson = new GsonBuilder().registerTypeAdapter(HashMap.class, new JsonDeserializer<HashMap>() {
            @Override
            public HashMap<String, Object> deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
                HashMap<String, Object> resultMap = new HashMap<>();
                JsonObject jsonObject = json.getAsJsonObject();
                Set<Map.Entry<String, JsonElement>> entrySet = jsonObject.entrySet();
                for (Map.Entry<String, JsonElement> entry : entrySet) {
                    resultMap.put(entry.getKey(), entry.getValue());
                }
                return resultMap;
            }

        }).create();
        return gson;
    }





    //返回数据解析（json）
    public static String analysisJson(String json, String analysisCode) {
        try {
            String analysisValue = JsonPath.read(json, analysisCode);
            return analysisValue;
        } catch (Exception e) {
            return null;
        }
    }

    //返回数据解析（json）
    public static Object analysisJsonObject(String json, String analysisCode) {
        Object analysisValue = JsonPath.read(json, analysisCode);
        return analysisValue;
    }

    //返回数据解析（json）
    public static List analysisJsonList(String json, String analysisCode) {
        List analysisValue = JsonPath.read(json, analysisCode);
        return analysisValue;
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
//        Gson gson = new Gson();
        Map<String, JsonElement> map = new HashMap<String, JsonElement>();
        map = StringJsonMap.getGson().fromJson(jsonString, map.getClass());
        Map<String, String> st_map = new HashMap<String, String>();
        for (String o:map.keySet()) {
            st_map.put(o,map.get(o).toString());
        }
        return st_map;
    }
    @SuppressWarnings("unchecked")
    public static Map<String, Object> st_Map(String jsonString) {
//        Gson gson = new Gson();
        HashMap<String, Object> map = getGson().fromJson(jsonString, HashMap.class);
        return map;
    }

    @SuppressWarnings("unchecked")
    public static void main(String[] args) {
//        String jsonString = "{\"0\":[{\"name\":\"品牌\",\"value\":\"1,2,3,4\"}],\"1\":[{\"name\":\"材质\",\"value\":\"\"},{\"name\":\"风格\",\"value\":\"\"}],\"2\":[{\"name\":\"类型\",\"value\":\"运动,休闲,登山\"}],\"3\":[{\"name\":\"适用季节\",\"value\":\"春,夏,秋,冬\"}],\"4\":[{\"name\":\"生产日期\",\"value\":\"\"},{\"name\":\"结束日期\",\"value\":\"\"}]}";
//        String st = "{\"store\":{\"book\":[{\"category\":\"reference\",\"author\":\"Nigel Rees\",\"title\":\"Sayings of the Century\",\"price\":8.95},{\"category\":\"fiction\",\"author\":\"Evelyn Waugh\",\"title\":\"Sword of Honour\",\"price\":12.99},{\"category\":\"fiction\",\"author\":\"Herman Melville\",\"title\":\"Moby Dick\",\"isbn\":\"0-553-21311-3\",\"price\":8.99},{\"category\":\"fiction\",\"author\":\"J. R. R. Tolkien\",\"title\":\"The Lord of the Rings\",\"isbn\":\"0-395-19395-8\",\"price\":22.99}],\"bicycle\":{\"color\":\"red\",\"price\":19.95}},\"expensive\":10}";
//        System.out.println(st);
//        Map<String, Object> map = StringMap("{\"goods_id\":\"140861765\",\"cat_id\":\"210\",\"goods_sn\":\"171073501\",\"goods_sn_back\":\"171073501\",\"goods_upc\":null,\"goods_name\":\"Lace-Up Boxer Swimming Trunks\"}");
//        Map<String, Object> map = StringMap(jsonString);
////
//        for (String m : map.keySet()) {
////            System.out.println(m);
//            List o = (List) map.get(m);
////            System.out.println(map.get(m));
//            System.out.println(o.get(0).toString());
//        }
//        for (String m : map.keySet()) {
////            System.out.println(m);
//            List o = (List) map.get(m);
////            System.out.println(map.get(m));
//            System.out.println(o.toString());
//        }
//        System.out.println(jsonString);
//        String ss = "$.name";
//        Object s = StringJsonMap.analysisJsonObject(jsonString, ss);
//        System.out.println(s.toString());

//        String JsonString = "{\n" +
//                "    \"store\": {\n" +
//                "        \"book\": [\n" +
//                "            {\n" +
//                "                \"category\": \"reference\",\n" +
//                "                \"author\": \"Nigel Rees\",\n" +
//                "                \"title\": \"Sayings of the Century\",\n" +
//                "                \"price\": 8.95\n" +
//                "            },\n" +
//                "            {\n" +
//                "                \"category\": \"fiction\",\n" +
//                "                \"author\": \"Evelyn Waugh\",\n" +
//                "                \"title\": \"Sword of Honour\",\n" +
//                "                \"price\": 12.99\n" +
//                "            },\n" +
//                "            {\n" +
//                "                \"category\": \"fiction\",\n" +
//                "                \"author\": \"Herman Melville\",\n" +
//                "                \"title\": \"Moby Dick\",\n" +
//                "                \"isbn\": \"0-553-21311-3\",\n" +
//                "                \"price\": 8.99\n" +
//                "            },\n" +
//                "            {\n" +
//                "                \"category\": \"fiction\",\n" +
//                "                \"author\": \"J. R. R. Tolkien\",\n" +
//                "                \"title\": \"The Lord of the Rings\",\n" +
//                "                \"isbn\": \"0-395-19395-8\",\n" +
//                "                \"price\": 22.99\n" +
//                "            }\n" +
//                "        ],\n" +
//                "        \"bicycle\": {\n" +
//                "            \"color\": \"red\",\n" +
//                "            \"price\": 19.95\n" +
//                "        }\n" +
//                "    },\n" +
//                "    \"expensive\": \"10\"\n" +
//                "}";
//
////        // 输出 所有 book 对象
//        String jsonPath = "$.store.book.[*]";
////        List msg = JsonPath.read(JsonString, jsonPath);
////        System.out.println(msg.toString());
////        //[{"category":"reference","author":"Nigel Rees","title":"Sayings of the Century","price":8.95},{"category":"fiction","author":"Evelyn Waugh","title":"Sword of Honour","price":12.99},{"category":"fiction","author":"Herman Melville","title":"Moby Dick","isbn":"0-553-21311-3","price":8.99},{"category":"fiction","author":"J. R. R. Tolkien","title":"The Lord of the Rings","isbn":"0-395-19395-8","price":22.99}]
////
////        //输出 所有 author 字段
////        jsonPath = "$.store.book[*].author";
////        List readMsg = JsonPath.read(JsonString, jsonPath);
////        System.out.println(readMsg.toString());
////        //["Nigel Rees","Evelyn Waugh","Herman Melville","J. R. R. Tolkien"]
////
////        //输出的 集合不存在 不会空指针 List = []
////        jsonPath = "$.store.book[*].author111";
////        List readMsg1 = JsonPath.read(JsonString, jsonPath);
////        System.out.println(readMsg1.toString());
////        // []
//
//        // 输出 单个字段 接受需要指定类型
//        jsonPath = "$.store.book";
//        Object readMsg2 = StringJsonMap.analysisJsonObject(JsonString, jsonPath);
//        System.out.println(readMsg2);
//        // 10

//        // 取出list 用LinkedHashMap 接收
//        String jsonPath4 = "$.store.book";
//        List<LinkedHashMap> msg4 = JsonPath.read(JsonString, jsonPath4);
//        System.out.println(msg4.toString());
//


        String aaa = "{ a: 1, b: 2.3 }";
        HashMap<String, Object> map = getGson().fromJson(aaa, HashMap.class);
        for (String st: map.keySet()) {
            String s = map.get(st).toString();
            System.out.println(s);
        }
        System.out.println(map);
    }


}
