package com.recsys.dao;

import com.recsys.util.DataUtil;

import java.util.List;

public interface DataManagementDao {

    public DataUtil addDataFile(String appid, DataUtil data);

    public List<String> listDataFile(String appid);

    public Boolean DeleteDataFile(String appid, String dataid);

    public DataUtil UpdateDataFile(String appid, DataUtil dataFile);

    public String findDataFileByid(String appid, String dataid);
}
