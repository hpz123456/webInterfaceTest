package com.markerhub.request_interface;

import com.markerhub.entity.requestReturn;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.CookieStore;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.cookie.Cookie;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.net.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class requestPostGet{
    //进行get请求
    public static requestReturn requestGet(String url, Map<String,String> header, Map<String,String> params, Map<String,String> data,String getCookie,CookieStore cookieStore) throws Exception {
        //创建一个httpclient对象
        DefaultHttpClient httpClient = null;
        if(requestPostGet.HttpOrHttps(url)){
            httpClient = new DefaultHttpClient();
        }else {
            httpClient = new SSLClient();
        }

        //创建一个GET对象
        URIBuilder uriBuilder = new URIBuilder(url);

        //添加params
        if (params != null) {
            for (String key : params.keySet()) {
                uriBuilder.addParameter(key, params.get(key));
            }
        }
        if (data != null) {
            for (String key : data.keySet()) {
                uriBuilder.addParameter(key, data.get(key));
            }
        }
        if(cookieStore != null){
            httpClient.setCookieStore(cookieStore);
        }
        //创建http请求
        HttpGet get = new HttpGet(uriBuilder.build());
        //添加header
        if (header != null) {
            for (String key : header.keySet()) {
                get.addHeader(key, header.get(key));
            }
        }
        //执行请求
        CloseableHttpResponse response = httpClient.execute(get);

        //body
        HttpEntity entity = response.getEntity();
        //statuscode
        int statusCode = response.getStatusLine().getStatusCode();
        //headers
        Header[] allheaders = response.getAllHeaders();
        //cookie
        List<Cookie> cookieList = null;
        if (getCookie != null && getCookie != ""){
            cookieList = requestPostGet.getCookie(httpClient);
        }

        requestReturn re = new requestReturn();
        re.setAllheaders(allheaders);
        //String string = EntityUtils.toString(entity, "utf-8");
        re.setCaseBody(EntityUtils.toString(entity, "utf-8"));
        re.setStatusCode(statusCode);
        re.setCookieList(cookieList);
        response.close();
        httpClient.close();
        return re;
    }
    //进行post请求
    public requestReturn requestPost(String url,Map<String,String> header,Map<String,String> params,Map<String,String> data,CookieStore cookieStore) throws Exception {
        //创建一个httpclient对象
        DefaultHttpClient httpClient = null;
        if(requestPostGet.HttpOrHttps(url)){
            httpClient = new DefaultHttpClient();
        }else {
            httpClient = new SSLClient();
        }
        //创建一个post对象
        HttpPost post = new HttpPost(url);
        //添加header
        if (header != null) {
            for (String key : header.keySet()) {
                post.addHeader(key, header.get(key));
            }
        }
        //创建一个Entity。模拟一个表单
        //添加data
        List<NameValuePair> kvList = new ArrayList<>();
        if (params != null) {
            for (String key : params.keySet()) {
                kvList.add(new BasicNameValuePair(key, params.get(key)));
            }
        }
        if (data != null) {
            for (String key : data.keySet()) {
                kvList.add(new BasicNameValuePair(key, data.get(key)));
            }
        }

        if(cookieStore != null){
            httpClient.setCookieStore(cookieStore);
        }
        //包装成一个Entity对象
        StringEntity entity = new UrlEncodedFormEntity(kvList, "utf-8");
        //设置请求的内容
        post.setEntity(entity);
        //执行post请求
        CloseableHttpResponse response = httpClient.execute(post);
        //body
        HttpEntity body = response.getEntity();
//        statuscode
        int statusCode = response.getStatusLine().getStatusCode();
//        headers
        Header[] allheaders = response.getAllHeaders();

        requestReturn re = new requestReturn();
        re.setAllheaders(allheaders);
        re.setCaseBody(EntityUtils.toString(body, "utf-8"));
        re.setStatusCode(statusCode);
        response.close();
        httpClient.close();
        return re;
    }

    //获取cookie
    public static List<Cookie> getCookie(DefaultHttpClient httpClient){
        CookieStore store = httpClient.getCookieStore();
        List<Cookie> cookieList = store.getCookies();
        return cookieList;
    }
    //获取token
    public Map<String,String> getToken(){

        return null;
    }

    /**
     * 判断url是http还是https
     * @param httpUrl
     * @return  true:http false:https
     * @throws MalformedURLException
     * @throws URISyntaxException
     */
    public static boolean HttpOrHttps(String httpUrl) throws MalformedURLException, URISyntaxException {
        URL url = new URL(httpUrl);
        URI uri = url.toURI();
        String scheme = uri.getScheme();

        return scheme.equals("http");

    }


    public static void main(String[] args) throws Exception {
        requestReturn re = requestPostGet.requestGet("http://www.baidu.com", null, null, null,"y",null);
        System.out.println(re.getCaseBody());
        System.out.println(re.getCookieList());

    }
}