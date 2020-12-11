package com.markerhub.caseTestNG;


import com.markerhub.entity.MyFirstModel;
import com.markerhub.entity.caseModel;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class testservice {

    Entry entry = new Entry();
    public static Object[][] objects;

    @DataProvider(name = "data")
    public Object[][] providerData() {
//        System.out.println("cs");
//        Object[][] o = entry.objects;

        System.out.println(objects);
        return objects;
    }

    @Test(dataProvider = "data")
    public void testDataProvider(MyFirstModel myFirstModel) throws Exception {
        System.out.println(myFirstModel);
        MyFirstModel myFirstModel1 = parameterDispose.startRequest(myFirstModel);
        
        caseModel.returnTestCase.add(myFirstModel1);

    }


}
