package com.recsys.dao.impl;


import com.recsys.dao.DataManagementDao;
import com.recsys.util.DataUtil;
import com.recsys.util.Utils;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.client.HTableInterface;
import org.apache.hadoop.hbase.client.Put;
import org.apache.hadoop.hbase.util.Bytes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.hadoop.hbase.HbaseTemplate;
import org.springframework.data.hadoop.hbase.TableCallback;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;

@Repository("dataManagementDao")
public class DataManagementDaoImpl implements DataManagementDao{

    private final String TABLE_NAME = "ns1:application_dataFile";

    private final String TABLE_COLUMN_FAMILY = "dataFile";

    private HbaseTemplate hbaseTemplate;

    @Autowired
    public void setHbaseTemplate(HbaseTemplate hbaseTemplate) {
        this.hbaseTemplate = hbaseTemplate;
    }

    @Resource(name = "hbaseConfiguration")
    private Configuration config;

    public DataUtil addDataFile(final Long appid, final DataUtil data) {
        data.setId(Utils.generateRowKey());
        final String value = Utils.Object2JsonStr(data);
        return hbaseTemplate.execute(TABLE_NAME, new TableCallback<DataUtil>() {
            public DataUtil doInTable(HTableInterface table) throws Throwable {
                Put p = new Put(Bytes.toBytes(appid));
                p.add(Bytes.toBytes(TABLE_COLUMN_FAMILY), Bytes.toBytes(data.getId()), Bytes.toBytes(value));
                table.put(p);
                return data;
            }
        });
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
