package com.recsys.controller;

import com.recsys.http.request.AddApplicationRequest;
import com.recsys.service.ApplicationService;
import com.recsys.util.Application;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import com.recsys.http.BaseResponse;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping(value = "/applicationManagement")
public class ApplicationManagementController {

    private ApplicationService applicationService;

    @Autowired
    public void setApplicationService(ApplicationService applicationService) {
        this.applicationService = applicationService;
    }

    @RequestMapping(value = "/addApplication", method = RequestMethod.POST)
    @ResponseBody
    public BaseResponse addApplication(@RequestBody final AddApplicationRequest request,
                                       HttpServletResponse response) {
        BaseResponse resp = new BaseResponse();
        if (request.getUserid().equals("")) {
            resp.setStatus(400);
            resp.setMsg("parameter error!");
            return resp;
        }
        Application application = new Application();
        if (request.getAppName() != null) {
            application.setAppName(request.getAppName());
        }
        if (request.getAppDesc() != null) {
            application.setAppDesc(request.getAppDesc());
        }
        if (request.getUserEmail() != null) {
            application.setEmail(request.getUserEmail());
        }
        if (request.getProductDesc() != null) {
            application.setProductDesc(request.getProductDesc());
        }
        Application result = applicationService.addApplication(application, request.getUserid());
        resp.setData(result);
        resp.setStatus(HttpStatus.OK.value());
        return resp;
    }

    @RequestMapping(value = "/modifyApplication", method = RequestMethod.POST)
    @ResponseBody
    public BaseResponse modifyApplication(HttpServletRequest req, HttpServletResponse response) {
        BaseResponse resp = new BaseResponse();
        resp.setStatus(HttpStatus.OK.value());
        return resp;
    }

    @RequestMapping(value = "/delApplication", method = RequestMethod.POST)
    @ResponseBody
    public BaseResponse delApplication(HttpServletRequest req, HttpServletResponse response) {
        BaseResponse resp = new BaseResponse();
        resp.setStatus(HttpStatus.OK.value());
        return resp;
    }

    @RequestMapping(value = "/listApplication", method = RequestMethod.POST)
    @ResponseBody
    public BaseResponse listApplication(HttpServletRequest req, HttpServletResponse response) {
        BaseResponse resp = new BaseResponse();
        resp.setStatus(HttpStatus.OK.value());
        return resp;
    }
}
