package com.markerhub.ExceptionCustom;

import com.markerhub.entity.MyFirstModel;
import com.markerhub.entity.caseModel;

public class CaseAssertException extends Exception {

    private static final long serialVersionUID = 2372630455006353870L;

    // 业务逻辑异常

    /**
     * 空参构造
     */
    public CaseAssertException() {
    }

    /**
     * @param message 表示异常提示
     */
    public CaseAssertException(MyFirstModel myFirstModel, String message) {
        super(message);
//        System.out.println(message);
        myFirstModel.setAssertResult("N");
        myFirstModel.setAssertResult(message);
        caseModel.returnTestCase.add(myFirstModel);
    }
}
