package com.iot.controller;

import com.iot.domain.PromotionSpace;
import com.iot.domain.ResponseResult;
import com.iot.service.PromotionSpaceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/promotionSpace")
public class PromotionSpaceController {

    @Autowired
    PromotionSpaceService promotionSpaceMapperService;

    /*查询所有的广告位*/
    @RequestMapping("/findAllPromotionSpace")
    public ResponseResult findAllPromotionSpace() {

        List<PromotionSpace> allPromotionSpace = promotionSpaceMapperService.findAllPromotionSpace();

        return new ResponseResult(true, 200, "响应成功", allPromotionSpace);
    }


    /*根据id查询广告位*/
    @RequestMapping("/findPromotionSpaceById")
    public ResponseResult  findPromotionSpaceById(@RequestParam  int id){

        PromotionSpace promotion = promotionSpaceMapperService.findPromotionSpaceById(id);

        return  new ResponseResult(true,200,"响应成功",promotion);

    }


    /*添加修改广告位*/
    @RequestMapping("saveOrUpdatePromotionSpace")
    public  ResponseResult savePromotionSpace(@RequestBody  PromotionSpace promotionSpace){

        //判断传递的promotionSpace是否有id   有id为修改  没有id 为添加
        if (promotionSpace.getId()==null){
            promotionSpaceMapperService.savePromotionSpace(promotionSpace);
            return  new ResponseResult(true,200,"新增广告位成功",null);
        }else {
            promotionSpaceMapperService.updatePromotionSpace(promotionSpace);
            return  new ResponseResult(true,200,"修改广告位成功",null);
        }

    }

}
