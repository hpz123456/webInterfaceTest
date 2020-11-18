package com.markerhub.test1;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

public class MyHttpClient<response> {


    public void test(){
        HttpResponse response ;
        String url = "http://www.baidu.com";
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpPost post = new HttpPost(url);
        try {
            response = httpClient.execute(post);
            int statusCode = response.getStatusLine().getStatusCode();
            if(statusCode == HttpStatus.SC_OK){
                String resultStr = EntityUtils.toString(response.getEntity());
                System.out.println(resultStr);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static void main(String[] args) {
        MyHttpClient myHttpClient = new MyHttpClient();
        myHttpClient.test();

    }

}