package com.iot.service;

import com.iot.domain.PromotionSpace;

import java.util.List;

public interface PromotionSpaceService {


    /*查询所有的广告位*/
    public List<PromotionSpace> findAllPromotionSpace();


    /*添加广告位*/
    public  void savePromotionSpace(PromotionSpace promotionSpace);

    /*修改广告位的信息*/
    public  void  updatePromotionSpace(PromotionSpace promotionSpace);


    /*根据id查询广告位*/
    public PromotionSpace  findPromotionSpaceById(int id);

}
