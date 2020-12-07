package com.markerhub.ExceptionCustom;

import com.markerhub.entity.MyFirstModel;

public class test {

    public static void main(String[] args) throws BusinessLogicException {
        MyFirstModel myFirstModel = new MyFirstModel();

        try {
            String st = null;
            st.equals("");
        } catch (Exception e) {
            throw new BusinessLogicException(myFirstModel, "出现异常");
        } finally {
            System.out.println(myFirstModel.getRequestResult());
        }
        System.out.println(123);

    }
}
