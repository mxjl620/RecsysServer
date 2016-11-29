package com.recsys.dao;

import com.recsys.util.DataUtil;

import java.util.List;

public interface DataManagementDao {

    public DataUtil addDataFile(String appid, DataUtil data);

    public List<String> listDataFile(String appid);

    public Boolean deleteDataFile(String appid, String dataid);

    public DataUtil updateDataFile(String appid, DataUtil dataFile);

    public String findDataFileByid(String appid, String dataid);
}
