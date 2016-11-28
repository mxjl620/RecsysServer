package com.recsys.service;


import com.recsys.util.DataUtil;

import java.util.List;

public interface DataManagementService {

    public DataUtil addDataFile(String appid, DataUtil data);

    public List<DataUtil> listDataFile(String appid);

    public Boolean DeleteDataFile(String appid, String dataid);

    public Boolean UploadDataFile(String appid, String dataid);
}
