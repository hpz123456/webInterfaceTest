package com.markerhub.caseTestNG;

import com.markerhub.entity.MyFirstModel;
import com.markerhub.entity.caseModel;
import com.markerhub.entity.requestReturn;
import com.markerhub.tool.StringJsonMap;
import org.apache.http.client.CookieStore;

import java.util.HashMap;
import java.util.Map;

public class GetSetData {
    //取到结果
    public static void getData(requestReturn re, MyFirstModel myFirstModel) {
        myFirstModel.setRequestResult(re.getCaseBody());
    }

    //得到cookie
    public static void getCookie(requestReturn re, MyFirstModel myFirstModel) {
        myFirstModel.setCookieStore(re.cookieList);
    }


    //获取cookie
    public static CookieStore setCookie(MyFirstModel myFirstModel) {
        //去returnTestCase（已结束的case）
        for (MyFirstModel myFirstModelList : caseModel.returnTestCase) {
            if (myFirstModelList.getCaseId().equals(myFirstModel.getCookie())) {
                return myFirstModelList.getCookieStore();
            }
        }
        return null;
    }

    //获取tongken
    public static void setToken(MyFirstModel myFirstModel) {
        //获取token条件
        Map<String, Object> map = StringJsonMap.StringMap(myFirstModel.getToken());
        //设置一个token的map
        Map<String, String> token = new HashMap<String, String>();
        //去returnTestCase（已结束的case）
        for (MyFirstModel myFirstModelList : caseModel.returnTestCase) {
            if (myFirstModelList.getCaseId().equals(map.get("caseId"))) {
                token.put((String) map.get("name"), StringJsonMap.analysisJson(myFirstModelList.getData(), (String) map.get("value")));
            }
        }
        myFirstModel.setParameterToken(token);
    }

    //数据依赖提取
    public static Map<String, String> dataDependence(String datap) {
        //将数据转换为map
        Map<String, String> map = StringJsonMap.StMap(datap);

        //依赖数据map
        Map<String, String> ylmap = new HashMap<String, String>();
        //遍历map，查找对应数据
        for (String s : map.keySet()) {
            for (MyFirstModel myFirstModelList : caseModel.returnTestCase) {
                if (myFirstModelList.getCaseId().equals(s)) {
                    String[] split = map.get(s).split("-");
                    //解析出value
                    String value = StringJsonMap.analysisJson(myFirstModelList.getDependData(), split[1]);
                    ylmap.put(split[0], value);
                }
            }
        }
        return ylmap;
    }

}
