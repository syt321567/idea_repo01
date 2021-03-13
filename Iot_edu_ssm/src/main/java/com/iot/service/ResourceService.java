package com.iot.service;

import com.github.pagehelper.PageInfo;
import com.iot.domain.Resource;
import com.iot.domain.ResourceVo;

import java.util.List;

public interface ResourceService {



    /*分页查询资源的信息 */
    public PageInfo<Resource> findAllResource(ResourceVo resourceVo);


    /*添加资源的信息*/
    public void saveResource(Resource resource);


    /*根据id查询资源的信息*/
    public  Resource findResourceById(int id);


    /*修改资源的信息*/
    public  void  updateResource(Resource resource);


    /*根据id删除资源的信息*/
    public  void  deleteResource (int id);
}
