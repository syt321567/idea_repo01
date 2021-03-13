package com.iot.service.impl;

import com.iot.dao.ResourceCategoryMapper;
import com.iot.dao.ResourceMapper;
import com.iot.domain.ResourceCategory;
import com.iot.service.ResourceCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ResourceCategoryServiceImpl implements ResourceCategoryService {
    @Autowired
    ResourceCategoryMapper  resourceCategoryMapper;

    @Override
    public List<ResourceCategory> findAllResourceCategory() {

        return resourceCategoryMapper.findAllResourceCategory();

    }
}
