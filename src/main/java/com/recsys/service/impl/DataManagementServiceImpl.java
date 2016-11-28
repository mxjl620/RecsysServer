package com.recsys.service.impl;


import com.recsys.dao.DataManagementDao;
import com.recsys.service.DataManagementService;
import com.recsys.util.DataUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("dataManagementService")
public class DataManagementServiceImpl implements DataManagementService{

    private DataManagementDao dataManagementDao;

    @Autowired
    public void setDataManagementDao(DataManagementDao dataManagementDao) {
        this.dataManagementDao = dataManagementDao;
    }

    public DataUtil addDataFile(Long appid, DataUtil data) {
        return dataManagementDao.addDataFile(appid, data);
    }

    public List<DataUtil> listDataFile(Long appid) {
        return null;
    }

    public Boolean DeleteDataFile(Long appid, Long dataid) {
        return null;
    }

    public Boolean UploadDataFile(Long appid, Long dataid) {
        return null;
    }
}
