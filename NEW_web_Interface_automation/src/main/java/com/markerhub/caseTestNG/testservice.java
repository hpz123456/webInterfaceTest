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
    public void testDataProvider(MyFirstModel myFirstModel) {
        System.out.println(myFirstModel);
        caseModel.returnTestCase.add(myFirstModel);

    }


}
