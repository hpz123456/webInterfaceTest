package com.markerhub.request_interface;

import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class test1 {

    public void requestGet(String url, Map<String, String> param) throws IOException, URISyntaxException {
        //创建一个httpclient对象
        CloseableHttpClient httpClient = HttpClients.createDefault();
        //创建一个GET对象
        URIBuilder uriBuilder = new URIBuilder("http://www.baidu.com/s");
        uriBuilder.addParameter("wd", "花千骨");
        HttpGet get = new HttpGet(uriBuilder.build());
        //执行请求
        CloseableHttpResponse response = httpClient.execute(get);
        //取响应的结果
        int statusCode = response.getStatusLine().getStatusCode();
        System.out.println(statusCode);
        HttpEntity entity = response.getEntity();
        String string = EntityUtils.toString(entity, "utf-8");
        System.out.println(string);
        //关闭httpclient
        response.close();
        httpClient.close();
    }

    public String requestPost() throws IOException {
        CloseableHttpClient httpClient = HttpClients.createDefault();

        //创建一个post对象
        HttpPost post = new HttpPost("http://127.0.0.1:8081/my-first-model/findAll");
        //创建一个Entity。模拟一个表单
        List<NameValuePair> kvList = new ArrayList<>();
        kvList.add(new BasicNameValuePair("username", "zhangsan"));
        kvList.add(new BasicNameValuePair("password", "123"));

        //包装成一个Entity对象
        StringEntity entity = new UrlEncodedFormEntity(kvList, "utf-8");
        //设置请求的内容
        post.setEntity(entity);

        //执行post请求
        CloseableHttpResponse response = httpClient.execute(post);
        String string = EntityUtils.toString(response.getEntity());
        System.out.println(string);
        response.close();
        httpClient.close();
        return string;
    }

    public static void main(String[] args) throws IOException, URISyntaxException {
        test1 test1 = new test1();
//        test1.test();
    }
}