package com.recsys.controller;

import com.recsys.http.BaseResponse;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping(value = "dataFileManagement")
public class DataFileManagementController {

    @RequestMapping(value = "/addDataFile", method = RequestMethod.POST)
    @ResponseBody
    public BaseResponse addDataFile(HttpServletRequest req, HttpServletResponse response) {
        BaseResponse resp = new BaseResponse();
        resp.setStatus(HttpStatus.OK.value());
        return resp;
    }

    @RequestMapping(value = "/delDataFile", method = RequestMethod.POST)
    @ResponseBody
    public BaseResponse delDataFile(HttpServletRequest req, HttpServletResponse response) {
        BaseResponse resp = new BaseResponse();
        resp.setStatus(HttpStatus.OK.value());
        return resp;
    }

    @RequestMapping(value = "/listDataFile", method = RequestMethod.POST)
    @ResponseBody
    public BaseResponse listDataFile(HttpServletRequest req, HttpServletResponse response) {
        BaseResponse resp = new BaseResponse();
        resp.setStatus(HttpStatus.OK.value());
        return resp;
    }

    @RequestMapping(value = "/UploadDataFile", method = RequestMethod.POST)
    @ResponseBody
    public BaseResponse UploadDataFile(HttpServletRequest req, HttpServletResponse response) {
        BaseResponse resp = new BaseResponse();
        resp.setStatus(HttpStatus.OK.value());
        return resp;
    }

    @RequestMapping(value = "/UpdateDataSize", method = RequestMethod.POST)
    @ResponseBody
    public BaseResponse UpdateDataSize(HttpServletRequest req, HttpServletResponse response) {
        BaseResponse resp = new BaseResponse();
        resp.setStatus(HttpStatus.OK.value());
        return resp;
    }
}
