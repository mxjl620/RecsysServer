package com.recsys.dao.impl;


import com.recsys.dao.ApplicationDao;
import com.recsys.util.Application;
import com.recsys.util.Utils;
import org.springframework.stereotype.Repository;

@Repository("applicationDao")
public class ApplicationDaoImpl implements ApplicationDao{

    public Application addApplication(Application application, String userid) {
        application.setAppid(Utils.generateRowKey());
        return application;
    }
}
