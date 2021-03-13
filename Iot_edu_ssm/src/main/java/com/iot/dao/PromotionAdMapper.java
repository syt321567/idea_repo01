package com.iot.dao;

import com.iot.domain.PromotionAd;

import java.util.List;

/*广告接口*/
public interface PromotionAdMapper {

    /*分页获取广告位的列表 */
    public List<PromotionAd> findPromoAdByPage();

    /*广告的的保存*/
    public void  savePromotionAd(PromotionAd promotionAd);


    /*根据id查询广告的信息  用于广告信息的回显*/
    public PromotionAd findPromotionAdById(int id);


    /*广告的修稿*/
    public void  updatePromotionAd(PromotionAd promotionAd);


    /*修改广告的状态*/
    public  void updatePromotionStatus(PromotionAd promotionAd);
}
