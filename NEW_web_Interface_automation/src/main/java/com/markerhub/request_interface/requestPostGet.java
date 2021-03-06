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
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class requestPostGet {
    //进行get请求
    public static requestReturn requestGet(String url, Map<String, String> header, Map<String, Object> params, String getCookie, CookieStore cookieStore) throws Exception {
        //创建一个httpclient对象
        DefaultHttpClient httpClient = null;
        if (requestPostGet.HttpOrHttps(url)) {
            httpClient = new DefaultHttpClient();
        } else {
            httpClient = new SSLClient();
        }

        //创建一个GET对象
        URIBuilder uriBuilder = new URIBuilder(url);

        //添加params
        if (params != null) {
            for (String key : params.keySet()) {
                String paramsSt = params.get(key).toString();
                if (((paramsSt.substring(paramsSt.length()-1)).equals("\"")) && ((paramsSt.substring(0,1)).equals("\""))){
                    paramsSt = paramsSt.substring(1,paramsSt.length()-1);
                }
                uriBuilder.addParameter(key, paramsSt);
            }
        }
        if (cookieStore != null) {
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
        CookieStore cookieList = null;
        if (getCookie != null && getCookie != "") {
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
    public static requestReturn requestPost(String url, Map<String, String> header, Map<String, Object> params, String data, CookieStore cookieStore, String getCookie,Map<String,Object> formData) throws Exception {
//        data = data.replace("'", "\"");
        //创建一个httpclient对象
        DefaultHttpClient httpClient = null;
        if (requestPostGet.HttpOrHttps(url)) {
            httpClient = new DefaultHttpClient();
        } else {
            httpClient = new SSLClient();
        }

        StringEntity entity = null;
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
                kvList.add(new BasicNameValuePair(key, params.get(key).toString()));
            }
            //包装成一个Entity对象
            entity = new UrlEncodedFormEntity(kvList, "utf-8");
        }

        if (data != null & data != "") {
            entity = new StringEntity(data, ContentType.APPLICATION_JSON);
            post.setEntity(entity);
        }

        if (formData != null) {
//            post.removeHeaders("Content-Length");
//            post.addHeader("Cache-Control", "no-cache");
//            post.addHeader("Postman-Token", "<calculated when request is sent>");
//            post.addHeader("Content-Type", "multipart/form-data; boundary=<calculated when request is sent>");
//            post.addHeader("Content-Length", "<calculated when request is sent>");
//            post.addHeader("Host", "<calculated when request is sent>");
//            post.addHeader("User-Agent", "PostmanRuntime/7.26.8");
//            post.addHeader("Accept", "*/*");
//            post.addHeader("Accept-Encoding", "gzip, deflate, br");
//            post.addHeader("Connection", "keep-alive");
            List<NameValuePair> formDataList = new ArrayList<NameValuePair>();
            for (String formDataSt:formData.keySet()) {
                String formSt = formData.get(formDataSt).toString();
                if (((formSt.substring(formSt.length()-1)).equals("\"")) && ((formSt.substring(0,1)).equals("\""))){
                    formSt = formSt.substring(1,formSt.length()-1);
                }

                formDataList.add(new BasicNameValuePair(formDataSt,formSt));
            }
            entity = new UrlEncodedFormEntity(formDataList,"UTF-8");
            post.setEntity(entity);

        }
        if (cookieStore != null) {
            httpClient.setCookieStore(cookieStore);
        }
//        Header[] allHeaders = post.getAllHeaders();
//        for (Header h:allHeaders) {
//            String name = h.getName();
//            String value = h.getValue();
//
//        }

        //执行post请求
        CloseableHttpResponse response = httpClient.execute(post);
//        Header[] allHeaders = post.getAllHeaders();
//        for (Header h:allHeaders) {
//            String name = h.getName();
//            String value = h.getValue();
//
//        }
        //body
        HttpEntity body = response.getEntity();
//        statuscode
        int statusCode = response.getStatusLine().getStatusCode();
//        headers
        Header[] allheaders = response.getAllHeaders();
        //cookie
        CookieStore cookieList = null;
        if (getCookie != null && getCookie != "") {
            cookieList = requestPostGet.getCookie(httpClient);
        }
//        String st = EntityUtils.toString(body, "utf-8");
        requestReturn re = new requestReturn();
        re.setAllheaders(allheaders);
        re.setCaseBody(EntityUtils.toString(body, "utf-8"));
//        System.out.println(EntityUtils.toString(body, "utf-8"));


//        EntityUtils.toString(response.getEntity(), "utf-8");
        re.setStatusCode(statusCode);
        re.setCookieList(cookieList);
        response.close();
        httpClient.close();
        return re;
    }

    //获取cookie
    public static CookieStore getCookie(DefaultHttpClient httpClient) {
        CookieStore store = httpClient.getCookieStore();
//        List<Cookie> cookieList = store.getCookies();
        return store;
    }

    //获取token
    public Map<String, String> getToken() {

        return null;
    }

    /**
     * 判断url是http还是https
     *
     * @param httpUrl
     * @return true:http false:https
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
//        requestReturn re = requestPostGet.requestPost("http://127.0.0.1:8081/my-first-model/deleteList", null, null, "[\"1017250775\",\"1017540888\"]", null);
//        System.out.println(re.getCaseBody());
//        System.out.println(re.getCookieList());

    }
}
