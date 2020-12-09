package com.markerhub.caseTestNG;

import com.markerhub.entity.MyFirstModel;
import com.markerhub.entity.caseModel;
import org.testng.TestNG;

import java.util.ArrayList;
import java.util.List;

/**
 * 调用TestNG类
 */
public class Entry {

//    public static Object[][] objects;


    public static void callTestNG() {

//        for (Integer i = 0; i < 10; i++) {
//            MyFirstModel m = new MyFirstModel();
//            caseModel.testCase.add(m);
//        }
        //执行参数传递
        Entry.parameterPassing();

        TestNG testNG = new TestNG();
        List<String> suites = new ArrayList<String>();
        suites.add("src/main/resources/testng.xml");
        //suites.add(".\\test-output\\testng-failed.xml");
        testNG.setTestSuites(suites);
        testNG.run();
        //执行testNG
//        TestNG testNG = new TestNG();
//        Class[] classes = {testservice.class};
//        testNG.setTestClasses(classes);
//        testNG.run();
        System.out.println(testservice.objects.length);
        System.out.println("结束");
    }

    //将参数传到这个类
    private static void parameterPassing() {
        List<MyFirstModel> mf = caseModel.testCase;
        Integer count = mf.size();
        testservice.objects = new Object[count][1];
        for (Integer i = 0; i < count; i++) {
            testservice.objects[i][0] = mf.get(i);
        }
//        objects = new Object[5][1];
//        for (Integer i = 0; i < 5; i++) {
//            objects[i][0] = i;
//        }
//        System.out.println(objects.length);

    }

    public static void main(String[] args) {

        Entry.callTestNG();

    }
}