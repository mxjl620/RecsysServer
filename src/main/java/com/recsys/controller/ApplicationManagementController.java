package com.recsys.controller;

import com.recsys.http.request.AddApplicationRequest;
import com.recsys.http.request.DelApplicationRequest;
import com.recsys.http.request.ListApplicationRequest;
import com.recsys.http.request.ModifyApplicationInfoRequest;
import com.recsys.service.ApplicationService;
import com.recsys.util.Application;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import com.recsys.http.BaseResponse;

import java.util.List;

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
    public BaseResponse addApplication(@RequestBody final AddApplicationRequest request) {
        BaseResponse resp = new BaseResponse();
        if (request.getUserid() == null) {
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
        if (request.getEmail() != null) {
            application.setEmail(request.getEmail());
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
    public BaseResponse modifyApplicationInfo(@RequestBody ModifyApplicationInfoRequest request) {
        BaseResponse resp = new BaseResponse();
        if (request.getUserid() == null || request.getAppid() == null) {
            resp.setStatus(400);
            resp.setMsg("parameter error!");
            return resp;
        }
        Application application = new Application();
        application.setAppid(request.getAppid());
        if (request.getAppName() != null) {
            application.setAppName(request.getAppName());
        }
        if (request.getAppDesc() != null) {
            application.setAppDesc(request.getAppDesc());
        }
        if (request.getEmail() != null) {
            application.setEmail(request.getEmail());
        }
        if (request.getProductDesc() != null) {
            application.setProductDesc(request.getProductDesc());
        }
        applicationService.modifyApplication(request.getUserid(), application);
        resp.setStatus(HttpStatus.OK.value());
        return resp;
    }

    @RequestMapping(value = "/delApplication", method = RequestMethod.POST)
    @ResponseBody
    public BaseResponse delApplication(@RequestBody DelApplicationRequest request) {
        BaseResponse resp = new BaseResponse();
        if (request.getUserid() == null || request.getAppid() == null) {
            resp.setStatus(400);
            resp.setMsg("parameter error!");
            return resp;
        }
        resp.setData(applicationService.deleteApplication(request.getUserid(), request.getAppid()));
        resp.setStatus(HttpStatus.OK.value());
        return resp;
    }

    @RequestMapping(value = "/listApplication", method = RequestMethod.POST)
    @ResponseBody
    public BaseResponse listApplication(@RequestBody ListApplicationRequest request) {
        BaseResponse resp = new BaseResponse();
        if (request.getUserid() == null) {
            resp.setStatus(400);
            resp.setMsg("parameter error!");
            return resp;
        }

        List<Application> applications = applicationService.listApplicationByUserid(request.getUserid());
        resp.setStatus(HttpStatus.OK.value());
        resp.setData(applications);
        return resp;
    }
}
