package com.recsys.dao.impl;


import com.recsys.dao.DataManagementDao;
import com.recsys.util.DataUtil;
import com.recsys.util.Utils;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.Cell;
import org.apache.hadoop.hbase.client.HTableInterface;
import org.apache.hadoop.hbase.client.Put;
import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.util.Bytes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.hadoop.hbase.HbaseTemplate;
import org.springframework.data.hadoop.hbase.RowMapper;
import org.springframework.data.hadoop.hbase.TableCallback;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Repository("dataManagementDao")
public class DataManagementDaoImpl implements DataManagementDao {

    private final String TABLE_NAME = "ns1:application_dataFile";

    private final String TABLE_COLUMN_FAMILY = "dataFile";

    private HbaseTemplate hbaseTemplate;

    @Autowired
    public void setHbaseTemplate(HbaseTemplate hbaseTemplate) {
        this.hbaseTemplate = hbaseTemplate;
    }

    @Resource(name = "hbaseConfiguration")
    private Configuration config;

    public DataUtil addDataFile(final String appid, final DataUtil data) {
        data.setId(Utils.generateRowKey().toString());
        final String value = Utils.Object2JsonStr(data);
        if (value != null) {
            return hbaseTemplate.execute(TABLE_NAME, new TableCallback<DataUtil>() {
                public DataUtil doInTable(HTableInterface table) throws Throwable {
                    Put p = new Put(Bytes.toBytes(appid));
                    p.add(Bytes.toBytes(TABLE_COLUMN_FAMILY), Bytes.toBytes(data.getId()), Bytes.toBytes(value));
                    table.put(p);
                    return data;
                }
            });
        } else {
            return null;
        }

    }

    public List<String> listDataFile(String appid) {
        return hbaseTemplate.get(TABLE_NAME, appid, new RowMapper<List<String>>() {
            public List<String> mapRow(Result result, int rowNum) throws Exception {
                List<Cell> ceList = result.listCells();
                List<String> dataFiles = new ArrayList<String>();
                if (ceList != null && ceList.size() > 0) {
                    for (Cell cell : ceList) {
                        dataFiles.add(Bytes.toString(cell.getValueArray(), cell.getValueOffset(), cell.getValueLength()));
                    }
                }
                return dataFiles;
            }
        });
    }

    public DataUtil UpdateDataFile(final String appid, final DataUtil dataFile) {
        final String value = Utils.Object2JsonStr(dataFile);
        if (value != null) {
            return hbaseTemplate.execute(TABLE_NAME, new TableCallback<DataUtil>() {
                public DataUtil doInTable(HTableInterface table) throws Throwable {
                    Put p = new Put(Bytes.toBytes(appid));
                    p.add(Bytes.toBytes(TABLE_COLUMN_FAMILY), Bytes.toBytes(dataFile.getId()), Bytes.toBytes(value));
                    table.put(p);
                    return dataFile;
                }
            });
        } else {
            return null;
        }

    }

    public String findDataFileByid(String appid, String dataid) {
        return hbaseTemplate.get(TABLE_NAME, appid, TABLE_COLUMN_FAMILY, dataid, new RowMapper<String>() {
            public String mapRow(Result result, int rowNum) throws Exception {
                List<Cell> ceList = result.listCells();
                String res = "";
                if (ceList != null && ceList.size() > 0) {
                    for (Cell cell : ceList) {
                        res = Bytes.toString(cell.getValueArray(), cell.getValueOffset(), cell.getValueLength());
                    }
                }
                return res;
            }
        });
    }

    public Boolean DeleteDataFile(String appid, String dataid) {
        return null;
    }

}
