package com.markerhub.ExceptionCustom;

import com.markerhub.entity.MyFirstModel;
import com.markerhub.entity.caseModel;

public class BusinessLogicException extends Exception {

    private static final long serialVersionUID = 2372630455006353870L;

    // 业务逻辑异常

    /**
     * 空参构造
     */
    public BusinessLogicException() {
    }

    /**
     * @param message 表示异常提示
     */
    public BusinessLogicException(MyFirstModel myFirstModel, String message) {
        super(message);
//        System.out.println(message);
        myFirstModel.setRequestResult(message);
        caseModel.returnTestCase.add(myFirstModel);
    }
}

