package com.markerhub.entity;

import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author 关注公众号：MarkerHub
 * @since 2020-11-10
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class MyFirstModel implements Serializable {

    private static final long serialVersionUID = 1L;

    private String id;

    private String caseId;

    private String projectName;

    private String subordinateModule;

    private String caseName;

    private String url;

    private String header;

    private String data;

    private String params;

    private String requestMethod;

    private String cookie;

    private String caseDescription;

    private String token;

    private String dependData;

    private String listAssert;

    private String listVagueAssert;

    private String commonAssert;

    private String getCookie;

    private String remark;

    private String remark1;

    private String remark2;


    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCaseId() {
        return caseId;
    }

    public void setCaseId(String caseId) {
        this.caseId = caseId;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getSubordinateModule() {
        return subordinateModule;
    }

    public void setSubordinateModule(String subordinateModule) {
        this.subordinateModule = subordinateModule;
    }

    public String getCaseName() {
        return caseName;
    }

    public void setCaseName(String caseName) {
        this.caseName = caseName;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getHeader() {
        return header;
    }

    public void setHeader(String header) {
        this.header = header;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getParams() {
        return params;
    }

    public void setParams(String params) {
        this.params = params;
    }

    public String getRequestMethod() {
        return requestMethod;
    }

    public void setRequestMethod(String requestMethod) {
        this.requestMethod = requestMethod;
    }

    public String getCookie() {
        return cookie;
    }

    public void setCookie(String cookie) {
        this.cookie = cookie;
    }

    public String getCaseDescription() {
        return caseDescription;
    }

    public void setCaseDescription(String caseDescription) {
        this.caseDescription = caseDescription;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getDependData() {
        return dependData;
    }

    public void setDependData(String dependData) {
        this.dependData = dependData;
    }

    public String getListAssert() {
        return listAssert;
    }

    public void setListAssert(String listAssert) {
        this.listAssert = listAssert;
    }

    public String getListVagueAssert() {
        return listVagueAssert;
    }

    public void setListVagueAssert(String listVagueAssert) {
        this.listVagueAssert = listVagueAssert;
    }

    public String getCommonAssert() {
        return commonAssert;
    }

    public void setCommonAssert(String commonAssert) {
        this.commonAssert = commonAssert;
    }

    public String getGetCookie() {
        return getCookie;
    }

    public void setGetCookie(String getCookie) {
        this.getCookie = getCookie;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getRemark1() {
        return remark1;
    }

    public void setRemark1(String remark1) {
        this.remark1 = remark1;
    }

    public String getRemark2() {
        return remark2;
    }

    public void setRemark2(String remark2) {
        this.remark2 = remark2;
    }
}
