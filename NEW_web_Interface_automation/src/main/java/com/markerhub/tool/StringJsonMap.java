package com.markerhub.tool;


import com.jayway.jsonpath.JsonPath;

public class StringJsonMap {



    //返回数据解析（json）
    public static String analysisJson(String json,String analysisCode){
        try {
            String analysisValue = JsonPath.read(json, analysisCode);
            return analysisValue;
        }catch (Exception e){
            return null;
        }
    }


    public static void main(String[] args) {
        String jsonString = "{\"0\":[{\"name\":\"品牌\",\"value\":\"1,2,3,4\"}],\"1\":[{\"name\":\"材质\",\"value\":\"\"},{\"name\":\"风格\",\"value\":\"\"}],\"2\":[{\"name\":\"类型\",\"value\":\"运动,休闲,登山\"}],\"3\":[{\"name\":\"适用季节\",\"value\":\"春,夏,秋,冬\"}],\"4\":[{\"name\":\"生产日期\",\"value\":\"\"},{\"name\":\"结束日期\",\"value\":\"\"}]}";
        String st = StringJsonMap.analysisJson(jsonString,"$.0[0].name");
        System.out.println(st);

    }





}
