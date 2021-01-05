package com.markerhub.caseTestNG;

import cn.hutool.json.JSONObject;
import com.markerhub.ExceptionCustom.BusinessLogicException;
import com.markerhub.entity.MyFirstModel;
import com.markerhub.entity.caseModel;
import com.markerhub.entity.requestReturn;
import com.markerhub.request_interface.requestPostGet;
import com.markerhub.tool.StringJsonMap;
import com.markerhub.tool.StringUtils;
import org.apache.http.client.CookieStore;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class parameterDispose {

    public static MyFirstModel startRequest(MyFirstModel my) throws Exception {
        //入参
        String url = my.getUrl();//url
        Map<String, String> header = null;//请求头
        Map<String, Object> params = null; //params
        String data = "";//data
        CookieStore cookieStore = null;//cookies
        String getCookie = my.getGetCookie();//是否获取cookie
        Map<String, Object> formData = null;//formData请求体

        //判断formData是否为空
        if (my.getFormData() != null && !my.getFormData().isEmpty()) {
            //不为空，将实例中的formData转换为map赋值给入参formData
            try {
                formData = StringJsonMap.st_Map(my.getFormData());
            } catch (Exception e) {
                throw new BusinessLogicException(my, "formData格式错误");
            }
        }


        //判断请求头是否为空
        if (!my.getHeader().isEmpty()) {
            //不为空，将实例中的header转换为map赋值给入参header
            try {
                header = StringJsonMap.StMap(my.getHeader());
            } catch (Exception e) {
                throw new BusinessLogicException(my, "header格式错误");
            }
        }

        //判断是否有token，如果有，就添加token，并放入header中
        if (!my.getToken().isEmpty()) {
            GetSetData.setToken(my);
            if (header != null) {
                //如果header不为空，将token添加进去
                header.putAll(my.getParameterToken());
            } else {
                //如果header为空，将token直接赋值
                header = my.getParameterToken();
            }
        }

        //cookie处理
        if (!my.getCookie().isEmpty()) {
            cookieStore = GetSetData.setCookie(my);
        }

        //定义一个依赖数据map
        Map<String, String> ylmap = null;
        //获取数据依赖
        if (!my.getDependData().isEmpty()) {
            //获得依赖数据
            ylmap = GetSetData.dataDependence(my, my.getDependData());
        }
        //处理params
        if (!my.getParams().isEmpty()) {
            try {
                params = StringJsonMap.st_Map(my.getParams());
            } catch (Exception e) {
                throw new BusinessLogicException(my, "params格式错误");
            }

        }

        //将依赖数据放入请求体param
        if (ylmap != null && my.getRequestMethod().equals("GET")) {
            if (params != null) {
                for (String s : ylmap.keySet()) {
                    params.put(s, ylmap.get(s));
                }
            } else {
                params = new HashMap<String, Object>();
                for (String s : ylmap.keySet()) {
                    params.put(s, ylmap.get(s));
                }
            }
        }

        //处理data
        if (!my.getData().isEmpty()) {
            data = my.getData();
        }

        //将依赖数据放入请求体data
        if (ylmap != null && my.getRequestMethod().equals("POST")) {
            try {
                if (data.isEmpty()) {
                    data = StringUtils.replaceBlank(ylmap.toString().replace("=", ":"));
                } else {
                    //将data转换成map
                    Map<String, String> dataMap = StringJsonMap.StMap(my.getData());
                    for (String key : dataMap.keySet()) {
                        String dataMapSt = dataMap.get(key);
                        if (((dataMapSt.substring(dataMapSt.length() - 1)).equals("\"")) && ((dataMapSt.substring(0, 1)).equals("\""))) {
                            dataMapSt = dataMapSt.substring(1, dataMapSt.length() - 1);
                        }
                        dataMap.put(key, dataMapSt);
                    }

                    dataMap.putAll(ylmap);
                    JSONObject jsonObject = new JSONObject(dataMap);
                    data = jsonObject.toString();
//                    data = StringUtils.replaceBlank(dataMap.toString().replace("=", ":"));
                }
            } catch (Exception e) {
                throw new BusinessLogicException(my, "data数据格式错误");
            }


        }


//        //入参数据都为空
//        if (my.getRequestMethod().equals("post") && my.getToken().isEmpty() && my.getCookie().isEmpty()) {
//            requestPostGet.requestPost(
//                    my.getUrl(),
//                    StringJsonMap.StMap(my.getHeader()),
//                    StringJsonMap.StMap(my.getParams()),
//                    my.getData(),
//                    null,
//                    null);
//        }
//        //cookie不为空
//        if (my.getRequestMethod().equals("post") && my.getToken().isEmpty() && !my.getCookie().isEmpty()) {
//            requestPostGet.requestPost(
//                    my.getUrl(),
//                    StringJsonMap.StMap(my.getHeader()),
//                    StringJsonMap.StMap(my.getParams()),
//                    my.getData(),
//                    my.getCookieStore(),
//                    null);
//        }
        //定义返回值

        requestReturn requestReturn = null;
        if (my.getRequestMethod().equals("POST")) {
            try {
                requestReturn = requestPostGet.requestPost(my.getUrl(), header, params, data, cookieStore, getCookie, formData);
            } catch (Exception e) {
                //如果请求中出现异常，将直接把抛出来的异常写入结果，并将异常抛出
                my.setAssertResult("N");
                my.setRequestResult(e.toString());
                caseModel.returnTestCase.add(my);
                throw e;
            }

        } else if (my.getRequestMethod().equals("GET")) {
            try {
                requestReturn = requestPostGet.requestGet(url, header, params, getCookie, cookieStore);
            } catch (Exception e) {
                //如果请求中出现异常，将直接把抛出来的异常写入结果，并将异常抛出
                my.setAssertResult("N");
                my.setRequestResult(e.toString());
                caseModel.returnTestCase.add(my);
                throw e;
            }
        }
        GetSetData.getData(requestReturn, my);
        //判断需不需要获取cookie
        if (!my.getGetCookie().isEmpty()) {
            GetSetData.getCookie(requestReturn, my);
        }
        return my;
    }

    public static void main(String[] args) {
        Map<String, List<String>> map = new HashMap<>();
        for (int i = 0; i < 5; i++) {
            List<String> list = new ArrayList<>();
            list.add("eqw");
            list.add("eqer");
            list.add("gfhf");
            map.put(i + "ewq", list);
        }

        System.out.println(StringUtils.replaceBlank(map.toString().replace("=", ":")));
    }

}
