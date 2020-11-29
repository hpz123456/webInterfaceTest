package com.markerhub.entity;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.cookie.Cookie;

import java.util.List;

/**
 * 接受请求接口返回值
 */
public class requestReturn {

    //body
    public String caseBody;
    //statuscode
    public int statusCode;
    //headers
    public Header[] allheaders;
    //cookie
    public List<Cookie> cookieList;

    public List<Cookie> getCookieList() {
        return cookieList;
    }

    public void setCookieList(List<Cookie> cookieList) {
        this.cookieList = cookieList;
    }

    public String getCaseBody() {
        return caseBody;
    }

    public void setCaseBody(String caseBody) {
        this.caseBody = caseBody;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public Header[] getAllheaders() {
        return allheaders;
    }

    public void setAllheaders(Header[] allheaders) {
        this.allheaders = allheaders;
    }
}
