package com.recsys.service;

import com.recsys.util.Application;
import org.springframework.stereotype.Service;

import java.util.List;


public interface ApplicationService {

    public Application addApplication(Application application, String userid);

    public Application modifyApplication(Application application, String userid);

    public Application deleteApplication(String applicationid, String userid);

    public List<Application> listApplicationByUserid(String userid);
}
