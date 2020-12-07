package com.markerhub.caseTestNG;

import com.markerhub.ExceptionCustom.BusinessLogicException;
import com.markerhub.entity.MyFirstModel;
import com.markerhub.entity.caseModel;
import com.markerhub.entity.requestReturn;
import com.markerhub.tool.StringJsonMap;
import org.apache.http.client.CookieStore;

import java.util.HashMap;
import java.util.List;
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
    public static CookieStore setCookie(MyFirstModel myFirstModel) throws BusinessLogicException {
        //去returnTestCase（已结束的case）
        CookieStore cookieStore = null;
        for (MyFirstModel myFirstModelList : caseModel.returnTestCase) {
            if (myFirstModelList.getCaseId().equals(myFirstModel.getCookie())) {
                cookieStore = myFirstModelList.getCookieStore();
                break;
            }
        }
        if (cookieStore == null) {
            throw new BusinessLogicException(myFirstModel, "cookie没有对应的用例");
        }
        return cookieStore;
    }

    //获取tongken
    public static void setToken(MyFirstModel myFirstModel) throws BusinessLogicException {
        //获取token条件
        Map<String, Object> map = null;
        try {
            map = StringJsonMap.StringMap(myFirstModel.getToken());
        } catch (Exception e) {
            throw new BusinessLogicException(myFirstModel, "token格式错误");
        }

        try {
            //设置一个token的map
            Map<String, String> token = new HashMap<String, String>();
            //去returnTestCase（已结束的case）
            for (MyFirstModel myFirstModelList : caseModel.returnTestCase) {
                if (myFirstModelList.getCaseId().equals(map.get("caseId"))) {
                    token.put((String) map.get("name"), StringJsonMap.analysisJson(myFirstModelList.getData(), (String) map.get("value")));
                }
            }
            myFirstModel.setParameterToken(token);
        } catch (Exception e) {
            throw new BusinessLogicException(myFirstModel, "token没有符合该编号的测试用例");
        }

    }

    //数据依赖提取
    public static Map<String, String> dataDependence(MyFirstModel myFirstModel, String datap) throws BusinessLogicException {
        //计数器
        Integer count = 0;
        //依赖数据map
        Map<String, String> ylmap = new HashMap<String, String>();
        try {

            //依赖数据解析过后的数据
            Map<String, List> ym = new HashMap<String, List>();
            //将数据转换为map
            Map<String, Object> map = StringJsonMap.StringMap(datap);
            //将map数据的value解析为单个数据String类型
            for (String m : map.keySet()) {
                ym.put(m, (List) map.get(m));
                count = count + ((List) map.get(m)).size();
            }


            //遍历map，查找对应数据
            for (String s : ym.keySet()) {
                for (MyFirstModel myFirstModelList : caseModel.returnTestCase) {
                    if (myFirstModelList.getCaseId().equals(s)) {
                        //循环value的list集合
                        for (Object o : ym.get(s)) {
                            String[] split = (o.toString()).split("-");
                            //解析出value
                            String value = StringJsonMap.analysisJson(myFirstModelList.getDependData(), split[1]);
                            ylmap.put(split[0], value);
                        }

                    }
                }
            }
        } catch (Exception e) {
            throw new BusinessLogicException(myFirstModel, "依赖数据格式错误");
        }
        if (ylmap.size() == 0) {
            throw new BusinessLogicException(myFirstModel, "全部依赖数据未找到");
        }
        if (count != ylmap.size()) {
            throw new BusinessLogicException(myFirstModel, "部分依赖数据未找到");
        }
        return ylmap;
    }


}
