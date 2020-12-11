package com.markerhub.caseTestNG;


import com.markerhub.ExceptionCustom.CaseAssertException;
import com.markerhub.entity.MyFirstModel;
import com.markerhub.tool.StringJsonMap;

import java.util.List;
import java.util.Map;

public class caseAssert {

    //普通断言
    public static void commonAssert(MyFirstModel myFirstModel) throws CaseAssertException {
        if (myFirstModel.getCommonAssert().isEmpty()) {
            return;
        }
        //将普通断言的值转换为map
        Map<String, String> stringMap = null;
        try {
            stringMap = StringJsonMap.StMap(myFirstModel.getCommonAssert());
        } catch (Exception e) {
            throw new CaseAssertException(myFirstModel, "普通断言格式错误");
        }
        //取出key值
        for (String key : stringMap.keySet()) {
            Object valueSt = null;
            try {
                //取出key对应的结果中的值
                valueSt = StringJsonMap.analysisJsonObject(myFirstModel.getRequestMethod(), key);
            } catch (Exception e) {
                throw new CaseAssertException(myFirstModel, "普通断言key格式错误，或结果中没有对应的值");
            }
            //结果中的值和value进行断言
            if (!((valueSt.toString()).equals(stringMap.get(key)))) {
                throw new CaseAssertException(myFirstModel, valueSt + "!=" + stringMap.get(key) + "不相等,普通断言失败");
            }
        }


    }

    //列表断言
    public static void listAssert(MyFirstModel myFirstModel) throws CaseAssertException {
        if (myFirstModel.getListAssert().isEmpty()) {
            return;
        }
        //将普通断言的值转换为map
        Map<String, String> stringMap = null;
        try {
            stringMap = StringJsonMap.StMap(myFirstModel.getListAssert());
        } catch (Exception e) {
            throw new CaseAssertException(myFirstModel, "列表断言格式错误");
        }
        //取出key值
        for (String key : stringMap.keySet()) {
            List valueList = null;
            try {
                //取出key对应的结果中的值
                valueList = StringJsonMap.analysisJsonList(myFirstModel.getRequestMethod(), key);
            } catch (Exception e) {
                throw new CaseAssertException(myFirstModel, "列表断言key格式错误，或结果中没有对应的值");
            }
            //结果中的值和value进行断言
            for (Object listOb : valueList) {
                if (listOb.toString().equals(stringMap.get(key))) {
                    throw new CaseAssertException(myFirstModel, listOb.toString() + "!=" + stringMap.get(key) + "不相等,列表断言失败");
                }
            }
        }
    }

    //列表模糊断言
    public static void listDimAssert(MyFirstModel myFirstModel) throws CaseAssertException {
        if (myFirstModel.getListAssert().isEmpty()) {
            return;
        }
        //将普通断言的值转换为map
        Map<String, String> stringMap = null;
        try {
            stringMap = StringJsonMap.StMap(myFirstModel.getListAssert());
        } catch (Exception e) {
            throw new CaseAssertException(myFirstModel, "列表模糊断言格式错误");
        }
        //取出key值
        for (String key : stringMap.keySet()) {
            List valueList = null;
            try {
                //取出key对应的结果中的值
                valueList = StringJsonMap.analysisJsonList(myFirstModel.getRequestMethod(), key);
            } catch (Exception e) {
                throw new CaseAssertException(myFirstModel, "列表模糊断言key格式错误，或结果中没有对应的值");
            }
            //结果中的值和value进行断言
            for (Object listOb : valueList) {
                if (listOb.toString().contains(stringMap.get(key))) {
                    throw new CaseAssertException(myFirstModel, listOb.toString() + "!^=" + stringMap.get(key) + "不相等,列表模糊断言失败");
                }
            }
        }
    }
}
