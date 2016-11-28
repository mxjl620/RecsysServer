package com.recsys.service.impl;

import com.recsys.dao.ApplicationDao;
import com.recsys.service.ApplicationService;
import com.recsys.util.Application;
import com.recsys.util.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service("applicationService")
public class ApplicationServiceImpl implements ApplicationService {

    private ApplicationDao applicationDao;

    @Autowired
    public void setApplicationDao(ApplicationDao applicationDao) {
        this.applicationDao = applicationDao;
    }

    public Application addApplication(Application application, String userid) {
        return applicationDao.addApplication(application, userid);
    }

    public Application modifyApplication(String userid, Application application) {
        return applicationDao.modifyApplicationInfo(userid, application);
    }

    public Boolean deleteApplication(String userid, String appid) {
        return applicationDao.deleteApplication(userid, appid);
    }

    public List<Application> listApplicationByUserid(String userid) {
        List<String> strs = applicationDao.listApplicationByUserid(userid);
        List<Application> applications = new ArrayList<Application>();
        for (String str: strs) {
            applications.add((Application) Utils.Jsonstr2Object(str, Application.class));
        }
        return applications;
    }
}
