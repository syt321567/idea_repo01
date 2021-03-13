package com.iot.controller;

import com.github.pagehelper.PageInfo;
import com.iot.domain.Resource;
import com.iot.domain.ResourceVo;
import com.iot.domain.ResponseResult;
import com.iot.service.ResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/resource")
public class ResourceController {

    @Autowired

    private ResourceService resourceService;

    /*查询资源信息*/
    @RequestMapping("/findAllResource")
    public ResponseResult findAllResource(@RequestBody ResourceVo resourceVo) {

        PageInfo<Resource> allResource = resourceService.findAllResource(resourceVo);

        return new ResponseResult(true, 200, "响应成功", allResource);

    }


    /*根据id 查询资源*/
    @RequestMapping("/findResourceById")
    public ResponseResult findResourceById(@RequestParam int id) {

        Resource resource = resourceService.findResourceById(id);

        return new ResponseResult(true, 200, "响应成功", resource);
    }

    /*添加修改资源的信息*/
    @RequestMapping("/saveOrUpdateResource")
    public ResponseResult saveOrUpdateResource(@RequestBody Resource resource) {
        if (resource.getId() == null) {
            resourceService.saveResource(resource);
            return new ResponseResult(true, 200, "响应成功", null);
        } else {
            resourceService.updateResource(resource);
            return new ResponseResult(true, 200, "响应成功", null);
        }

    }

    /*根据id 删除资源的信息*/
    @RequestMapping("/deleteResource")
    public ResponseResult deleteResource(@RequestParam int id) {

        resourceService.deleteResource(id);

        return new ResponseResult(true, 200, "响应成功", null);
    }

}
