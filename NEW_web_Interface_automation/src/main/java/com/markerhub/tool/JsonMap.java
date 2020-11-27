package com.markerhub.tool;

import com.markerhub.request_interface.test1;

import java.io.IOException;
import java.util.Map;

public class JsonMap {

    public Map jsonMap(String json) {


        return null;

    }


    public static void main(String[] args) throws IOException {
        test1 test1 = new test1();
        String st = test1.requestPost();

        st = StringUtils.replaceBlank(st);
        System.out.println(st);

    }


}
