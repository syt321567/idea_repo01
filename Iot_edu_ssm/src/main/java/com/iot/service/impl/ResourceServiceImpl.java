package com.iot.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.iot.dao.ResourceMapper;
import com.iot.domain.Resource;
import com.iot.domain.ResourceVo;
import com.iot.service.ResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class ResourceServiceImpl implements ResourceService {

    @Autowired
    private ResourceMapper resourceMapper;

    @Override
    public  PageInfo<Resource>  findAllResource(ResourceVo resourceVo) {

        PageHelper.startPage(resourceVo.getCurrentPage(),resourceVo.getPageSize());

        List<Resource> allResource = resourceMapper.findAllResource(resourceVo);

        PageInfo<Resource> adPageInfo = new PageInfo<Resource>(allResource);

        return adPageInfo;
    }

    @Override
    public void saveResource(Resource resource) {

        Date date = new Date();
        resource.setCreatedTime(date);

        resource.setUpdatedTime(date);

        resource.setCreatedBy("system");
        resource.setUpdatedBy("system");

        resourceMapper.saveResource(resource);
    }

    @Override
    public Resource findResourceById(int id) {
     return    resourceMapper.findResourceById(id);
    }

    @Override
    public void updateResource(Resource resource) {

        resource.setUpdatedBy("system");
        resource.setUpdatedTime(new Date());
        resourceMapper.updateResource(resource);
    }

    @Override
    public void deleteResource(int id) {

        resourceMapper.deleteResource(id);
    }
}
