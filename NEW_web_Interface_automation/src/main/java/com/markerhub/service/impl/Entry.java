package com.markerhub.service.impl;

import org.testng.TestNG;

public class Entry {

    public static void main(String[] args) {

        System.out.println("12321");
        TestNG testNG = new TestNG();
        Class[] classes = {testservice.class};
        testNG.setTestClasses(classes);
        testNG.run();
    }
}