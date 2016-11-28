package com.recsys.dao;

import com.recsys.util.Application;

import java.util.List;

public interface ApplicationDao {

    public Application addApplication(Application application, String userid);

    public List<String> listApplicationByUserid(String userid);

    public Application modifyApplicationInfo(String userid, Application application);

    public Boolean deleteApplication(String userid, Long appid);
}
