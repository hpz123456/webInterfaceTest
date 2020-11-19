package com.markerhub.tool;

import com.markerhub.entity.MyFirstModel;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class test {

    public static void main(String[] args) {

        long l = System.currentTimeMillis();
        System.out.println(l+"");

//        ExcelUtil excelUtil = new ExcelUtil();
//        String path = "D:\\my_web_interface\\NEW_web_Interface_automation\\src\\main\\java\\com\\markerhub\\file_Excel";
//        List<Map<String,String>> list = excelUtil.readExcel2007(path,"测试用例test.xlsx",0,0,1);
//        List<MyFirstModel> myFirstModels_list = new ArrayList<MyFirstModel>();
//        try {
//            File target = new File(path, "测试用例test.xlsx");
//            InputStream ins = new FileInputStream(target);
//            XSSFWorkbook wb = new XSSFWorkbook(ins);
//            ins.close();
//            // 得到Excel工作表对象
//            int i = wb.getNumberOfSheets();
//            System.out.println(i);
////            XSSFSheet sheet = wb.getSheetAt(5);
//
//
//        }catch (Exception e){
//            System.out.println("异常");
//        }


//        boolean t = list.isEmpty();
//        System.out.println(t);
//        for (Map<String,String> map:list) {

//            System.out.println(map.keySet());
//            System.out.println(map.values());
//            System.out.println(map.get("用例id"));

//        }

//        for (Map<String,String> model:list) {
//            MyFirstModel myFirstModel = new MyFirstModel();
//            myFirstModel.setCaseId(model.get("用例id"));
//            myFirstModel.setProjectName(model.get("项目名称"));
//            myFirstModel.setSubordinateModule(model.get("所属模块"));
//            myFirstModel.setCaseName(model.get("用例名称"));
//            myFirstModel.setUrl(model.get("URL"));
//            myFirstModel.setHeader(model.get("header请求头"));
//            myFirstModel.setData(model.get("data请求体"));
//            myFirstModel.setParams(model.get("params请求体"));
//            myFirstModel.setRequestMethod(model.get("请求方式"));
//            myFirstModel.setCookie(model.get("cookies"));
//            myFirstModel.setCaseDescription(model.get("用例描述"));
//            myFirstModel.setToken(model.get("token"));
//            myFirstModel.setDependData(model.get("数据依赖"));
//            myFirstModel.setListAssert(model.get("列表断言"));
//            myFirstModel.setListVagueAssert(model.get("列表模糊断言"));
//            myFirstModel.setCommonAssert(model.get("普通断言"));
//            myFirstModel.setGetCookie(model.get("获取cookies"));
//            myFirstModel.setRemark(model.get("备注"));
//            myFirstModel.setEditor(model.get("编写人"));
//            myFirstModel.setPerformer(model.get("执行人"));
//            myFirstModel.setId(MathUtils.getPrimaryKey());
//            myFirstModels_list.add(myFirstModel);
//        }
//
//        for (MyFirstModel m : myFirstModels_list) {
//            System.out.println(m.toString());
//        }
    }


}
