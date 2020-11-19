package com.markerhub.service.impl;

import org.testng.TestNG;

public class Entry {

    public static Object[][] objects;


    public static void main(String[] args) {


        objects = new Object[][]{
                {"zhangsan",10},
                {"lisi",20},
                {"wangwu",30}
        };
        System.out.println("12321");
        TestNG testNG = new TestNG();
        Class[] classes = {testservice.class};
        testNG.setTestClasses(classes);
        testNG.run();
    }
}