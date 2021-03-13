package com.iot.dao;

import com.iot.domain.PromotionAd;
import com.iot.domain.PromotionSpace;

import java.util.List;

/*广告位的接口*/
public interface PromotionSpaceMapper {


    /*获取所有的广告位*/
    public List<PromotionSpace> findAllPromotionSpace();

    /*添加广告位*/
    public  void savePromotionSpace(PromotionSpace promotionSpace);

    /*修改广告位的信息*/
    public  void  updatePromotionSpace(PromotionSpace promotionSpace);

    /*根据id查询记录*/
    public PromotionSpace  findPromotionSpaceById(int id);


}
