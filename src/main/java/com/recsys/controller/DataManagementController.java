package com.recsys.controller;

import com.recsys.http.BaseResponse;
import com.recsys.http.request.DelDataFileRequest;
import com.recsys.http.request.ListDataFileRequest;
import com.recsys.service.DataManagementService;
import com.recsys.util.DataUtil;
import com.recsys.util.HdfsFileSystem;
import com.recsys.util.Utils;
import org.apache.commons.fileupload.disk.DiskFileItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.util.Iterator;
import java.util.List;

@Controller
@RequestMapping(value = "dataManagement")
public class DataManagementController {

    private final String HADOOP_URL = "hdfs://192.168.235.146:8020/upload/";

    private DataManagementService dataManagementService;

    @Autowired
    public void setDataManagementService(DataManagementService dataManagementService) {
        this.dataManagementService = dataManagementService;
    }

    @RequestMapping(value = "/addData", method = RequestMethod.POST)
    @ResponseBody
    public BaseResponse addDataFile(HttpServletRequest request) throws IOException {
        BaseResponse resp = new BaseResponse();
        if (request.getParameter("appid") == null || request.getParameter("type") == null) {
            resp.setStatus(400);
            resp.setMsg("parameter error!");
            return resp;
        }
        DataUtil data = new DataUtil();
        if (request.getParameter("name") != null) {
            data.setName(request.getParameter("name"));
        }
        data.setType(request.getParameter("type"));

        data.setSize("0 Kb");

        DataUtil result = dataManagementService.addDataFile(request.getParameter("appid"), data);

        Long fileSize = 0L;

        CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(
                request.getSession().getServletContext());

        if (multipartResolver.isMultipart(request)) {
            MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) request;
            Iterator iter = multiRequest.getFileNames();

            while (iter.hasNext()) {
                MultipartFile file = multiRequest.getFile(iter.next().toString());
                if (file != null) {
                    String filename = result.getId();
                    CommonsMultipartFile cf= (CommonsMultipartFile)file;
                    DiskFileItem fi = (DiskFileItem)cf.getFileItem();
                    File inputFile = fi.getStoreLocation();
                    cf.transferTo(fi.getStoreLocation());
                    HdfsFileSystem.createFile(inputFile,HADOOP_URL + filename);

                    fileSize = inputFile.length();
                }
            }
        }

        data.setSize(Utils.getFileSize(fileSize));

        result = dataManagementService.updateFileSize(request.getParameter("appid"),
                result.getId(), Utils.getFileSize(fileSize));
        resp.setStatus(HttpStatus.OK.value());
        resp.setData(result);
        return resp;
    }

    @RequestMapping(value = "/delDataFile", method = RequestMethod.POST)
    @ResponseBody
    public BaseResponse delDataFile(@RequestBody DelDataFileRequest request) {
        BaseResponse resp = new BaseResponse();
        if (request.getAppid() == null || request.getDataid() == null){
            resp.setStatus(400);
            resp.setMsg("parameter error!");
            return resp;
        }
        dataManagementService.deleteDataFile(request.getAppid(), request.getDataid());
        HdfsFileSystem.deleteFile(HADOOP_URL + request.getDataid());
        resp.setStatus(HttpStatus.OK.value());
        return resp;
    }

    @RequestMapping(value = "/listDataFile", method = RequestMethod.POST)
    @ResponseBody
    public BaseResponse listDataFile(@RequestBody ListDataFileRequest request) {
        BaseResponse resp = new BaseResponse();
        if (request.getAppid() == null) {
            resp.setStatus(400);
            resp.setMsg("parameter error!");
            return resp;
        }
        List<DataUtil> list = dataManagementService.listDataFile(request.getAppid());
        resp.setStatus(HttpStatus.OK.value());
        resp.setData(list);
        return resp;
    }

}
