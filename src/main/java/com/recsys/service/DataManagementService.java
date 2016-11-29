package com.recsys.service;


import com.recsys.util.DataUtil;

import java.util.List;

public interface DataManagementService {

    public DataUtil addDataFile(String appid, DataUtil data);

    public List<DataUtil> listDataFile(String appid);

    public Boolean DeleteDataFile(String appid, String dataid);

    public DataUtil UpdateFileSize(String appid, String dataid, String fileSize);
}
