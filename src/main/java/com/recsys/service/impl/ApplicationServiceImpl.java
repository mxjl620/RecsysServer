package com.recsys.service.impl;

import com.recsys.dao.ApplicationDao;
import com.recsys.service.ApplicationService;
import com.recsys.util.Application;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("applicationService")
public class ApplicationServiceImpl implements ApplicationService{

    private ApplicationDao applicationDao;

    @Autowired
    public void setApplicationDao(ApplicationDao applicationDao) {
        this.applicationDao = applicationDao;
    }

    public Application addApplication(Application application, String userid) {
        return applicationDao.addApplication(application, userid);
    }

    public Application modifyApplication(Application application, String userid) {
        return null;
    }

    public Application deleteApplication(String applicationid, String userid) {
        return null;
    }

    public List<Application> listApplication(String userid) {
        return null;
    }
}
