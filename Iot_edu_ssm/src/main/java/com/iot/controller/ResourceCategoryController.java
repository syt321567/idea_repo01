package com.iot.controller;

import com.iot.domain.ResourceCategory;
import com.iot.domain.ResponseResult;
import com.iot.service.ResourceCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/ResourceCategory")
public class ResourceCategoryController {


    @Autowired
    ResourceCategoryService resourceCategoryService;

    @RequestMapping("/findAllResourceCategory")
    public ResponseResult findAllResourceCategory(){

        List<ResourceCategory> allResourceCategory = resourceCategoryService.findAllResourceCategory();

        return  new ResponseResult(true,200,"响应成功",allResourceCategory);

    }
}
