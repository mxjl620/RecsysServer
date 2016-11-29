package com.recsys.service.impl;


import com.recsys.dao.DataManagementDao;
import com.recsys.service.DataManagementService;
import com.recsys.util.DataUtil;
import com.recsys.util.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service("dataManagementService")
public class DataManagementServiceImpl implements DataManagementService{

    private DataManagementDao dataManagementDao;

    @Autowired
    public void setDataManagementDao(DataManagementDao dataManagementDao) {
        this.dataManagementDao = dataManagementDao;
    }

    public DataUtil addDataFile(String appid, DataUtil data) {
        return dataManagementDao.addDataFile(appid, data);
    }

    public List<DataUtil> listDataFile(String appid) {
        List<String> strs = dataManagementDao.listDataFile(appid);
        List<DataUtil> dataFiles = new ArrayList<DataUtil>();
        for (String dataFile: strs) {
            dataFiles.add((DataUtil) Utils.Jsonstr2Object(dataFile, DataUtil.class));
        }
        return dataFiles;
    }

    public DataUtil UpdateFileSize(String appid, String dataid, String fileSize) {
        String dataFile = dataManagementDao.findDataFileByid(appid, dataid);
        DataUtil data = (DataUtil) Utils.Jsonstr2Object(dataFile, DataUtil.class);
        if (data != null) {
            data.setSize(fileSize);
        }
        return dataManagementDao.UpdateDataFile(appid, data);
    }

    public Boolean DeleteDataFile(String appid, String dataid) {
        return null;
    }


}
