package com.markerhub.service.impl;


import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class testservice {

    Entry entry = new Entry();

    @DataProvider(name = "data")
    public Object[][] providerData(){
        System.out.println("cs");
        Object[][] o = entry.objects;


        return o;
    }
    @Test(dataProvider = "data")
    public void testDataProvider(String name,int age){
        System.out.println("name = " + name + ",age = " + age);

    }


}
