package com.recsys.dao.impl;


import com.recsys.dao.ApplicationDao;
import com.recsys.util.Application;
import com.recsys.util.Utils;
import org.apache.hadoop.conf.Configuration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.hadoop.hbase.HbaseTemplate;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;

@Repository("applicationDao")
public class ApplicationDaoImpl implements ApplicationDao{

    private HbaseTemplate hbaseTemplate;

    @Autowired
    public void setHbaseTemplate(HbaseTemplate hbaseTemplate) {
        this.hbaseTemplate = hbaseTemplate;
    }

    @Resource(name = "hbaseConfiguration")
    private Configuration config;

    public Application addApplication(Application application, String userid) {
        application.setAppid(Utils.generateRowKey());
        return application;
    }
}
