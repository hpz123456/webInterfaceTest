package com.markerhub.caseTestNG;

import com.markerhub.entity.MyFirstModel;
import com.markerhub.entity.caseModel;
import org.testng.TestNG;

import java.util.List;

/**
 * 调用TestNG类
 */
public class Entry {

    public static Object[][] objects;


    public static void callTestNG() {

//        for (Integer i = 0; i < 10; i++) {
//            MyFirstModel m = new MyFirstModel();
//            caseModel.testCase.add(m);
//        }
        //执行参数传递
        Entry.parameterPassing();
        //执行testNG
        TestNG testNG = new TestNG();
        Class[] classes = {testservice.class};
        testNG.setTestClasses(classes);
        testNG.run();
    }

    //将参数传到这个类
    private static void parameterPassing() {
        List<MyFirstModel> mf = caseModel.testCase;
        Integer count = mf.size();
        objects = new Object[count][1];
        for (Integer i = 0; i < count; i++) {
            objects[i][0] = mf.get(i);
        }
    }

    public static void main(String[] args) {

        Entry.callTestNG();

    }
}