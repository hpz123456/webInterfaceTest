package com.markerhub.tool;

import org.apache.poi.ss.usermodel.Workbook;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;

public class test {

    public static void main(String[] args) {
        ExcelUtil excelUtil = new ExcelUtil();
        String path = "D:\\interface_case\\NEW_web_Interface_automation\\src\\main\\java\\com\\markerhub\\file_Excel";
        List<Map<String,String>> list = excelUtil.readExcel2007(path,"新建 XLSX 工作表.xlsx",0,0,0);

        for (Map<String,String> map:list) {
            System.out.println(map.keySet());
            System.out.println(map.values());
        }




    }


}
