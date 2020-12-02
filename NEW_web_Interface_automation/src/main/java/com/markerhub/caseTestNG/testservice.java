package com.markerhub.caseTestNG;


import com.markerhub.entity.MyFirstModel;
import com.markerhub.entity.caseModel;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class testservice {

    Entry entry = new Entry();

    @DataProvider(name = "data")
    public Object[][] providerData() {
        System.out.println("cs");
        Object[][] o = entry.objects;


        return o;
    }

    @Test(dataProvider = "data")
    public void testDataProvider(MyFirstModel myFirstModel) throws Exception {
        System.out.println(myFirstModel);
        MyFirstModel myFirstModel1 = parameterDispose.startRequest(myFirstModel);
        caseModel.returnTestCase.add(myFirstModel1);

    }


}