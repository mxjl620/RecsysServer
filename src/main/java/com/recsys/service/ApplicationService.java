package com.recsys.service;

import com.recsys.util.Application;
import com.sun.org.apache.xpath.internal.operations.Bool;
import org.springframework.stereotype.Service;

import java.util.List;


public interface ApplicationService {

    public Application addApplication(Application application, String userid);

    public Application modifyApplication(String userid, Application application);

    public Boolean deleteApplication(String userid, String appid);

    public List<Application> listApplicationByUserid(String userid);
}
