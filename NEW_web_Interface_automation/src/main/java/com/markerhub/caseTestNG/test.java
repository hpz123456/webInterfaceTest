package com.markerhub.caseTestNG;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class test {
//    private final static Logger log=Logger.getLogger(loginReport.class);

    @DataProvider(name = "post")
    public Object[][] logData() {
        Object[][] arr = new Object[][]{
                {"123"},
                {"3213"},
                {"3432"}};
        return arr;
    }

    @Test(dataProvider = "post")
    public void login(String url) {
        System.out.println(url);
    }
}