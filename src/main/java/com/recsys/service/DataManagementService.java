package com.recsys.service;


import com.recsys.util.DataUtil;

import java.util.List;

public interface DataManagementService {

    public DataUtil addDataFile(Long appid, DataUtil data);

    public List<DataUtil> listDataFile(Long appid);

    public Boolean DeleteDataFile(Long appid, Long dataid);

    public Boolean UploadDataFile(Long appid, Long dataid);
}
