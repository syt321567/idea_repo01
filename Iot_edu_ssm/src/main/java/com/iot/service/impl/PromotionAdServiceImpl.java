package com.iot.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.iot.dao.PromotionAdMapper;
import com.iot.domain.PromotionAd;
import com.iot.domain.PromotionAdVo;
import com.iot.service.PromotionAdService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class PromotionAdServiceImpl implements PromotionAdService {

    @Autowired
    PromotionAdMapper promotionAdMapper;

    @Override
    public PageInfo<PromotionAd> findPromotionAdByPage(PromotionAdVo promotionAdVo) {

        /*进行分页*/
        PageHelper.startPage(promotionAdVo.getCurrentPage(),promotionAdVo.getPageSize());

        List<PromotionAd> promoAdByPage = promotionAdMapper.findPromoAdByPage();

            PageInfo<PromotionAd> pageInfo=new PageInfo<PromotionAd>(promoAdByPage);

            return pageInfo;
    }

    @Override
    public void savePromotionAd(PromotionAd promotionAd) {

        Date date=new Date();
        //封装数据
        promotionAd.setCreateTime(date);
        promotionAd.setUpdateTime(date);

        //进行写入
        promotionAdMapper.savePromotionAd(promotionAd);

    }

    @Override
    public PromotionAd findPromotionAdById(int id) {
        PromotionAd promotionAd = promotionAdMapper.findPromotionAdById(id);
        return  promotionAd;
    }

    @Override
    public void updatePromotionAd(PromotionAd promotionAd) {

        promotionAd.setUpdateTime(new Date());

        promotionAdMapper.updatePromotionAd(promotionAd);

    }

    @Override
    public void updatePromotionAdStatus(int id, int status) {

        /*封装PromotionAd对象*/
        PromotionAd promotionAd=new PromotionAd();
        promotionAd.setId(id);
        promotionAd.setUpdateTime(new Date());
        promotionAd.setStatus(status);
        /*修改数据*/
        promotionAdMapper.updatePromotionStatus(promotionAd);
    }
}
