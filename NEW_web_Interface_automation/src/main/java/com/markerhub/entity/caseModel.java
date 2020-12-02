package com.markerhub.entity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class caseModel {

    public static List<MyFirstModel> testCase;
    public static List<MyFirstModel> returnTestCase;

    public static void listStort() {
        //接受通用用例的list
        List<MyFirstModel> myFirstModelsTY = new ArrayList<MyFirstModel>();
        //接受非通用用例的id
        List<String> caseNameid = new ArrayList<String>();
        //接收普通用例的list
        List<List<MyFirstModel>> PTCaseList = new ArrayList<List<MyFirstModel>>();

        //吧名字分类
        for (MyFirstModel my : testCase) {
            String[] st = my.getCaseId().split("-");
            //将TY用例筛选出来
            if (st[0].equals("TY")) {
                myFirstModelsTY.add(my);
            } else {
                //将普通用例筛选出来
                caseNameid.add(st[0]);
            }
        }
        //去重
        List<String> caseNameIdDe = deWeigjt(caseNameid);
        //将用例名相同的放在一起
        for (String st : caseNameIdDe) {
            List<MyFirstModel> myf = new ArrayList<MyFirstModel>();
            for (MyFirstModel my : testCase) {
                if (st.equals(my.getCaseId().split("-")[0])) {
                    myf.add(my);
                }
            }
            PTCaseList.add(myf);
        }
        //清空testCase
        testCase.clear();
        //将TY排序，并挨个放入testCase
        List<MyFirstModel> TY = sourtListMyFirstModelTY(myFirstModelsTY);
        testCseSourt(TY);
        //将PT排序，并挨个放入testCase
        for (List<MyFirstModel> m : PTCaseList) {
            List<MyFirstModel> PT = sourtListMyFirstModelTY(m);
            testCseSourt(PT);
        }
    }

    //字符串去重
    public static List<String> deWeigjt(List<String> list) {
        List<String> numbersList = new ArrayList<>(list);
//        System.out.println(numbersList);
        List<String> listWithoutDuplicates = numbersList.stream().distinct().collect(Collectors.toList());

        return listWithoutDuplicates;
    }

    //冒泡排序,按照编号顺序排序PT
    public static List<MyFirstModel> sourtListMyFirstModelPT(List<MyFirstModel> MyFirstModelList) {
        for (int i = 0; i < MyFirstModelList.size(); i++) {
            for (int j = 0; j < i; j++) {
                if (Integer.valueOf(MyFirstModelList.get(j).getCaseId().split("-")[1]) > Integer.valueOf(MyFirstModelList.get(i).getCaseId().split("-")[1])) {
                    Collections.swap(MyFirstModelList, i, j);
                }
            }
        }
        return MyFirstModelList;
    }

    //冒泡排序,按照编号顺序排序TY
    public static List<MyFirstModel> sourtListMyFirstModelTY(List<MyFirstModel> MyFirstModelList) {
        for (int i = 0; i < MyFirstModelList.size(); i++) {
            for (int j = 0; j < i; j++) {
                if (Integer.valueOf(MyFirstModelList.get(j).getCaseId().split("-")[2]) > Integer.valueOf(MyFirstModelList.get(i).getCaseId().split("-")[2])) {
                    Collections.swap(MyFirstModelList, i, j);
                }
            }
        }
        return MyFirstModelList;
    }

    //将用例排序后重新放入testCase
    public static void testCseSourt(List<MyFirstModel> myFirstModels) {
        for (MyFirstModel my : myFirstModels) {
            testCase.add(my);
        }
    }


    public static void main(String[] args) {
//        String st = "02";
//
//        System.out.println(Integer.valueOf(st));
        List<List<MyFirstModel>> PTCaseList = new ArrayList<List<MyFirstModel>>();
        for (int i = 0; i < 3; i++) {
            List<MyFirstModel> myf = new ArrayList<MyFirstModel>();
            PTCaseList.add(myf);
        }
        System.out.println(PTCaseList.size());

    }


}
