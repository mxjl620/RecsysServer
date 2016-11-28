package com.recsys.controller;

import com.recsys.http.BaseResponse;
import com.recsys.http.request.AddDataFileRequest;
import com.recsys.http.request.ListDataFileRequest;
import com.recsys.service.DataManagementService;
import com.recsys.util.DataUtil;
import com.recsys.util.HdfsFileSystem;
import org.apache.commons.fileupload.disk.DiskFileItem;
import org.apache.commons.io.IOUtils;
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

    private DataManagementService dataManagementService;

    @Autowired
    public void setDataManagementService(DataManagementService dataManagementService) {
        this.dataManagementService = dataManagementService;
    }

    @RequestMapping(value = "/addData", method = RequestMethod.POST)
    @ResponseBody
    public BaseResponse addDataFile(@RequestBody AddDataFileRequest request) {
        BaseResponse resp = new BaseResponse();
        if (request.getAppid() == null || request.getType() == null) {
            resp.setStatus(400);
            resp.setMsg("parameter error!");
            return resp;
        }
        DataUtil data = new DataUtil();
        if (request.getName() != null) {
            data.setName(request.getName());
        }
        data.setType(request.getType());
        data.setSize("0");
        DataUtil result = dataManagementService.addDataFile(request.getAppid(), data);
        resp.setStatus(HttpStatus.OK.value());
        resp.setData(result);
        return resp;
    }

    @RequestMapping(value = "/delData", method = RequestMethod.POST)
    @ResponseBody
    public BaseResponse delDataFile(@RequestBody ListDataFileRequest request) {
        BaseResponse resp = new BaseResponse();
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

    @RequestMapping(value = "/UploadDataFile", method = RequestMethod.POST)
    @ResponseBody
    public BaseResponse UploadDataFile(HttpServletRequest request) throws IllegalStateException, IOException {

        BaseResponse resp = new BaseResponse();

        if (request.getParameter("datafileid") == null){
            resp.setStatus(400);
            resp.setMsg("parameter error!");
            return resp;
        }

        CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(
                request.getSession().getServletContext());

        if (multipartResolver.isMultipart(request)) {
            MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) request;
            Iterator iter = multiRequest.getFileNames();

            while (iter.hasNext()) {
                MultipartFile file = multiRequest.getFile(iter.next().toString());
                if (file != null) {
                    String filename = request.getParameter("datafileid");
                    CommonsMultipartFile cf= (CommonsMultipartFile)file;
                    DiskFileItem fi = (DiskFileItem)cf.getFileItem();
                    File inputFile = fi.getStoreLocation();
                    cf.transferTo(fi.getStoreLocation());
                    HdfsFileSystem.createFile(inputFile,
                            "hdfs://192.168.235.146:8020/upload/" + filename);
                    //TODO update file size
                }

            }
        }

        resp.setStatus(HttpStatus.OK.value());
        resp.setMsg("file upload success!");
        return resp;
    }
}
