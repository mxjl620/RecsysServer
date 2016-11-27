package com.recsys.http.request;

public class ModifyApplicationInfoRequest {
    private String userid;

    private Long appid;

    private String appName;

    private String appDesc;

    private String email;

    private String productDesc;

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public Long getAppid() {
        return appid;
    }

    public void setAppid(Long appid) {
        this.appid = appid;
    }

    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }

    public String getAppDesc() {
        return appDesc;
    }

    public void setAppDesc(String appDesc) {
        this.appDesc = appDesc;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getProductDesc() {
        return productDesc;
    }

    public void setProductDesc(String productDesc) {
        this.productDesc = productDesc;
    }
}
