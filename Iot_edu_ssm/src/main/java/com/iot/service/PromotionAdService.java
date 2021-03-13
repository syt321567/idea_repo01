package com.iot.service;

import com.github.pagehelper.PageInfo;
import com.iot.domain.PromotionAd;
import com.iot.domain.PromotionAdVo;

import java.util.List;

public interface PromotionAdService {

    /*分页查询广告信息*/

    public PageInfo findPromotionAdByPage(PromotionAdVo promotionAdVo);


    /*广告的的保存*/
    public void savePromotionAd(PromotionAd promotionAd);


    /*根据id查询广告的信息  用于广告信息的回显*/
    public PromotionAd findPromotionAdById(int  id);


    /*广告的修稿*/
    public void updatePromotionAd(PromotionAd promotionAd);

    /*修改广告的状态*/
    public void updatePromotionAdStatus(int id,int status);


}
