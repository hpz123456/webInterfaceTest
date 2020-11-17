package com.markerhub.service.impl;


import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;


public class testservice {

    @DataProvider(name = "data")
    public Object[][] providerData(){
        System.out.println("cs");
        Object[][] o = new Object[][]{
                {"zhangsan",10},
                {"lisi",20},
                {"wangwu",30}
        };


        return o;
    }
    @Test(dataProvider = "data")
    public void testDataProvider(String name,int age){
        System.out.println("name = " + name + ",age = " + age);

    }


}
