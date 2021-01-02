package com.markerhub.request_interface;

import com.markerhub.entity.requestReturn;

public class test {

    public static void main(String[] args) throws Exception {
        HttpClientUtil httpClientUtil = new HttpClientUtil();


        String s = httpClientUtil.doPostJson("http://127.0.0.1:8081/my-first-model/findCase", "{}");
        System.out.println(s);

        requestReturn requestReturn = requestPostGet.requestPost("http://127.0.0.1:8081/my-first-model/findCase", null, null, "{}", null, null,null);
        System.out.println(requestReturn.getCaseBody());


    }


}
