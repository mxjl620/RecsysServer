package com.recsys.service;


import com.recsys.util.DataUtil;

import java.util.List;

public interface DataManagementService {

    public DataUtil addDataFile(String appid, DataUtil data);

    public List<DataUtil> listDataFile(String appid);

    public Boolean deleteDataFile(String appid, String dataid);

    public DataUtil updateFileSize(String appid, String dataid, String fileSize);
}
