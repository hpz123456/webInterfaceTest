package com.markerhub.caseTestNG;

import com.markerhub.entity.MyFirstModel;

public class parameterDispose {

    public static void startRequest(MyFirstModel my) {
        if (my.getRequestMethod().equals("post")) {
            if (my.getData().isEmpty()) {
                if (my.getToken().isEmpty()) {
                    if (my.getCookie().isEmpty()) {
//                        requestPostGet.requestPost(my.getUrl(), my.getHeader(), my.getParams(), my.getData(), null);
                    }
                }
            }
        }

    }


    public static void main(String[] args) {
        System.out.println("123".isEmpty());
    }

}
