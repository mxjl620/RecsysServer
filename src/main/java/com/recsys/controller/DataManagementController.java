package com.recsys.controller;

import com.recsys.http.BaseResponse;
import com.recsys.http.request.AddDataFileRequest;
import com.recsys.http.request.ListDataFileRequest;
import com.recsys.service.DataManagementService;
import com.recsys.util.DataUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
        if (request.getName() != null){
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
    public BaseResponse UploadDataFile(HttpServletRequest req, HttpServletResponse response) {
        BaseResponse resp = new BaseResponse();
        resp.setStatus(HttpStatus.OK.value());
        return resp;
    }
}
