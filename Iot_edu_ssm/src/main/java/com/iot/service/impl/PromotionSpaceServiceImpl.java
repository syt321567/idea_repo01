package com.iot.service.impl;

import com.iot.dao.PromotionSpaceMapper;
import com.iot.domain.PromotionSpace;
import com.iot.service.PromotionSpaceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
public class PromotionSpaceServiceImpl implements PromotionSpaceService {


    @Autowired
    PromotionSpaceMapper promotionSpaceMapper;

    @Override
    public List<PromotionSpace> findAllPromotionSpace() {
        return promotionSpaceMapper.findAllPromotionSpace();

    }

    @Override
    public void savePromotionSpace(PromotionSpace promotionSpace) {

        //spaceKey不重复  调用 uuid  封装
        Date date = new Date();
        promotionSpace.setSpaceKey(UUID.randomUUID().toString());
        promotionSpace.setCreateTime(date);
        promotionSpace.setUpdateTime(date);

        promotionSpaceMapper.savePromotionSpace(promotionSpace);
    }

    @Override
    public void updatePromotionSpace(PromotionSpace promotionSpace) {

        promotionSpace.setUpdateTime(new Date());
        promotionSpaceMapper.updatePromotionSpace(promotionSpace);
    }

    @Override
    public PromotionSpace findPromotionSpaceById(int id) {
        return promotionSpaceMapper.findPromotionSpaceById(id);
    }

}
