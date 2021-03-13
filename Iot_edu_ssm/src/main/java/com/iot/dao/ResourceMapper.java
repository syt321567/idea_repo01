package com.iot.dao;

import com.iot.domain.Resource;
import com.iot.domain.ResourceVo;

import java.util.List;

public interface ResourceMapper {


    /*基于分页实现的查询资源表下面的信息*/
    public List<Resource> findAllResource(ResourceVo resourceVo);


    /*添加资源的信息*/
    public void saveResource(Resource resource);


    /*根据id查询资源的信息*/
    public  Resource findResourceById(int id);


    /*修改资源的信息*/
    public  void  updateResource(Resource resource);


    /*删除资源的信息*/
    public void deleteResource(int id);

}
