package com.recsys.dao.impl;

import java.util.ArrayList;

import com.recsys.dao.ApplicationDao;
import com.recsys.util.Application;
import com.recsys.util.Utils;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.Cell;
import org.apache.hadoop.hbase.client.Delete;
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
import java.util.List;

@Repository("applicationDao")
public class ApplicationDaoImpl implements ApplicationDao {

    private final String TABLE_NAME = "ns1:user_applications";
    private final String TABLE_COLUMN_FAMILY = "applications";

    private HbaseTemplate hbaseTemplate;

    @Autowired
    public void setHbaseTemplate(HbaseTemplate hbaseTemplate) {
        this.hbaseTemplate = hbaseTemplate;
    }

    @Resource(name = "hbaseConfiguration")
    private Configuration config;


    public Application addApplication(final Application application, final String userid) {
        application.setAppid(Utils.generateRowKey().toString());
        final String value = Utils.Object2JsonStr(application);
        return hbaseTemplate.execute(TABLE_NAME, new TableCallback<Application>() {
            public Application doInTable(HTableInterface table) throws Throwable {
                Put p = new Put(Bytes.toBytes(userid));
                p.add(Bytes.toBytes(TABLE_COLUMN_FAMILY), Bytes.toBytes(application.getAppid()), Bytes.toBytes(value));
                table.put(p);
                return application;
            }
        });
    }

    public List<String> listApplicationByUserid(String userid) {
        return hbaseTemplate.get(TABLE_NAME, userid, new RowMapper<List<String>>() {
            public List<String> mapRow(Result result, int rowNum) throws Exception {
                List<Cell> ceList = result.listCells();
                List<String> applications = new ArrayList<String>();
                if (ceList != null && ceList.size() > 0) {
                    for (Cell cell : ceList) {
                        applications.add(Bytes.toString(cell.getValueArray(), cell.getValueOffset(), cell.getValueLength()));
                    }
                }
                return applications;
            }
        });
    }

    public Application modifyApplicationInfo(final String userid, final Application application) {
        final String value = Utils.Object2JsonStr(application);
        deleteApplication(userid, application.getAppid());
        return hbaseTemplate.execute(TABLE_NAME, new TableCallback<Application>() {
            public Application doInTable(HTableInterface table) throws Throwable {
                Put p = new Put(Bytes.toBytes(userid));
                p.add(Bytes.toBytes(TABLE_COLUMN_FAMILY), Bytes.toBytes(application.getAppid()), Bytes.toBytes(value));
                table.put(p);
                return application;
            }
        });
    }

    public Boolean deleteApplication(final String userid, final String appid) {
        hbaseTemplate.execute(TABLE_NAME, new TableCallback() {
            public Object doInTable(HTableInterface htable) throws Throwable {
                Delete delete = new Delete(Bytes.toBytes(userid));
                delete.deleteColumn(Bytes.toBytes(TABLE_COLUMN_FAMILY), Bytes.toBytes(appid));
                htable.delete(delete);
                return null;
            }
        });
        return true;
    }
}
